package com.jpattern.gwt.client.logger;

/**
 * 
 * @author Francesco Cina'
 *
 * 12 Apr 2011
 */
public class SysoutLoggerService extends ALoggerService {
	
	public static String NAME = "SysoutLoggerService"; 

	@Override
	public ILogger getLogger(Class<?> aClass) {
		return new SysoutLogger(aClass.getName());
	}

}
