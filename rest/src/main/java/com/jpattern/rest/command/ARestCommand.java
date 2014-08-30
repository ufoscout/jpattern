package com.jpattern.rest.command;

import com.jpattern.shared.command.ABaseCommand;
import com.jpattern.shared.command.IBaseCommand;
import com.jpattern.logger.ILogger;
import com.jpattern.logger.ILoggerFactory;
import com.jpattern.logger.NullLogger;

/**
 * 
 * @author Francesco Cina
 *
 * 15/giu/2011
 */
public abstract class ARestCommand extends ABaseCommand implements IRestCommand {

	private static final long serialVersionUID = 1L;
	private ILogger logger = new NullLogger();

	public ARestCommand(IBaseCommand previousCommand) {
		super(previousCommand);
	}

	@Override
	public void logger(ILoggerFactory loggerFactory) {
		logger = loggerFactory.logger(getClass());
	}

	protected ILogger getLogger(){
		return logger;
	}

}
