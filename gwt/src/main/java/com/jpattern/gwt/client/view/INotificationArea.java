package com.jpattern.gwt.client.view;

import java.util.List;

import com.jpattern.shared.result.IErrorMessage;

/**
 * 
 * @author Francesco Cina'
 *
 */
public interface INotificationArea {
	
	void operationStart();
	
	void operationFinished();

	void addErrorMessage(IErrorMessage errorMessage);
	
	void addErrorMessages(List<IErrorMessage> errorMessages);
	
	void clear();
}
