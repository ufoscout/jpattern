package com.jpattern.rest.command;

import com.jpattern.shared.command.IBaseCommand;
import com.jpattern.logger.ILoggerFactory;

/**
 * 
 * @author Francesco Cina
 *
 * 15/giu/2011
 */
public interface IRestCommand extends IBaseCommand {

	void logger(ILoggerFactory loggerFactory);
	
}
