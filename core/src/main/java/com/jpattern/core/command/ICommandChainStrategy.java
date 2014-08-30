package com.jpattern.core.command;

import java.io.Serializable;

import com.jpattern.shared.result.IResult;

/**
 * 
 * A strategy to decide how to execute or rollback the commands in the chain.
 * 
 * @author Francesco Cina'
 *
 * 11/set/2011
 */
public interface ICommandChainStrategy extends Serializable {

	boolean executeNext(IResult result);
	
	boolean executeRollback();
	
	void doExec(ACommand<?> command, ACommandResult commandResult, ICommandExecutor commandExecutor);
	
	void doRollback(ACommand<?> command, ACommandResult commandResult, ICommandExecutor commandExecutor);
	
}
