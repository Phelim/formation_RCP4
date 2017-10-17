
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
	private Label duLabel;
	private Label dateDebut;
	private Label dateFin;
	private Label auLabel;

	@PostConstruct
	public void createContent(Composite parent, RentalAgency rentalAgency) {
		parent.setLayout(new GridLayout(1, false));
		
		Group grpInformations = new Group(parent, SWT.NONE);
		GridData gd_grpInformations = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_grpInformations.minimumWidth = 200;
		gd_grpInformations.heightHint = 50;
		gd_grpInformations.widthHint = 200;
		grpInformations.setLayoutData(gd_grpInformations);
		grpInformations.setText("Informations");
		grpInformations.setLayout(new GridLayout(2, false));
		
		rantedObjectLabel = new Label(grpInformations, SWT.NONE);
		GridData gd_rantedObjectLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1);
		gd_rantedObjectLabel.widthHint = 140;
		rantedObjectLabel.setLayoutData(gd_rantedObjectLabel);
		
		loueALabel = new Label(grpInformations, SWT.NONE);
		loueALabel.setText("Lou\u00E9 \u00E0 :");
		
		customerName = new Label(grpInformations, SWT.NONE);
		GridData gd_customerName = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_customerName.minimumWidth = 120;
		gd_customerName.widthHint = 120;
		customerName.setLayoutData(gd_customerName);
		
		grpDatesDeLocation = new Group(parent, SWT.NONE);
		GridData gd_grpDatesDeLocation = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_grpDatesDeLocation.minimumWidth = 200;
		gd_grpDatesDeLocation.widthHint = 200;
		grpDatesDeLocation.setLayoutData(gd_grpDatesDeLocation);
		grpDatesDeLocation.setText("Dates de location");
		grpDatesDeLocation.setLayout(new GridLayout(2, false));
		
		duLabel = new Label(grpDatesDeLocation, SWT.NONE);
		duLabel.setText("du :");
		
		dateDebut = new Label(grpDatesDeLocation, SWT.NONE);
		GridData gd_dateDebut = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_dateDebut.widthHint = 112;
		dateDebut.setLayoutData(gd_dateDebut);
		
		auLabel = new Label(grpDatesDeLocation, SWT.NONE);
		GridData gd_auLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_auLabel.widthHint = 53;
		auLabel.setLayoutData(gd_auLabel);
		auLabel.setSize(20, 15);
		auLabel.setText("au :");
		
		dateFin = new Label(grpDatesDeLocation, SWT.NONE);
		GridData gd_dateFin = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_dateFin.widthHint = 112;
		dateFin.setLayoutData(gd_dateFin);
	}
	
	public void setRental(Rental r) {
		if (r != null) {
			rantedObjectLabel.setText(r.getRentedObject().getName());
			customerName.setText(r.getCustomer().getDisplayName());
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			dateDebut.setText(dateFormat.format(r.getStartDate()));
			dateFin.setText(dateFormat.format(r.getEndDate()));
		}
	}

	@Focus
	public void onFocus() {
		
	}

	@Inject @Optional
	public void receiveSelection(@Named(IServiceConstants.ACTIVE_SELECTION) Rental r) {
		setRental(r);
	}
}