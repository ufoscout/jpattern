package com.jpattern.core.command;

import com.jpattern.logger.ILogger;

/**
 * Thrown Exception Strategy, the exception thrown
 * @author cinafr
 *
 */
public class ThrownOnExceptionStrategy implements IOnExceptionStrategy {

	@Override
	public void onException(RuntimeException e, ILogger logger, ACommandResult commandResult, String errorMassage) {
		throw e;
	}

}
