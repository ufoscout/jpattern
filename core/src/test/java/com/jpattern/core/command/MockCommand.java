package com.jpattern.core.command;

import com.jpattern.core.IProvider;
import com.jpattern.logger.ILogger;
import com.jpattern.shared.result.ErrorMessage;

/**
 * 
 * @author Francesco Cina'
 *
 */
public class MockCommand extends ACommand<IProvider> {

	private final boolean throwException;
	private final boolean generateError;
	private boolean executed = false;
	private boolean rolledBack = false;
	private final String name;
	
	public boolean isExecuted() {
		return executed;
	}

	public MockCommand (boolean throwExeception, boolean generateError) { 
		this("", throwExeception, generateError);
	}
	public MockCommand (String name, boolean throwExeception, boolean generateError) {
		this.name = name;
		this.throwException = throwExeception;
		this.generateError = generateError;
	}
	
	@Override
	protected void execute(ACommandResult commandResult) {
		ILogger logger = getProvider().getLoggerService().logger(this.getClass());
		logger.debug("exec", name + " -> start");
		executed = true;
		if (generateError) {
			logger.debug("exec", name + " -> generate error ");
			commandResult.getErrorMessages().add(new ErrorMessage("mockcommand", "error"));
		}
		if (throwException) {
			logger.debug("exec", name + " -> generate exception");
			throw new NullPointerException(); 
		}
	}


	@Override
	protected void rollback(ACommandResult commandResult) {
		getLogger().debug("exec", name + " -> rollback");
		rolledBack = true;
	}

	public boolean isRolledBack() {
		return rolledBack;
	}
}
