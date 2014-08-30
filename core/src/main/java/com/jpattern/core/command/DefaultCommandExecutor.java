package com.jpattern.core.command;

/**
 * 
 * @author Francesco Cina'
 *
 * 11/set/2011
 */
public class DefaultCommandExecutor implements ICommandExecutor {

	@Override
	public void execute(ACommand<?> command, ACommandResult commandResult) {
		command.doExec(commandResult);
	}

	@Override
	public void rollback(ACommand<?> command, ACommandResult commandResult) {
		command.doRollback(commandResult);		
	}

}
