package com.jpattern.gwt.client.command;

import com.jpattern.shared.result.IResult;

/**
 * 
 * @author Francesco Cina'
 *
 * Jan 23, 2012
 */
public interface ICommandResult extends IResult {

	int getResponseCode();
	
	void setResponseCode(int responseCode);
}
