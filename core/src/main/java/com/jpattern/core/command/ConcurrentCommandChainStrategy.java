package com.jpattern.core.command;

import com.jpattern.shared.result.IResult;

/**
 * This Strategy is particularly powerful. This let the management of every single command of the chain
 * to the main chain executor.
 * This means that if an asynchronous executor is used then every command will be passed to the executor and every single command 
 * will be executed in a separated thread. In this case the commands are executed without waiting the execution end of the previous commands in the chain.
 * If a pool executor is used every single command will be put in the pool and then executed based on the pool strategy.
 * Due to the independence of the commands, when this strategy is used and an error is thrown by a command
 * the chain is not rollbacked. 
 *  
 * @author Francesco Cina'
 *
 * 11/set/2011
 */
public class ConcurrentCommandChainStrategy implements ICommandChainStrategy {

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
		command.exec(commandExecutor, commandResult);		
	}

	@Override
	public void doRollback(ACommand<?> command, ACommandResult commandResult, ICommandExecutor commandExecutor) {
		command.rollback(commandExecutor, commandResult);
	}

}
