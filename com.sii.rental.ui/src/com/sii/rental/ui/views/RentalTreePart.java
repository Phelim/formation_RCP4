
package com.sii.rental.ui.views;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import com.opcoach.training.rental.RentalAgency;

public class RentalTreePart {

	@PostConstruct
	public void postConstruct(Composite parent, RentalAgency rentalAgency) {
		TreeViewer tv = new TreeViewer(parent);
		
		RentalProvider rentalProvider = new RentalProvider();
		tv.setContentProvider(rentalProvider);
		tv.setLabelProvider(rentalProvider);
		
		tv.setInput(Arrays.asList(rentalAgency));
	}

}