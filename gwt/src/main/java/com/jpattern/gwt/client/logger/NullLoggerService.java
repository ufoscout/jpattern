package com.jpattern.gwt.client.logger;

/**
 * 
 * @author Francesco Cina'
 *
 * 12 Apr 2011
 */
public class NullLoggerService extends ALoggerService {

	@Override
	public ILogger getLogger(Class<?> aClass) {
		return new NullLogger();
	}

}
