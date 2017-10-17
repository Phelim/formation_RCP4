
package com.sii.rental.ui.views;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import com.opcoach.training.rental.RentalAgency;

public class RentalTreePart {

	@PostConstruct
	public void postConstruct(Composite parent, RentalAgency rentalAgency, IEclipseContext ctx) {
		TreeViewer tv = new TreeViewer(parent);
		
		RentalProvider rentalProvider = ContextInjectionFactory.make(RentalProvider.class, ctx);
		tv.setContentProvider(rentalProvider);
		tv.setLabelProvider(rentalProvider);
		
		tv.setInput(Arrays.asList(rentalAgency));
	}

}