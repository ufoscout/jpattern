package com.jpattern.core.command;

import com.jpattern.logger.ILogger;
import com.jpattern.shared.result.ErrorMessage;

/**
 * 
 * @author Francesco Cina'
 *
 */
public class MockCommandWithProviderExt extends ACommand<ProviderExt> {

	private final boolean throwException;
	private final boolean generateError;
	private boolean executed = false;
	
	public boolean isExecuted() {
		return executed;
	}

	public MockCommandWithProviderExt (boolean throwExeception, boolean generateError) {
		this.throwException = throwExeception;
		this.generateError = generateError;
	}
	
	@Override
	protected void execute(ACommandResult commandResult) {
		ILogger logger = getProvider().getLoggerService().logger(this.getClass());
		logger.debug("exec", "start");
		executed = true;
		if (generateError) {
			logger.debug("exec", "generate error");
			commandResult.getErrorMessages().add(new ErrorMessage("mockcommand", "error"));
		}
		if (throwException) {
			logger.debug("exec", "generate exception");
			throw new NullPointerException(); 
		}
	}


	@Override
	protected void rollback(ACommandResult commandResult) {
		
	}
}
