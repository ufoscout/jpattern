package com.jpattern.gwt.event;

import java.util.ArrayList;
import java.util.List;

import com.jpattern.gwt.client.view.INotificationArea;
import com.jpattern.shared.result.IErrorMessage;

/**
 * 
 * @author Francesco Cina'
 *
 */
public class MockErrorArea implements INotificationArea {
	
	private List<IErrorMessage> errorMessages = new ArrayList<IErrorMessage>();
	boolean operationFinished = false;
	boolean operationStarted = false;

	public List<IErrorMessage> getErrorMessages() {
		return errorMessages;
	}

	@Override
	public void addErrorMessage(IErrorMessage errorMessage) {
		if (errorMessage!=null) {
			errorMessages.add(errorMessage);
		}
	}

	@Override
	public void addErrorMessages(List<IErrorMessage> errorMessages) {
		if (errorMessages!=null) {
			this.errorMessages.addAll(errorMessages);
		}
	}

	@Override
	public void clear() {
		errorMessages.clear();
	}

	@Override
	public void operationStart() {
		operationStarted = true;
	}

	@Override
	public void operationFinished() {
		operationFinished = true;
	}
	
}
