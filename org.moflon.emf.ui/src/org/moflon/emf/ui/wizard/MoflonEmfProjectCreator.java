package org.moflon.emf.ui.wizard;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.moflon.core.build.MoflonProjectCreator;
import org.moflon.core.build.nature.MoflonProjectConfigurator;
import org.moflon.core.plugins.PluginProperties;
import org.moflon.emf.build.MoflonEmfBuilder;
import org.moflon.emf.build.MoflonEmfNature;

public class MoflonEmfProjectCreator extends MoflonProjectCreator
{
   private static final List<String> GITIGNORE_LINES = Arrays.asList(//
         "/bin", //
         "/gen/*", //
         "/model/*.ecore", "/model/*.genmodel", "/model/*.xmi", //
         "!/**/.keep*");

   /**
   * Pass-through constructor to {@link MoflonProjectCreator}
   * @param project the project to create
   * @param projectProperties the metadata to use
   * @param projectConfigurator the project configurator
   */
   public MoflonEmfProjectCreator(IProject project, PluginProperties projectProperties, MoflonProjectConfigurator projectConfigurator)
   {
      super(project, projectProperties, projectConfigurator);
   }

   @Override
   protected List<String> getGitignoreLines()
   {
      return GITIGNORE_LINES;
   }

   @Override
   protected String getNatureId() throws CoreException
   {
      return MoflonEmfNature.getId();
   }

   @Override
   protected String getBuilderId() throws CoreException
   {
      return MoflonEmfBuilder.getId();
   }

}
