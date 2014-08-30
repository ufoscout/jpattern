package com.jpattern.gwt.client.logger;

/**
 * 
 * @author Francesco Cina'
 *
 * 12 Apr 2011
 */
public class GwtLoggerService extends ALoggerService {
	
	public static String NAME = "GwtLoggerService"; 

	@Override
	public ILogger getLogger(Class<?> aClass) {
		return new GwtLogger(aClass.getName());
	}

}
