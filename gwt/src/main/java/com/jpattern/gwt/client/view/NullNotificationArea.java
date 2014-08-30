package com.jpattern.gwt.client.view;

import java.util.List;

import com.jpattern.shared.result.IErrorMessage;

/**
 * A passive implementation of the IErrorArea which doesn't do nothing.
 * @author Francesco Cina'
 *
 */
public class NullNotificationArea implements INotificationArea {

	@Override
	public void addErrorMessage(IErrorMessage errorMessage) {
	}

	@Override
	public void addErrorMessages(List<IErrorMessage> errorMessages) {
	}

	@Override
	public void clear() {
	}

	@Override
	public void operationStart() {
	}

	@Override
	public void operationFinished() {
	}

}
