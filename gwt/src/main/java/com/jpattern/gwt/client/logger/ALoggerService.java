package com.jpattern.gwt.client.logger;

import com.jpattern.gwt.client.AService;

/**
 * 
 * @author Francesco Cina'
 *
 * 12 Apr 2011
 */
public abstract class ALoggerService extends AService {
	
	public abstract ILogger getLogger(Class<?> aClass);

}
