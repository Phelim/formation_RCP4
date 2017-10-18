package com.sii.rental.ui;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class RentalProvider extends LabelProvider 
		implements ITreeContentProvider, IColorProvider, RentalUIConstants {

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof List<?>) {
			return ((List) inputElement).toArray();
		} 
		return null;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof RentalAgency) {
			RentalAgency r = (RentalAgency)parentElement;
			return new Node[] { new Node(Node.CUSTOMERS, r),
					new Node(Node.RENTALS,r),
					new Node(Node.OBJECTS,r) };
		} else if (parentElement instanceof Node) {
			Node r = (Node)parentElement;
			return r.getChildren();
		} 
		return null;
	}  

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	
	public boolean hasChildren(Object element) {
		if (element instanceof Customer || element instanceof RentalObject || element instanceof Rental) {
			return false;
		} 
		return true;
	}
	
	
	@Override
	public String getText(Object element) {
		if (element instanceof Customer) {
			return ((Customer) element).getDisplayName();
		} else if (element instanceof RentalObject) {
			return ((RentalObject) element).getName();
		} else if (element instanceof RentalAgency) {
			return ((RentalAgency) element).getName();
		} 
		return super.getText(element);
	}
	
	class Node {
		private static final String OBJECTS = "Objects";
		private static final String RENTALS = "Rentals";
		private static final String CUSTOMERS = "Customers";
		private String label;
		private RentalAgency agency;
		
		public Node(String label, RentalAgency agency) {
			super();
			this.label = label;
			this.agency = agency;
		}
		
		public Object[] getChildren() {
			if (CUSTOMERS.equals(label)) {
				return agency.getCustomers().toArray();
			} else if (RENTALS.equals(label)) {
				return agency.getRentals().toArray();
			} else if (OBJECTS.equals(label)) {
				return agency.getObjectsToRent().toArray();
			}
			return null;
		}

		public RentalAgency getAgency() {
			return agency;
		}

		@Override
		public String toString() {
			return label;
		}

	}

	@Override
	public Color getForeground(Object element) {
		if (element instanceof Customer) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_RED);
		} else if (element instanceof RentalObject) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_RED);
		} else if (element instanceof RentalAgency) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN);
		} else if (element instanceof Node) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
		}  else if (element instanceof Rental) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_RED);
		} 
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		return Display.getCurrent().getSystemColor(SWT.COLOR_WHITE);
	}

	@Inject @Named(RENTAL_UI_IMG_REGISTRY)
	private ImageRegistry registry;
	
	@Override
	public Image getImage(Object element) {
		if (element instanceof Customer) {
			return registry.get(IMG_CUSTOMER);
		} else if (element instanceof Rental) {
			return registry.get(IMG_RENTAL);
		} else if (element instanceof RentalObject) {
			return registry.get(IMG_RENTAL_OBJECT);
		}  else if (element instanceof RentalAgency) {
			return registry.get(IMG_AGENCY);
		} 
		return null;
	}

}
