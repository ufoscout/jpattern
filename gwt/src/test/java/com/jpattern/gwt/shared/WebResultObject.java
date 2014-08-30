package com.jpattern.gwt.shared;

import com.jpattern.gwt.client.command.CommandResult;

/**
 * 
 * @author Francesco Cina
 *
 * 14/lug/2011
 */
public class WebResultObject extends CommandResult implements IWebResultObject {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getReturnedObject() {
		return new Object();
	}


}
