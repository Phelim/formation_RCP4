
package com.sii.rental.ui.views;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.extensions.Preference;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import com.opcoach.training.rental.RentalAgency;
import com.sii.rental.ui.RentalProvider;
import com.sii.rental.ui.RentalUIConstants;

public class RentalTreePart implements RentalUIConstants {

	private TreeViewer tv;

	@PostConstruct
	public void postConstruct(Composite parent, RentalAgency rentalAgency, IEclipseContext ctx, EMenuService menuService) {
		tv = new TreeViewer(parent);
		
		RentalProvider rentalProvider = ContextInjectionFactory.make(RentalProvider.class, ctx);
		tv.setContentProvider(rentalProvider);
		tv.setLabelProvider(rentalProvider);
		
		tv.setInput(Arrays.asList(rentalAgency));
		
		provideSelection(tv);
		
		menuService.registerContextMenu(tv.getControl(), "com.sii.rental.eap.popupmenu.message");
	}
	
	@Inject
	private ESelectionService selectionService;
	
	private void provideSelection(TreeViewer tv) {
		tv.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection sel = (IStructuredSelection) event.getSelection();
				selectionService.setSelection(sel.size() == 1 ? sel.getFirstElement() : sel.toArray());
			}
		});
	}
	
	@Inject
	public void refresh(@Preference(value=PREF_RENTAL_COLOR) String c,
			@Preference(value=PREF_RENTAL_COLOR) String d,
			@Preference(value=PREF_RENTAL_COLOR) String e) {
		if (tv != null && !tv.getControl().isDisposed()) {
			tv.refresh();
		}
	}
}