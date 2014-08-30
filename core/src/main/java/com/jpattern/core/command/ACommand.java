package com.jpattern.core.command;

import com.jpattern.core.IProvider;
import com.jpattern.core.exception.NullProviderException;
import com.jpattern.logger.ILogger;
import com.jpattern.logger.SystemOutLoggerFactory;

/**
 * 
 * @author Francesco Cina'
 *
 * 11/set/2011
 */
public abstract class ACommand<T extends IProvider> {
	
	private ICommandExecutor commandExecutor;
	private IOnExceptionStrategy onExceptionStrategy;
	private T provider;
	private ILogger logger = null;
	private boolean executed = false;
	private boolean rolledback = false;

	/**
     * This method launch the execution of the command (or chain of commands) using the default
     * default Executor and catching every runtime exception.
     * This command is the same of:
     * exec(provider, true);
     * @return the result of the execution
     */
	public final ACommandResult exec(T provider) {
		return exec(provider, new DefaultCommandExecutor());
	}
	
    /**
     * This method launch the execution of the command (or chain of commands).
     * Every command in the chain will be managed by an ICommandExecutor object.
     * This command is the same of:
     * exec(commandExecutor, true);
     * @param aCommandExecutor the pool in which the command will runs
     * @return the result of the execution
     */
	public final ACommandResult exec(T provider, ICommandExecutor commandExecutor) {
		visit(provider);
		return exec( commandExecutor, new CommandResult());
	}
	
    /**
     * This method launch the rollback of the command execution (or chain of commands) using the default
     * default Executor and catching every runtime exception.
     * The rollback is effectively performed only if the command has been executed with a positive result, otherwise
     * the command is intended as "not executed" then no rollback will be performed. 
     * This command is the same of:
     * rollback(provider, true);
     * @return the result of the rollback
     */
	public final ACommandResult rollback(T provider) {
		return rollback(provider, new DefaultCommandExecutor());
	}
	
    /**
     * This method launch the rollback of the command execution (or chain of commands) using a custom command executor.
     * The rollback is effectively performed only if the command has been executed with a positive result, otherwise
     * the command is intended as "not executed" then no rollback will be performed. 
     * This command is the same of:
     * rollback(provider, commandExecutor, true);
     * @return the result of the rollback
     */
	public final ACommandResult rollback(T provider, ICommandExecutor commandExecutor) {
		visit(provider);
		return rollback(commandExecutor, new CommandResult());
	}
	
	
	void visit(T provider) {
		this.provider = provider;
	}

	protected final ACommandResult exec(ICommandExecutor commandExecutor, ACommandResult commandResult) {
		this.commandExecutor = commandExecutor;
		commandResult.setExecutionStart(this);
		getCommandExecutor().execute(this, commandResult);
		return commandResult;
	}
	
	protected final ACommandResult rollback(ICommandExecutor commandExecutor, ACommandResult commandResult) {
		this.commandExecutor = commandExecutor;
		commandResult.setExecutionStart(this);
		getCommandExecutor().rollback(this, commandResult);
		return commandResult;
	}
	
	protected final ICommandExecutor getCommandExecutor() {
		if (commandExecutor==null) {
			commandExecutor = new DefaultCommandExecutor();
		}
		return commandExecutor;
	}
	
	protected final T getProvider() {
		if (provider==null) {
			throw new NullProviderException();
		}
		return provider;
	}
	
	protected final ILogger getLogger() {
		if (logger == null) {
			if (provider == null) {
				logger = new SystemOutLoggerFactory().logger(getClass());
			} else {
				logger = getProvider().getLoggerService().logger(this.getClass());
			}
		}
		return logger;
	}
	
	public void setOnExceptionStrategy(IOnExceptionStrategy onExceptionStrategy) {
		this.onExceptionStrategy = onExceptionStrategy;
	}

	public IOnExceptionStrategy getOnExceptionStrategy() {
		if (onExceptionStrategy == null) {
			onExceptionStrategy = new CatchOnExceptionStrategy();
		}
		return onExceptionStrategy;
	}
	
	protected final void doExec(ACommandResult commandResult) {
		try {
			int errorSize = commandResult.getErrorMessages().size();
			executed = false;
			rolledback = false;
			execute(commandResult);
			executed = commandResult.getErrorMessages().size() == errorSize;
		} catch (RuntimeException e) {
			getOnExceptionStrategy().onException(e, getLogger(), commandResult, "RuntimeException thrown");
		} finally {
			try {
				postExecute(commandResult);
			} finally {
				commandResult.setExecutionEnd(this);
			}
		}
	}
	
	void postExecute(ACommandResult commandResult) {
	}
	
	void postRollback(ACommandResult commandResult) {
	}

	protected final void doRollback(ACommandResult commandResult) {
		
			try {
				if (executed && !rolledback) {
					rollback(commandResult);
					rolledback = true;
				}
			} catch (RuntimeException e) {
				getOnExceptionStrategy().onException(e, getLogger(), commandResult, "RuntimeException thrown while rollbacking");
			} finally {
				try {
					postRollback(commandResult);
				} finally {
					commandResult.setExecutionEnd(this);
				}
			}
	}
	
	protected abstract void execute(ACommandResult commandResult);

	protected abstract void rollback(ACommandResult commandResult);

	void setExecuted(boolean executed) {
		this.executed = executed;
	}

	boolean isExecuted() {
		return executed;
	}

	void setRolledback(boolean rolledback) {
		this.rolledback = rolledback;
	}

	boolean isRolledback() {
		return rolledback;
	}
	
}
