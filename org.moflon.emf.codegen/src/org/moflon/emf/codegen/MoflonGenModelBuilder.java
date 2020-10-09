package org.moflon.emf.codegen;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.RegistryFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.moflon.core.preferences.PlatformUriType;
import org.moflon.core.propertycontainer.AdditionalDependencies;
import org.moflon.core.propertycontainer.AdditionalUsedGenPackages;
import org.moflon.core.propertycontainer.MoflonPropertiesContainer;
import org.moflon.core.utilities.ExtensionsUtil;
import org.moflon.core.utilities.MoflonConventions;
import org.moflon.core.utilities.eMoflonEMFUtil;
import org.moflon.emf.codegen.dependency.Dependency;
import org.moflon.emf.codegen.dependency.DependencyTypes;
import org.moflon.emf.codegen.dependency.SimpleDependency;

public class MoflonGenModelBuilder extends GenModelBuilder {
	protected static final Logger logger = Logger.getLogger(MoflonGenModelBuilder.class);

	private static final String URI_PREF_EXTENSION_ID = "org.moflon.emf.codegen.URIPreferenceExtension";

	private static Collection<URIPreferenceExtension> uriPreferenceExtension = ExtensionsUtil
			.collectExtensions(URI_PREF_EXTENSION_ID, "class", URIPreferenceExtension.class);

	// The model file used for code generation
	protected IFile ecoreFile;

	protected final URI ecoreURI;

	private final List<Resource> resources;

	// The directory containing model file
	private final String modelDirectory;

	protected MoflonPropertiesContainer moflonProperties;

	private final String basePackage;

	public MoflonGenModelBuilder(final ResourceSet resourceSet, final List<Resource> resources, final IFile ecoreFile,
			final String basePackage, final String modelDirectory, final MoflonPropertiesContainer moflonProperties) {
		super(resourceSet);
		this.ecoreFile = ecoreFile;
		this.resources = resources;
		this.basePackage = basePackage;
		this.modelDirectory = modelDirectory;
		this.moflonProperties = moflonProperties;

		final IProject project = ecoreFile.getProject();

		final URI projectURI = determineProjectUriBasedOnPreferences(project);
		this.ecoreURI = MoflonConventions.getDefaultProjectRelativeEcoreFileURI(project).resolve(projectURI);
	}

	@Override
	public boolean isNewGenModelRequired(final URI genModelURI) {
		return super.isNewGenModelRequired(genModelURI) || moflonProperties.getReplaceGenModel().isBool();
	}

	@Override
	public void loadDefaultGenModelContent(final GenModel genModel) {
		super.loadDefaultGenModelContent(genModel);
		genModel.getForeignModel().add(ecoreFile.getName());
		genModel.setModelDirectory(modelDirectory); // org.gervarro.democles.emoflon/src
		for (final GenPackage genPackage : genModel.getGenPackages()) {
			genPackage.setBasePackage(basePackage);
		}

		// Enable generics
		genModel.setComplianceLevel(GenJDKLevel.JDK80_LITERAL);

		// Enable operation reflection
		genModel.setOperationReflection(true);

		// Set plugin id so conversion to plugin is available in editor
		genModel.setModelPluginID(ecoreFile.getProject().getName());
	}
	
	@Override
	public List<Dependency> getGenModelResourceDependencies() {
		List<Dependency> result = new BasicEList<Dependency>();

		// User-defined GenPackages
		for (final AdditionalUsedGenPackages usedDefinedGenPackage : moflonProperties.getAdditionalUsedGenPackages()) {
			result.add(new SimpleDependency(URI.createURI(usedDefinedGenPackage.getValue())));
		}

		for (final Resource resource : resources) {
			final URI uri = resource.getURI();
			if (!isAdditionalDependency(uri) && !uri.equals(ecoreURI)) {
				final int kind = DependencyTypes.getDependencyType(uri);
				if (kind == DependencyTypes.DEPLOYED_PLUGIN) {
					result.add(new SimpleDependency(lookupExtensionRegistry(uri)));
				} else if (kind == DependencyTypes.WORKSPACE_PLUGIN_PROJECT
						|| kind == DependencyTypes.WORKSPACE_PROJECT) {
					result.add(new SimpleDependency(calculateGenModelURI(uri)));
				}
			}
		}
		return result;
	}

	/**
	 * Returns a platform:/ {@link URI} for the given project based on the visible
	 * {@link URIPreferenceExtension}s
	 * 
	 * If multiple extensions exist, the preference of one extension is taken
	 * nondeterministically.
	 * 
	 * @param project
	 *                    the project
	 * @return the corresponding {@link URI}
	 */
	public static URI determineProjectUriBasedOnPreferences(final IProject project) {
		final PlatformUriType preferredGenModelPlatformUriType = uriPreferenceExtension.stream()//
				.map(URIPreferenceExtension::getPlatformURIType)//
				.findAny()//
				.orElse(PlatformUriType.DEFAULT);
		final URI projectURI = determineProjectUriBasedOnPlatformUriType(project, preferredGenModelPlatformUriType);
		return projectURI;
	}

	/**
	 * Returns a platform:/ {@link URI} for the given project based on the given
	 * {@link PlatformUriType}
	 * 
	 * @param project
	 *                            the project
	 * @param platformUriType
	 *                            the {@link PlatformUriType}
	 * @return the corresponding {@link URI}
	 */
	private static URI determineProjectUriBasedOnPlatformUriType(final IProject project,
			final PlatformUriType platformUriType) {
		final URI projectURI;
		switch (platformUriType) {
		case RESOURCE:
			projectURI = eMoflonEMFUtil.lookupProjectURIAsPlatformResource(project);
			break;
		case PLUGIN:
		default:
			projectURI = eMoflonEMFUtil.lookupProjectURI(project);
			break;
		}
		return projectURI;
	}

	public static final URI calculateGenModelURI(final URI ecoreURI) {
		return ecoreURI.trimFileExtension().appendFileExtension(GENMODEL_FILE_EXTENSION);
	}

	private final boolean isAdditionalDependency(final URI uri) {
		for (AdditionalDependencies dependency : moflonProperties.getAdditionalDependencies()) {
			if (dependency.getValue().equals(uri.toString())) {
				return true;
			}
		}
		return false;
	}

	private final URI lookupExtensionRegistry(final URI uri) {
		assert uri.isPlatformPlugin() && uri.segmentCount() >= 2;
		final String uriString = uri.toString();
		final String pluginID = uri.segment(1);
		final IExtensionPoint extensionPoint = RegistryFactory.getRegistry()
				.getExtensionPoint("org.eclipse.emf.ecore.generated_package");
		for (IExtension extension : extensionPoint.getExtensions()) {
			if (pluginID.equals(extension.getContributor().getName())) {
				for (IConfigurationElement config : extension.getConfigurationElements()) {
					if (uriString.equals(config.getAttribute("uri"))) {
						final String genModelAttribute = config.getAttribute("genModel");
						if (genModelAttribute != null) {
							final URI pluginURI = URI.createPlatformPluginURI(pluginID + "/", true);
							return URI.createURI(genModelAttribute).resolve(pluginURI);
						}
					}
				}
				extension.getConfigurationElements()[0].getAttribute("genModel");
			}
		}
		return calculateGenModelURI(uri);
	}
}
