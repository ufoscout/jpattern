package com.jpattern.core.command;

/**
 * 
 * @author Francesco Cina'
 *
 * 11/set/2011
 */
public class AsyncRollbackCommandWrapper implements Runnable {

	private final ACommand<?> command;
	private final ACommandResult commandResult;

	AsyncRollbackCommandWrapper(ACommand<?> command, ACommandResult commandResult) {
		this.command = command;
		this.commandResult = commandResult;
	}
	
	@Override
	public void run() {
		command.doRollback( commandResult);		
	}

}
