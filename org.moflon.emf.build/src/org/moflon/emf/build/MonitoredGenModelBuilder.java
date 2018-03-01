package org.moflon.emf.build;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.gervarro.eclipse.task.ITask;
import org.moflon.core.propertycontainer.MoflonPropertiesContainer;
import org.moflon.core.propertycontainer.MoflonPropertiesContainerHelper;
import org.moflon.core.utilities.WorkspaceHelper;
import org.moflon.core.utilities.eMoflonEMFUtil;
import org.moflon.emf.codegen.MoflonGenModelBuilder;

public final class MonitoredGenModelBuilder implements ITask {
	private final ResourceSet resourceSet;

	private final IFile ecoreFile;

	private final List<Resource> resources;

	private final boolean saveGenModel;

	private MoflonPropertiesContainer moflonProperties;

	private GenModel genModel;

	public MonitoredGenModelBuilder(final ResourceSet resourceSet, final List<Resource> resources,
			final IFile ecoreFile, final boolean saveGenModel, final MoflonPropertiesContainer properties) {
		this.resourceSet = resourceSet;
		this.ecoreFile = ecoreFile;
		this.resources = resources;
		this.saveGenModel = saveGenModel;
		this.moflonProperties = properties;
	}

	@Override
	public final IStatus run(final IProgressMonitor monitor) {
		final SubMonitor subMon = SubMonitor.convert(monitor, getTaskName() + " task", 100);
		final IProject project = ecoreFile.getProject();
		subMon.subTask("Building or loading GenModel for project " + project.getName());
		subMon.worked(5);

		if (this.moflonProperties == null) {
			this.moflonProperties = MoflonPropertiesContainerHelper.load(project, subMon.split(5));

		}
		subMon.setWorkRemaining(90);

		if (subMon.isCanceled()) {
			return Status.CANCEL_STATUS;
		}

		// Create EMFCodegen
		final String basePackage = "";
		final String modelDirectory = WorkspaceHelper.getGenFolder(project).getFullPath().toString();

		final MoflonGenModelBuilder genModelBuilder = new MoflonGenModelBuilder(resourceSet, resources, ecoreFile,
				basePackage, modelDirectory, moflonProperties);
		genModelBuilder.loadDefaultSettings();
		subMon.worked(10);
		if (subMon.isCanceled()) {
			return Status.CANCEL_STATUS;
		}

		final URI projectURI = URI.createPlatformResourceURI(project.getName() + "/", true);
		final URI ecoreURI = URI.createURI(ecoreFile.getProjectRelativePath().toString()).resolve(projectURI);
		final URI genModelURI = MoflonGenModelBuilder.calculateGenModelURI(ecoreURI);
		final boolean isNewGenModelConstructed = genModelBuilder.isNewGenModelRequired(genModelURI);
		try {
			this.genModel = genModelBuilder.buildGenModel(genModelURI);
		} catch (final RuntimeException e) {
			return new Status(IStatus.ERROR, WorkspaceHelper.getPluginId(getClass()), e.getMessage(), e);
		}
		subMon.worked(30);
		if (subMon.isCanceled()) {
			return Status.CANCEL_STATUS;
		}

		// Validate resource set
		final IStatus resourceSetStatus = eMoflonEMFUtil.validateResourceSet(resourceSet, "GenModel building",
				subMon.split(10));
		if (subMon.isCanceled()) {
			return Status.CANCEL_STATUS;
		}
		if (!resourceSetStatus.isOK()) {
			return resourceSetStatus;
		}

		// Validate GenModel
		final IStatus genModelValidationStatus = eMoflonEMFUtil.validateGenModel(this.genModel);
		subMon.worked(30);
		if (subMon.isCanceled()) {
			return Status.CANCEL_STATUS;
		}
		if (!genModelValidationStatus.isOK()) {
			return genModelValidationStatus;
		}

		IStatus saveStatus = Status.OK_STATUS;
		if (this.saveGenModel) {
			saveStatus = saveGenModel(isNewGenModelConstructed);
		}
		subMon.worked(10);
		return saveStatus.isOK() ? Status.OK_STATUS : saveStatus;
	}

	private IStatus saveGenModel(final boolean isNewGenModelConstructed) {
		IStatus saveStatus;
		try {
			final Resource genModelResource = genModel.eResource();
			final Map<String, Object> saveOptions = new HashMap<String, Object>();
			saveOptions.put(Resource.OPTION_LINE_DELIMITER, WorkspaceHelper.DEFAULT_RESOURCE_LINE_DELIMITER);
			if (isNewGenModelConstructed) {
				// Save to file (with no options)
				genModelResource.save(saveOptions);
			} else {
				// Save to file (if modified)
				saveOptions.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED,
						Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
				genModelResource.save(saveOptions);
			}
			saveStatus = Status.OK_STATUS;
		} catch (final IOException e) {
			saveStatus = new Status(IStatus.ERROR, WorkspaceHelper.getPluginId(getClass()), IStatus.ERROR,
					e.getMessage(), e);
		}
		return saveStatus;
	}

	public final GenModel getGenModel() {
		return genModel;
	}

	@Override
	public final String getTaskName() {
		return "GenModel building";
	}
}
