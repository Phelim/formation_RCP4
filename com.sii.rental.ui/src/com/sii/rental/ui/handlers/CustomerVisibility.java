 
package com.sii.rental.ui.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Evaluate;
import org.eclipse.e4.ui.services.IServiceConstants;

import com.opcoach.training.rental.Customer;

public class CustomerVisibility {
	
	@Evaluate
	public boolean evaluate(@Named(IServiceConstants.ACTIVE_SELECTION) Object o) {
		return o instanceof Customer;
	}
}
