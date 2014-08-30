package com.jpattern.core.command;

import com.jpattern.logger.ILogger;
import com.jpattern.shared.result.ErrorMessage;

/**
 * Default Exception Strategy, the exception is catch and a new error message is generated in the command result
 * @author cinafr
 *
 */
public class CatchOnExceptionStrategy implements IOnExceptionStrategy {

	@Override
	public void onException(RuntimeException e, ILogger logger, ACommandResult commandResult, String errorMessage) {
		logger.error("onException", errorMessage, e);
		commandResult.addErrorMessage(new ErrorMessage(ICommandErrorsCostants.RUNTIME_EXEC_ERROR_NAME, e.getMessage()));		
	}

}
