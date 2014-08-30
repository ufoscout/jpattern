package com.jpattern.gwt.client.serializer;

import java.util.ArrayList;
import java.util.List;

import com.jpattern.shared.result.IErrorMessage;
import com.jpattern.shared.result.facade.ICommandFacadeResult;

/**
 * 
 * @author cinafr
 *
 * @param <E>
 */
public abstract class ASerializableCommandFacadeResult<E> implements ICommandFacadeResult<E> {

	private static final long serialVersionUID = 1L;
	private List<IErrorMessage> errorMessages = new ArrayList<IErrorMessage>();
	
	@Override
	public List<IErrorMessage> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(List<IErrorMessage> errorMessages) {
		this.errorMessages = errorMessages;
	}

}
