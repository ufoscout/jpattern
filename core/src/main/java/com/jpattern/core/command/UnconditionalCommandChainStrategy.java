package com.jpattern.core.command;

import com.jpattern.shared.result.IResult;

/**
 * 
 * When this strategy is used every command is intended like a separated entity.
 * The entire chain of command is executed even if some commands end with errors.
 * Due to the independence of the commands, when this strategy is used and an error is thrown by a command
 * the chain is not rollbacked. 
 * 
 * @author Francesco Cina'
 *
 * 11/set/2011
 */
public class UnconditionalCommandChainStrategy implements ICommandChainStrategy {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean executeNext(IResult result) {
		return true;
	}

	@Override
	public boolean executeRollback() {
		return false;
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
