 
package com.sii.rental.ui.views;

import javax.annotation.PostConstruct;

import org.eclipse.e4.core.contexts.IEclipseContext;

import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.helpers.RentalAgencyGenerator;

public class RentalUIAddon {
	
	@PostConstruct
	public void initializeRental(IEclipseContext ctx) {
		ctx.set(RentalAgency.class, RentalAgencyGenerator.createSampleAgency());
	}


}
