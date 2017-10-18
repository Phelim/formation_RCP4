 
package com.sii.rental.eap.menus;

import java.util.List;

import javax.inject.Named;

import org.eclipse.e4.ui.di.AboutToShow;
import org.eclipse.e4.ui.model.application.ui.MImperativeExpression;
import org.eclipse.e4.ui.model.application.ui.menu.MDirectMenuItem;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuElement;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EModelService;

import com.opcoach.training.rental.Customer;

public class RentalMenuContribution {
	
	@AboutToShow
	public void aboutToShow(List<MMenuElement> items, @Named(IServiceConstants.ACTIVE_SELECTION) Customer c, EModelService modelService) {
		String bc = "bundleclass://com.sii.rental.eap/com.sii.rental.eap.handlers.CopyCustomerHandler";
		String bundle = "platform:/plugin/com.sii.rental.eap.application";
		
		MDirectMenuItem directItem = modelService.createModelElement(MDirectMenuItem.class);
		directItem.setLabel("Copy customer " + ((Customer) c).getDisplayName());
		directItem.setContributionURI(bc);
		directItem.setContributorURI(bundle);
			
		MImperativeExpression exp = modelService.createModelElement(MImperativeExpression.class);
		exp.setContributionURI("bundleclass://com.sii.rental.ui/com.sii.rental.ui.handlers.CustomerVisibility");
		directItem.setVisibleWhen(exp);

		items.add(directItem);
	}

	@AboutToShow
	public void aboutToShow(List<MMenuElement> items, @Named(IServiceConstants.ACTIVE_SELECTION) Object o, EModelService modelService) {
		
	}
}