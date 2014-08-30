package com.jpattern.core.command;

import com.jpattern.shared.result.IResult;

/**
 * This strategy is used by default.
 * With this strategy the command in the position (i) is executed only if all the (i-1) precedent commands
 * execute without errors.
 * If a command generates errors the rollback is called on all the executed elements of the chain in reverse order of their execution.
 * 
 * @author Francesco Cina'
 *
 * 11/set/2011
 */
public class ConditionalCommandChainStrategy implements ICommandChainStrategy {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean executeNext(IResult result) {
		return result.getErrorMessages().isEmpty();
	}

	@Override
	public boolean executeRollback() {
		return true;
	}

	@Override
	public void doExec(ACommand<?> command, ACommandResult commandResult, ICommandExecutor commandExecutor) {
		command.doExec(commandResult);		
	}

	@Override
	public void doRollback(ACommand<?> command, ACommandResult commandResult, ICommandExecutor commandExecutor) {
		command.doRollback(commandResult);	
	}

}
