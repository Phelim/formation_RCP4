
package com.sii.rental.ui.views;


import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class RentalPart {

	@PostConstruct
	public void createContent(Composite parent) {
		Label l = new Label(parent, SWT.NONE);
		l.setText("Hello");
	}

	@Focus
	public void onFocus() {
		
	}
}