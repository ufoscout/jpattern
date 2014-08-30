package com.jpattern.gwt.client.command;

import com.jpattern.gwt.client.command.ACommand;
import com.jpattern.gwt.client.logger.ILogger;
import com.jpattern.shared.result.ErrorMessage;

/**
 * 
 * @author Francesco Cina'
 *
 */
public class MockCommand extends ACommand {

	private final boolean throwExeception;
	private final boolean generateError;
	private boolean executed = false;
	
	public boolean isExecuted() {
		return executed;
	}

	public MockCommand (boolean throwExeception, boolean generateError) {
		this.throwExeception = throwExeception;
		this.generateError = generateError;
	}
	
	@Override
	protected void exec(ICommandResult commandResult) {
		waitAsyncCallback();
		ILogger logger = getProvider().getLoggerService().getLogger(this.getClass());
		logger.debug("exec", "start");
		executed = true;
		if (generateError) {
			logger.debug("exec", "generate error");
			commandResult.getErrorMessages().add(new ErrorMessage("mockcommand", "error"));
		}
		if (throwExeception) {
			logger.debug("exec", "generate exception");
			throw new NullPointerException(); 
		}
		logger.debug("exec", "callback");
		callback(commandResult);
	}
}
