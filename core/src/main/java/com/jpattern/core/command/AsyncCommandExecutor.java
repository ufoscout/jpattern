package com.jpattern.core.command;


/**
 * 
 * @author Francesco Cina'
 *
 * 27/feb/2011
 * 
 * This implementation of IAsyncCommandPool is a pool which simply
 * starts every new command in a new Thread.
 * No controls exist on the total number of Threads running.
 */
public class AsyncCommandExecutor implements ICommandExecutor {

	@Override
	public void execute(ACommand<?> command, ACommandResult commandResult) {
		new Thread(new AsyncExecCommandWrapper(command, commandResult)).start();
	}

	@Override
	public void rollback(ACommand<?> command, ACommandResult commandResult) {
		new Thread(new AsyncRollbackCommandWrapper(command, commandResult)).start();
	}
	
}
