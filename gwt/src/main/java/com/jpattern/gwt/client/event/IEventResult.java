package com.jpattern.gwt.client.event;

import com.jpattern.gwt.client.command.ICommandResult;
import com.jpattern.shared.result.facade.ICommandFacadeResult;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/mag/2011
 */
public interface IEventResult<T> extends ICommandFacadeResult<T>, ICommandResult {

	boolean isValid();
	
}
