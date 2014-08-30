package com.jpattern.core.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.jpattern.core.IProvider;
import com.jpattern.logger.ILogger;

/**
 * 
 * @author Francesco Cina'
 *
 * 11/set/2011
 */
public final class CommandChain<T extends IProvider> extends ACommand<T> {

	private List<ACommand<? super T>> commands = new ArrayList<ACommand<? super T>>();
	private final ICommandChainStrategy chainStrategy;

	public CommandChain() {
		this(new ConditionalCommandChainStrategy());
	}
	
	public CommandChain(ICommandChainStrategy chainStrategy) {
		this.chainStrategy = chainStrategy;
	}

	@Override
	protected void execute(ACommandResult commandChainResult) {
		Iterator<ACommand<? super T>> commandsIterator = commands.iterator();
		while (commandsIterator.hasNext() &&  chainStrategy.executeNext( commandChainResult )) {
			ACommand<? super T> command = null;
			try {
				command = commandsIterator.next();
				command.visit(getProvider());
				command.setOnExceptionStrategy(getOnExceptionStrategy());
				chainStrategy.doExec(command, commandChainResult, getCommandExecutor());
			} catch (RuntimeException e) {
				getOnExceptionStrategy().onException(e, getLogger(), commandChainResult, "RuntimeException thrown during command chain execution");
			} 
		}
		checkIfRollback(commandChainResult);
	}
	
	@Override
	final void postExecute(ACommandResult commandResult) {
		setExecuted(true);
	}
	
	@Override
	final void postRollback(ACommandResult commandResult) {
	}

	@Override
	protected void rollback(ACommandResult commandChainResult) {
			for (int i = (commands.size()-1); i>=0 ; i--) {
				ACommand<? super T> command = null;
				try {
					command = commands.get(i);
					command.visit(getProvider());
					command.setOnExceptionStrategy(getOnExceptionStrategy());
					chainStrategy.doRollback(command, commandChainResult, getCommandExecutor());
				} catch (RuntimeException e) {
					getOnExceptionStrategy().onException(e, getLogger(), commandChainResult, "RuntimeException thrown during command chain rollback");
				}
			}
		
	}
	
	private final void checkIfRollback(ACommandResult commandResult) {
		ILogger logger = getLogger();
		logger.debug("checkIfRollback", "Check if rollback the chain...");
//		logger.trace("checkIfRollback", "Error messages found: " + commandResult.getErrorMessages().size());
//		logger.trace("checkIfRollback", "chainStrategy.executeRollback(): " + chainStrategy.executeRollback());
		if (!commandResult.getErrorMessages().isEmpty() && chainStrategy.executeRollback()) {
			logger.debug("checkIfRollback", "Rollback needed!");
			for (int i = (commands.size()-1); i>=0 ; i--) {
				logger.trace("checkIfRollback", "Rolling back command in position " + i);
				commands.get(i).doRollback(commandResult);
			}
			setRolledback(true);
		}
	}
	
	public final void addCommand(ACommand<? super T> command) {
		commands.add(command);
	}
	
}
