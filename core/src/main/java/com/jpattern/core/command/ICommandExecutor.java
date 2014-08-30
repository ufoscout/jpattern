package com.jpattern.core.command;

/**
 * 
 * @author Francesco Cina'
 *
 * 11/set/2011
 */
public interface ICommandExecutor {
	
	void execute(ACommand<?> command, ACommandResult commandResult);
	
	void rollback(ACommand<?> command, ACommandResult commandResult);

}
