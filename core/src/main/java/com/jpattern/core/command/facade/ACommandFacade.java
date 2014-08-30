package com.jpattern.core.command.facade;

import com.jpattern.core.IProvider;
import com.jpattern.logger.ILogger;
import com.jpattern.shared.result.facade.ICommandFacadeResult;

/**
 * 
 * @author Francesco Cina'
 *
 * 25 Feb 2011
 * 
 * This class is used as a entry point for the execution of a chain of commands.
 * 
 */
public abstract class ACommandFacade<E, T extends IProvider> {

	private T provider;
	private ILogger logger;

     public final synchronized ICommandFacadeResult<E> exec(T provider) {
    	this.provider = provider;
    	return execute(provider);
    }
    
    protected abstract ICommandFacadeResult<E> execute(T provider);

    protected synchronized ILogger getLogger () {
    	if (logger==null) {
    		logger = provider.getLoggerService().logger(getClass());
    	}
    	return logger;
    }
}
