/*
 * Copyright (c) 2011, Marc Röttig.
 *
 * This file is part of GenericKnimeNodes.
 * 
 * GenericKnimeNodes is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.ballproject.knime;

import org.ballproject.knime.base.mime.DefaultMIMEtypeRegistry;
import org.ballproject.knime.base.mime.MIMEtypeRegistry;
import org.ballproject.knime.base.mime.demangler.Demangler;
import org.ballproject.knime.base.mime.demangler.DemanglerProvider;
import org.ballproject.knime.base.preferences.GKNPreferenceInitializer;
import org.ballproject.knime.base.util.FileStash;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.knime.core.node.NodeLogger;
import org.osgi.framework.BundleContext;

/**
 * This is the eclipse bundle activator. Note: KNIME node developers probably
 * won't have to do anything in here, as this class is only needed by the
 * eclipse platform/plugin mechanism. If you want to move/rename this file, make
 * sure to change the plugin.xml file in the project root directory accordingly.
 * 
 * @author roettig
 */
public class GenericNodesPlugin extends AbstractUIPlugin {
	// The shared instance.
	private static GenericNodesPlugin plugin;

	private static final NodeLogger logger = NodeLogger
			.getLogger(GenericNodesPlugin.class);

	// TODO check if removable
	public static boolean DEBUG = false;
	private static DefaultMIMEtypeRegistry registry = new DefaultMIMEtypeRegistry();

	public static void log(String message) {
		if (GenericNodesPlugin.DEBUG) {
			System.out.println(message);
			logger.info(message);
		}
	}

	public static boolean isDebug() {
		return GenericNodesPlugin.DEBUG;
	}

	public static void toggleDebug() {
		GenericNodesPlugin.DEBUG = !GenericNodesPlugin.DEBUG;
		System.out.println("toggling Debug Mode");
	}

	public static void setDebug(boolean flag) {
		GenericNodesPlugin.DEBUG = flag;
		System.out.println("setting Debug Mode :" + flag);
	}

	public static MIMEtypeRegistry getMIMEtypeRegistry() {
		return registry;
	}

	/**
	 * The constructor.
	 */
	public GenericNodesPlugin() {
		super();
		plugin = this;
	}

	/**
	 * This method is called upon plug-in activation.
	 * 
	 * @param context
	 *            The OSGI bundle context
	 * @throws Exception
	 *             If this plugin could not be started
	 */
	@Override
	public void start(final BundleContext context) throws Exception {
		super.start(context);

		log("starting plugin: GenericNodesPlugin");

		IConfigurationElement[] config = Platform
				.getExtensionRegistry()
				.getConfigurationElementsFor(
						"org.ballproject.knime.base.mime.demangler.DemanglerProvider");
		try {
			for (IConfigurationElement e : config) {
				final Object o = e.createExecutableExtension("class");
				if (o instanceof DemanglerProvider) {
					DemanglerProvider dp = (DemanglerProvider) o;
					for (Demangler dm : dp.getDemanglers()) {
						log("registering Demangler for data type "
								+ dm.getMIMEType().toString());
						registry.addDemangler(dm);
					}
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
			throw new Exception(e);
		}

		IPreferenceStore store = GenericNodesPlugin.getDefault()
				.getPreferenceStore();
		FileStash
				.getInstance()
				.setStashDirectory(
						store.getString(GKNPreferenceInitializer.PREF_FILE_STASH_LOCATION));

	}

	/**
	 * This method is called when the plug-in is stopped.
	 * 
	 * @param context
	 *            The OSGI bundle context
	 * @throws Exception
	 *             If this plugin could not be stopped
	 */
	@Override
	public void stop(final BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

	/**
	 * Returns the shared instance.
	 * 
	 * @return Singleton instance of the Plugin
	 */
	public static GenericNodesPlugin getDefault() {
		return plugin;
	}

}
