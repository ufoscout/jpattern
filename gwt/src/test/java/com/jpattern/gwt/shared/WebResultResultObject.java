package com.jpattern.gwt.shared;

import com.jpattern.gwt.client.command.CommandResult;

/**
 * 
 * @author Francesco Cina'
 *
 * 11/mag/2011
 */
public class WebResultResultObject extends CommandResult implements IWebResultResultObject {

	private static final long serialVersionUID = 1L;
	private final IResultObject returnedObject;

	public WebResultResultObject(IResultObject resultObject) {
		this.returnedObject = resultObject;
		
	}
	@Override
	public IResultObject getReturnedObject() {
		return returnedObject;
	}

}
