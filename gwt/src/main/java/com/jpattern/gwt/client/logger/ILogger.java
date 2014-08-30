package com.jpattern.gwt.client.logger;

/**
 * 
 * @author Francesco Cina'
 *
 * 12 Apr 2011
 */
public interface ILogger {
	
	void trace(String methodName, String message);

	void debug(String methodName, String message);
	
	void info(String methodName, String message);
	
	void warn(String methodName, String message);
	
	void error(String methodName, String message, Throwable e);
	
}
