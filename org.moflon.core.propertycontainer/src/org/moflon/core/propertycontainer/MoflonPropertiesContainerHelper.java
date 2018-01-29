package org.moflon.core.propertycontainer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.moflon.core.utilities.LogUtils;
import org.moflon.core.utilities.MoflonConventions;
import org.moflon.core.utilities.WorkspaceHelper;
import org.moflon.core.utilities.eMoflonEMFUtil;

public class MoflonPropertiesContainerHelper
{
   /**
    * This string is used as a placeholder for the correct metamodel name
    */
   public static final String UNDEFINED_METAMODEL_NAME = "NO_META_MODEL_PROJECT_NAME_SET_YET";

   private static final Logger logger = Logger.getLogger(MoflonPropertiesContainerHelper.class);

   /**
    * Loads the eMoflon properties of the given project.
    *
    * @param project
    * @param monitor
    * @return the properties. Is never null.
    */
   public static MoflonPropertiesContainer load(final IProject project, final IProgressMonitor monitor)
   {
      final SubMonitor subMon = SubMonitor.convert(monitor, "Load properties.", 1);

      final MoflonPropertiesContainer container = loadOrCreatePropertiesContainer(project, MoflonConventions.getDefaultMoflonPropertiesFile(project));
      final String projectName = project.getName();
      checkAndUpdateMissingDefaults(container);

      if (!projectName.equals(container.getProjectName()))
      {
         LogUtils.warn(logger, "Project name in Moflon properties file ('%s') does not match Project. Setting correct project name to '%s'.",
               container.getProjectName(), projectName);
         container.setProjectName(projectName);
      }

      MoflonPropertiesContainerHelper.save(container, subMon.split(1));
      return container;
   }

   public static MoflonPropertiesContainer loadOrCreatePropertiesContainer(final IProject project, final IFile propertyFile)
   {
      MoflonPropertiesContainer moflonPropertiesContainer;
      if (propertyFile.exists())
      {
         PropertycontainerFactory.eINSTANCE.getClass();
         moflonPropertiesContainer = (MoflonPropertiesContainer) eMoflonEMFUtil.getResourceFromFileIntoDefaultResourceSet(propertyFile).getContents().get(0);

      } else
      {
         moflonPropertiesContainer = PropertycontainerFactory.eINSTANCE.createMoflonPropertiesContainer();
      }
      moflonPropertiesContainer.setProjectName(project.getName());
      return moflonPropertiesContainer;
   }

   public static MoflonPropertiesContainer createDefaultPropertiesContainer(final String projectName, final String metaModelProjectName)
   {
      MoflonPropertiesContainer container = PropertycontainerFactory.eINSTANCE.createMoflonPropertiesContainer();
      container.setProjectName(projectName);

      updateMetamodelProjectName(container, metaModelProjectName);
      checkAndUpdateMissingDefaults(container);

      return container;
   }

   /**
    * Saves the Moflon properties at the default path (see {@link MoflonConventions#getDefaultMoflonPropertiesFile(IProject)}
    * @param properties the properties to save
    * @param monitor the progress monitor to report to
    */
   public static void save(final MoflonPropertiesContainer properties, final IProgressMonitor monitor)
   {
      try
      {
         final SubMonitor subMon = SubMonitor.convert(monitor, "Saving eMoflon properties", 1);
         final IWorkspaceRoot workspace = ResourcesPlugin.getWorkspace().getRoot();
         final IProject project = workspace.getProject(properties.getProjectName());
         if (project == null)
         {
            LogUtils.error(logger, "Unable to save property file '%s' for project '%s'.", MoflonConventions.MOFLON_CONFIG_FILE, properties.getProjectName());
         } else
         {
            final IFile projectFile = project.getFile(MoflonConventions.MOFLON_CONFIG_FILE);
            final ResourceSet set = eMoflonEMFUtil.createDefaultResourceSet();
            final URI fileURI = eMoflonEMFUtil.createFileURI(projectFile.getLocation().toString(), false);
            final Resource resource = set.createResource(fileURI);
            resource.getContents().add(normalize(properties));

            final HashMap<String, String> saveOptions = new HashMap<String, String>();
            saveOptions.put(Resource.OPTION_LINE_DELIMITER, WorkspaceHelper.DEFAULT_RESOURCE_LINE_DELIMITER);
            resource.save(saveOptions);

            projectFile.refreshLocal(IResource.DEPTH_ZERO, subMon.split(1));
         }
      } catch (final Exception e)
      {
         LogUtils.error(logger, "Unable to save property file '%s' for project '%s':\n %s", MoflonConventions.MOFLON_CONFIG_FILE, properties.getProjectName(),
               WorkspaceHelper.printStacktraceToString(e));
      }

   }

