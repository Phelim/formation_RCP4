package com.sii.rental.ui.prefs;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.graphics.RGB;

import com.opcoach.e4.preferences.ScopedPreferenceStore;
import com.sii.rental.ui.RentalUIConstants;

/**
 * @author sii
 *
 */
public class PrefInit extends AbstractPreferenceInitializer implements RentalUIConstants {

	/**
	 * 
	 */
	public PrefInit() {
		
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store;
		store = new ScopedPreferenceStore(InstanceScope.INSTANCE, PLUGIN_ID);
		
		store.setDefault(PREF_RENTAL_COLOR, StringConverter.asString(new RGB(0, 255, 0)));
		store.setDefault(PREF_RENTAL_OBJECT_COLOR, StringConverter.asString(new RGB(0, 255, 0)));
		store.setDefault(PREF_CUSTOMER_COLOR, StringConverter.asString(new RGB(0, 255, 0)));
	}

}
