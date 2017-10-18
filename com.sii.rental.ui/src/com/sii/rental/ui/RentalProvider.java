package com.sii.rental.ui;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.StringConverter;
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

	/** A local color registry to store the node colors */
	private ColorRegistry colorRegistry = new ColorRegistry();
	
	@Inject @Named(RENTAL_UI_PREF_STORE)
	private IPreferenceStore prefStore;

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

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((agency == null) ? 0 : agency.hashCode());
			result = prime * result + ((label == null) ? 0 : label.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (agency == null) {
				if (other.agency != null)
					return false;
			} else if (!agency.equals(other.agency))
				return false;
			if (label == null) {
				if (other.label != null)
					return false;
			} else if (!label.equals(other.label))
				return false;
			return true;
		}

		private RentalProvider getOuterType() {
			return RentalProvider.this;
		}
	}

	@Override
	public Color getForeground(Object element) {
		if (element instanceof Customer)
			return getPrefColor(PREF_CUSTOMER_COLOR);
		else if (element instanceof Rental)
			return getPrefColor(PREF_RENTAL_COLOR);
		else if (element instanceof RentalObject)
			return getPrefColor(PREF_RENTAL_OBJECT_COLOR);

		return null;
	}
	
	/**
	 * Get a color according to a key in the preference store
	 * 
	 * @param key
	 *            the preference key to get the rgb value
	 */
	private Color getPrefColor(String key)
	{
		String rgbKey = prefStore.getString(key);

		Color result = colorRegistry.get(rgbKey);
		if (result == null)
		{
			// Get value in pref store
			colorRegistry.put(rgbKey, StringConverter.asRGB(rgbKey));
			result = colorRegistry.get(rgbKey);
		}

		return result;

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
