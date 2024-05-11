package org.moflon.core.plugins.manifest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.URI;
import org.gervarro.eclipse.workspace.util.WorkspaceTask;
import org.moflon.core.utilities.MoflonConventions;
import org.moflon.core.utilities.WorkspaceHelper;
import org.moflon.core.utilities.XMLUtils;
import org.moflon.core.utilities.eMoflonEMFUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This class updates plugin.xml, e.g., with information from the genmodel.
 */
public class PluginXmlUpdater extends WorkspaceTask {
	private final IProject project;

	private final GenModel genModel;

	private PluginXmlUpdater(final IProject project) {
		this(project, extractGenModelFromProject(project));
	}

	private PluginXmlUpdater(final IProject project, final GenModel genModel) {
		this.project = project;
		this.genModel = genModel;
	}

	private static class GeneratedPackageEntry {
		private final String uri;

		private final String className;

		private final String genmodelFile;

		public GeneratedPackageEntry(final String uri, final String className, final String genmodelFile) {
			this.uri = uri;
			this.className = className;
			this.genmodelFile = genmodelFile;
		}

	}

	/**
	 * Minimal content of a plugin.xml file
	 */
	public static final String DEFAULT_PLUGIN_XML_CONTENT = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<?eclipse version=\"3.0\"?>\n<plugin>\n</plugin>";

	/**
	 * Updates plugin.xml from the information in the given project. The genmodel is
	 * fetched from the default path.
	 *
	 * @see WorkspaceHelper#getProjectGenmodelFile(IProject)
	 */
	public static final void updatePluginXml(final IProject currentProject, final IProgressMonitor monitor)
			throws CoreException {
		final var subMon = SubMonitor.convert(monitor, "Create/update plugin.xml", 1);

		updatePluginXml(currentProject, extractGenModelFromProject(currentProject), subMon.split(1));
	}

	public static final void updatePluginXml(final IProject currentProject, final GenModel genModel,
			final IProgressMonitor monitor) throws CoreException {
		final var pluginXmlUpdater = new PluginXmlUpdater(currentProject, genModel);
		WorkspaceTask.executeInCurrentThread(pluginXmlUpdater, IWorkspace.AVOID_UPDATE, monitor);
	}

	/**
	 * Updates plugin.xml from the information in the given project and the given
	 * genmodel.
	 */
	@Override
	public void run(final IProgressMonitor monitor) throws CoreException {
		try {
			final var subMon = SubMonitor.convert(monitor, "Updating plugin.xml from Genmodel", 3);
			final var content = readOrGetDefaultPluginXmlContent(this.project);
			final var doc = XMLUtils.parseXmlDocument(content);
			subMon.worked(1);

			removeExtensionPointsForGeneratedPackages(doc);

			final var extensionElements = createListOfGeneratedPackageExtensions(doc, this.project, this.genModel);
			final var pluginRootElement = getRootNode(doc);
			extensionElements.forEach(element -> pluginRootElement.appendChild(element));

			final var output = XMLUtils.formatXmlString(doc, subMon.split(1));

			if (!output.equals(content)) {
				WorkspaceHelper.addFile(this.project, "plugin.xml", output, subMon.split(1));
			}

		} catch (IOException | XPathExpressionException e) {
			throw new CoreException(new Status(IStatus.ERROR, WorkspaceHelper.getPluginId(getClass()),
					"Error reading/writing plugin.xml for project " + this.project.getName() + ": " + e.getMessage(), e));
		}
	}

	public static Node getRootNode(final Document doc) {
		return doc.getElementsByTagName("plugin").item(0);
	}

	@Override
	public String getTaskName() {
		return "Plugin.xml updater";
	}

	@Override
	public ISchedulingRule getRule() {
		return this.project;
	}

	private static void removeExtensionPointsForGeneratedPackages(final Document doc) throws XPathExpressionException {
		final var extensionPoints = PluginXmlUpdater.getGeneratedPackageExtensionPoints(doc);
		for (var n = 0; n < extensionPoints.getLength(); ++n) {
			extensionPoints.item(n).getParentNode().removeChild(extensionPoints.item(n));
		}
	}

	private static String readOrGetDefaultPluginXmlContent(final IProject project) throws IOException, CoreException {
		final var pluginXmlFile = getPluginXml(project);
		var content = "";
		if (pluginXmlFile.exists()) {
			content = new String(pluginXmlFile.getContents().readAllBytes());
		} else {
			content = DEFAULT_PLUGIN_XML_CONTENT;
		}
		return content;
	}

	private static List<Element> createListOfGeneratedPackageExtensions(final Document doc, final IProject project,
			final GenModel genmodel) {
		final var entries = extractGeneratedPackageEntries(project, genmodel);
		final List<Element> extensionElements = new ArrayList<>();

		entries.forEach(entry -> {
			final var extensionElement = doc.createElement("extension");
			extensionElement.setAttribute("point", "org.eclipse.emf.ecore.generated_package");
			final var packageElement = doc.createElement("package");
			packageElement.setAttribute("uri", entry.uri);
			packageElement.setAttribute("class", entry.className);
			packageElement.setAttribute("genModel", entry.genmodelFile);
			extensionElement.appendChild(packageElement);
			extensionElements.add(extensionElement);
		});
		return extensionElements;
	}

	private static List<GeneratedPackageEntry> extractGeneratedPackageEntries(final IProject project,
			final GenModel genmodel) {
		final var genmodelFile = MoflonConventions.getDefaultPathToGenModelInProject(project.getName());
		final List<GeneratedPackageEntry> entries = new ArrayList<>();
		final var ePackages = genmodel.getAllGenPackagesWithClassifiers();
		for (final GenPackage genPackage : ePackages) {
			final var fullyQualifiedPackageClassName = genPackage.getInterfacePackageName() + "."
					+ genPackage.getPackageInterfaceName();

			entries.add(new GeneratedPackageEntry(genPackage.getNSURI(), fullyQualifiedPackageClassName, genmodelFile));
		}
		return entries;
	}

	private static IFile getPluginXml(final IProject currentProject) {
		return currentProject.getFile("plugin.xml");
	}

	private static NodeList getGeneratedPackageExtensionPoints(final Document doc) throws XPathExpressionException {
		final var xPathfactory = XPathFactory.newInstance();
		final var xpath = xPathfactory.newXPath();
		final var expr = xpath.compile("/plugin/extension[@point='org.eclipse.emf.ecore.generated_package']");
		final var extensionPoints = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		return extensionPoints;
	}

	/**
	 * Loads the genmodel from the given project, assuming the default genmodel path
	 * as returned by
	 * {@link MoflonConventions#getDefaultPathToGenModelInProject(String)}.
	 *
	 * @return the genmodel (if exists)
	 */
	private static GenModel extractGenModelFromProject(final IProject currentProject) {
		final var pathInsideProject = MoflonConventions.getDefaultPathToGenModelInProject(currentProject.getName());
		final var projectGenModelFile = currentProject.getFile(pathInsideProject);
		final var pathToGenmodel = projectGenModelFile.getRawLocation().toOSString();
		final var set = eMoflonEMFUtil.createDefaultResourceSet();
		final var genModelResource = set.getResource(URI.createFileURI(pathToGenmodel), true);
		final var genmodel = (GenModel) genModelResource.getContents().get(0);
		return genmodel;
	}
}
