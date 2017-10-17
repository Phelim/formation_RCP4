package com.sii.rental.ui.views;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.RentalAgency;

public class RentalProvider extends LabelProvider implements ITreeContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof List<?>)
			return ((List) inputElement).toArray();
		return null;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof RentalAgency) {
			return ((RentalAgency)parentElement).getCustomers().toArray();
		} else {
			return null;
		}
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
		if (element instanceof RentalAgency) {
			return ((RentalAgency) element).getName();
		} else if (element instanceof Customer) {
			return ((Customer) element).getDisplayName();
		} 
		return null;
	}
}
