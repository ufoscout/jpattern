package com.jpattern.core.command;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.jpattern.logger.ILogger;
import com.jpattern.service.log.ILoggerService;

/**
 * 
 * @author Francesco Cina'
 *
 * 27/feb/2011
 * 
 * This implementation of IAsyncCommandPool is a pool which manage
 * a queue of runnable objects and runs a prefixed maximum of Thread
 * at the same time.
 */
public class PoolAsyncCommandExecutor implements ICommandExecutor {
	
	private final BlockingQueue<Runnable> commandsQueue;
	private final ExecutorService executorService;
	private boolean started = false;
	private ILogger logger;
	private final String name;

	public PoolAsyncCommandExecutor(String name, int maxWaitingCommands, int maxConcurrentThreads, ILoggerService loggerService) {
		this.name = name;
		logger = loggerService.logger(this.getClass());
		commandsQueue = new ArrayBlockingQueue<Runnable>(maxWaitingCommands, true);
		executorService = Executors.newFixedThreadPool(maxConcurrentThreads);
		logger.info("init", getClass().getSimpleName() + " '" + name + "' initialized. Max queue lenght: " + maxWaitingCommands + ". Max concurrent Thread: " + maxConcurrentThreads + ".");
	}

	@Override
	public void execute(ACommand<?> command, ACommandResult commandResult) {
		try {
			commandsQueue.put(new AsyncExecCommandWrapper(command, commandResult));
			logger.debug("execute","New command added to pool for execution. Pool queue lenght: " + commandsQueue.size());
		} catch (InterruptedException e) {
			logger.error("execute", "", e);
		}
		
	}
	
	@Override
	public void rollback(ACommand<?> command, ACommandResult commandResult) {
		try {
			commandsQueue.put(new AsyncRollbackCommandWrapper(command, commandResult));
			logger.debug("rollback","New command added to pool for rollback. Pool queue lenght: " + commandsQueue.size());
		} catch (InterruptedException e) {
			logger.error("rollback", "", e);
		}
	}
	
	protected synchronized void stopPool() {
		started=false;
		logger.info("stopPool", getClass().getSimpleName() + " '" + name + "' stopped.");
	}
	
	protected synchronized void startPool() {
		if (!started) {
			started=true;
			logger.info("startPool", getClass().getSimpleName() + " '" + name + "' started");
			new Thread(new Runnable() {
				public void run() {
					while(started) {
						try {
							executorService.execute(commandsQueue.take());
							logger.debug("startPool", "new command in execution. Commands in queue: " + commandsQueue.size());
						} catch (Exception e) {
							logger.error("startPool", "error during pooled command execution", e);
						}
					}
				}
			}).start();
		}
	}

}