   /**
    * This method sets the {@link MetaModelProject} of the given {@link MoflonPropertiesContainer} to the given value
    */
   public static void updateMetamodelProjectName(final MoflonPropertiesContainer moflonProperties, final String metamodelProjectName)
   {
      MetaModelProject metamodelProject = moflonProperties.getMetaModelProject();
      if (metamodelProject == null)
      {
         metamodelProject = PropertycontainerFactory.eINSTANCE.createMetaModelProject();
         moflonProperties.setMetaModelProject(metamodelProject);
         metamodelProject.setMetaModelProjectName(metamodelProjectName);
      }

      metamodelProject.setMetaModelProjectName(metamodelProjectName);
   }

   /**
    * Adds the minimal set of properties to a {@link MoflonPropertiesContainer}
    */
   public static void checkAndUpdateMissingDefaults(final MoflonPropertiesContainer moflonProperties)
   {
      final PropertycontainerFactory factory = PropertycontainerFactory.eINSTANCE;
      if (moflonProperties.getReplaceGenModel() == null)
      {
         moflonProperties.setReplaceGenModel(factory.createReplaceGenModel());
      }

      if (moflonProperties.getSdmCodegeneratorHandlerId() == null)
      {
         moflonProperties.setSdmCodegeneratorHandlerId(factory.createSdmCodegeneratorMethodBodyHandler());
      }

      if (moflonProperties.getTGGBuildMode() == null)
      {
         moflonProperties.setTGGBuildMode(factory.createTGGBuildMode());
      }

      if (moflonProperties.getMetaModelProject() == null)
      {
         final MetaModelProject metaModelProject = factory.createMetaModelProject();
         moflonProperties.setMetaModelProject(metaModelProject);
         metaModelProject.setMetaModelProjectName(UNDEFINED_METAMODEL_NAME);
      }
   }

   private static EObject normalize(final MoflonPropertiesContainer properties)
   {
      // Normalize properties to avoid unnecessary nondeterminism
      List<Dependencies> sortedDependencies = new ArrayList<>(properties.getDependencies());
      sortedDependencies.sort((d1, d2) -> d1.getValue().compareTo(d2.getValue()));
      properties.getDependencies().clear();
      properties.getDependencies().addAll(sortedDependencies);

      return properties;
   }

   public static Map<String, String> mappingsToMap(final List<? extends PropertiesMapping> mappings)
   {
      Map<String, String> map = new HashMap<String, String>();

      for (PropertiesMapping mapping : mappings)
         map.put(mapping.getKey(), mapping.getValue());

      return map;
   }

   public static Collection<String> mapToValues(final Collection<? extends PropertiesValue> values)
   {
      List<String> list = new LinkedList<String>();
      for (PropertiesValue value : values)
         list.add(value.getValue());
      return list;
   }

   public static MoflonPropertiesContainer createEmptyContainer()
   {
      return PropertycontainerFactory.eINSTANCE.createMoflonPropertiesContainer();
   }

   /**
    * Returns the code generator configured in moflon.properties.xmi
    */
   public static final String getMethodBodyHandler(final MoflonPropertiesContainer moflonProperties)
   {
      SDMCodeGeneratorIds handlerId = moflonProperties.getSdmCodegeneratorHandlerId().getValue();
      return handlerId.getLiteral();
   }

}