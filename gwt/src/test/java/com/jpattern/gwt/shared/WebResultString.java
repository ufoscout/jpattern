package com.jpattern.gwt.shared;

import com.jpattern.gwt.client.command.CommandResult;

/**
 * 
 * @author Francesco Cina'
 *
 * 07/set/2011
 */
public class WebResultString  extends CommandResult implements IWebResultString {

	private static final long serialVersionUID = 1L;
	private final String returnedObject;

	public WebResultString(String returnedObject) {
		this.returnedObject = returnedObject;
	}
	
	@Override
	public String getReturnedObject() {
		return returnedObject;
	}

}
