 
package com.sii.rental.ui;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.widgets.Shell;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.opcoach.e4.preferences.ScopedPreferenceStore;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.helpers.RentalAgencyGenerator;

public class RentalUIAddon implements RentalUIConstants{
	
	@PostConstruct
	public void initializeRental(IEclipseContext ctx) {
		ctx.set(RentalAgency.class, RentalAgencyGenerator.createSampleAgency());
		ctx.set(RENTAL_UI_IMG_REGISTRY, getLocalImageRegistry());
		
		IPreferenceStore prefStore = new ScopedPreferenceStore(InstanceScope.INSTANCE, PLUGIN_ID);
		ctx.set(RENTAL_UI_PREF_STORE, prefStore);
	}

	/**
	 * A method to create and initialize an ImageRegistry
	 * @return a initialized ImageRegistry that can be put in the context
	 */
	ImageRegistry getLocalImageRegistry()
	{
		// Get the bundle using the universal method to get it from the current class
		Bundle b = FrameworkUtil.getBundle(getClass());  
		
		// Create a local image registry
		ImageRegistry reg = new ImageRegistry();

		// Then fill the values...
		reg.put(IMG_CUSTOMER, ImageDescriptor.createFromURL(b.getEntry(IMG_CUSTOMER)));
		reg.put(IMG_RENTAL, ImageDescriptor.createFromURL(b.getEntry(IMG_RENTAL)));
		reg.put(IMG_RENTAL_OBJECT, ImageDescriptor.createFromURL(b.getEntry(IMG_RENTAL_OBJECT)));
		reg.put(IMG_AGENCY, ImageDescriptor.createFromURL(b.getEntry(IMG_AGENCY)));
		reg.put(IMG_COLLAPSE_ALL, ImageDescriptor.createFromURL(b.getEntry(IMG_COLLAPSE_ALL)));
		reg.put(IMG_EXPAND_ALL, ImageDescriptor.createFromURL(b.getEntry(IMG_EXPAND_ALL)));
		
		return reg;
	}

	
	@Inject @Optional
	private void reactOnRentalCopy(@UIEventTopic("rental/copy") String copy, @Named(IServiceConstants.ACTIVE_SHELL) Shell shell) {
		MessageDialog.openInformation(shell, "Action utilisateur", "Copie du client " + copy);
	}
}