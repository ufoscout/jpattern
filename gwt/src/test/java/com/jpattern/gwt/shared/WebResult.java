package com.jpattern.gwt.shared;

import java.util.List;

import com.jpattern.shared.result.IErrorMessage;
import com.jpattern.shared.result.IResult;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/mag/2011
 */
public class WebResult implements IWebResultObject {

	private static final long serialVersionUID = 1L;
	private final Object returnedObject;
	private final IResult result;

	public WebResult(IResult result, Object returnedObject) {
		this.result = result;
		this.returnedObject = returnedObject;
	}
	
	@Override
	public List<IErrorMessage> getErrorMessages() {
		return result.getErrorMessages();
	}

	@Override
	public Object getReturnedObject() {
		return returnedObject;
	}

}
