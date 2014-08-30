package com.jpattern.core.command;

import com.jpattern.logger.ILogger;

/**
 * 
 * @author cinafr
 * 
 * Define the behaviour of the command when a runtime exception is thrown
 *
 */
public interface IOnExceptionStrategy {

	public void onException(RuntimeException e, ILogger iLogger, ACommandResult commandResult, String errorMessage);
	
}
