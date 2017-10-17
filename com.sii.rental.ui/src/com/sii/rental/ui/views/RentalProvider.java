package com.sii.rental.ui.views;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class RentalProvider extends LabelProvider implements ITreeContentProvider {

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
	
}
