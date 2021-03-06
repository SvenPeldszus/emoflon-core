package org.moflon.core.ui.autosetup.handler;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.menus.ExtensionContributionFactory;
import org.eclipse.ui.menus.IContributionRoot;
import org.eclipse.ui.services.IServiceLocator;
import org.moflon.core.ui.autosetup.WorkspaceInstaller;
import org.moflon.core.utilities.ExtensionsUtil;

public class PopulatePsfUrlMenu extends ExtensionContributionFactory {
	private Logger logger = Logger.getLogger(PopulatePsfUrlMenu.class);

	private static final String URI_PREF_EXTENSION_ID = "org.moflon.core.ui.autosetup.RegisterPsfUrlExtension";

	private static Collection<RegisterPsfUrlExtension> registerPsfUrlExtensions = ExtensionsUtil
			.collectExtensions(URI_PREF_EXTENSION_ID, "class", RegisterPsfUrlExtension.class);

	@Override
	public void createContributionItems(IServiceLocator serviceLocator, IContributionRoot additions) {
		registerPsfUrlExtensions.stream()//
				.sorted((r1, r2) -> r1.getLabel().compareTo(r2.getLabel()))//
				.forEach(ext -> {
					additions.addContributionItem(new ContributionItem() {
						@Override
						public void fill(Menu menu, int index) {
							logger.debug("Inserting " + ext.getLabel() + " at " + index);
							MenuItem mi = new MenuItem(menu, SWT.DEFAULT, menu.getItemCount());
							mi.setText(ext.getLabel());
							mi.addSelectionListener(new SelectionAdapter() {
								@Override
								public void widgetSelected(SelectionEvent e) {
									logger.info(ext.getLabel() + " selected!");
									logger.info(ext.getUrl() + " is going to be imported.");
									new WorkspaceInstaller().installPsfFile(ext.getUrl(), ext.getLabel());
								}
							});
						}
					}, null);
				});
	}
}
