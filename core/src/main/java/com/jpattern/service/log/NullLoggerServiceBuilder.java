package com.jpattern.service.log;

import com.jpattern.core.IServiceBuilder;


/**
 * 
 * @author Francesco Cina'
 *
 * 6 May 2011
 */
public class NullLoggerServiceBuilder implements IServiceBuilder<ILoggerService> {

	@Override
	public ILoggerService buildService() {
		return new NullLoggerService();
	}

}
