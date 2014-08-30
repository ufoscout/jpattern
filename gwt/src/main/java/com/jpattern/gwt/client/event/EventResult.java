package com.jpattern.gwt.client.event;

import java.util.List;

import com.jpattern.gwt.client.command.ICommandResult;
import com.jpattern.shared.result.IErrorMessage;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/mag/2011
 */
public class EventResult<T> implements IEventResult<T> {

	private static final long serialVersionUID = 1L;
	private final T returnedObject;
	private final ICommandResult commandResult;

	public EventResult(ICommandResult commandResult, T resultObject) {
		this.commandResult = commandResult;
		this.returnedObject = resultObject;
	}
	
	@Override
	public List<IErrorMessage> getErrorMessages() {
		return commandResult.getErrorMessages();
	}

	@Override
	public T getReturnedObject() {
		return returnedObject;
	}

	@Override
	public boolean isValid() {
		return commandResult.getErrorMessages().isEmpty();
	}

	@Override
	public int getResponseCode() {
		return commandResult.getResponseCode();
	}

	@Override
	public void setResponseCode(int responseCode) {
		commandResult.setResponseCode(responseCode);		
	}

}
