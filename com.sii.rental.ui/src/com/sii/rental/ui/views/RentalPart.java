
package com.sii.rental.ui.views;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;

public class RentalPart {

	private Label rantedObjectLabel;
	private Label loueALabel;
	private Label customerName;
	private Group grpDatesDeLocation;
	private Label auLabel;
	private Label dateDebut;
	private Label dateFin;
	private Label duLabel;

	@PostConstruct
	public void createContent(Composite parent, RentalAgency rentalAgency) {
		parent.setLayout(new GridLayout(1, false));
		
		Group grpInformations = new Group(parent, SWT.NONE);
		GridData gd_grpInformations = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_grpInformations.heightHint = 67;
		gd_grpInformations.widthHint = 181;
		grpInformations.setLayoutData(gd_grpInformations);
		grpInformations.setText("Informations");
		grpInformations.setLayout(new GridLayout(2, false));
		
		rantedObjectLabel = new Label(grpInformations, SWT.NONE);
		rantedObjectLabel.setText("Perceuse electrique");
		new Label(grpInformations, SWT.NONE);
		
		loueALabel = new Label(grpInformations, SWT.NONE);
		loueALabel.setText("Lou\u00E9 \u00E0 :");
		
		customerName = new Label(grpInformations, SWT.NONE);
		customerName.setText("John Galt");
		
		grpDatesDeLocation = new Group(parent, SWT.NONE);
		GridData gd_grpDatesDeLocation = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_grpDatesDeLocation.widthHint = 182;
		grpDatesDeLocation.setLayoutData(gd_grpDatesDeLocation);
		grpDatesDeLocation.setText("Dates de location");
		grpDatesDeLocation.setLayout(new GridLayout(2, false));
		
		auLabel = new Label(grpDatesDeLocation, SWT.NONE);
		auLabel.setText("au :");
		
		dateDebut = new Label(grpDatesDeLocation, SWT.NONE);
		dateDebut.setText("15/03/2011");
		
		duLabel = new Label(grpDatesDeLocation, SWT.NONE);
		GridData gd_duLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_duLabel.widthHint = 53;
		duLabel.setLayoutData(gd_duLabel);
		duLabel.setSize(20, 15);
		duLabel.setText("du :");
		
		dateFin = new Label(grpDatesDeLocation, SWT.NONE);
		GridData gd_finDebut = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_finDebut.widthHint = 112;
		dateFin.setLayoutData(gd_finDebut);
		dateFin.setText("22/03/2011");
		
		setRental(rentalAgency.getRentals().get(0));
	}
	
	public void setRental(Rental r) {
		rantedObjectLabel.setText(r.getRentedObject().getName());
		customerName.setText(r.getCustomer().getDisplayName());
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateDebut.setText(dateFormat.format(r.getStartDate()));
		dateFin.setText(dateFormat.format(r.getEndDate()));
	}

	@Focus
	public void onFocus() {
		
	}

	@Inject @Optional
	public void receiveSelection(@Named(IServiceConstants.ACTIVE_SELECTION) Rental r) {
		if (r != null) {
			setRental(r);
		}
	}
}