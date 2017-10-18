 
package com.sii.rental.eap.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;

import com.opcoach.training.rental.Customer;

public class CopyCustomerHandler {
	
	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) Customer o, Display display, IEventBroker broker) {
		Clipboard clipboard = new Clipboard(display);
		String textData = o.getDisplayName();
		TextTransfer textTransfer = TextTransfer.getInstance();
		Transfer[] transfers = new Transfer[]{textTransfer};
		Object[] data = new Object[]{textData};
		clipboard.setContents(data, transfers);
		clipboard.dispose();
		
		broker.send("rental/copy", textData);
	}
	
	
		
}