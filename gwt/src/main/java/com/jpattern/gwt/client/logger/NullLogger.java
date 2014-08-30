package com.jpattern.gwt.client.logger;

/**
 * 
 * @author Francesco Cina'
 *
 * 12 Apr 2011
 */
public class NullLogger implements ILogger {

	@Override
	public void trace(String methodName, String message) {
	}

	@Override
	public void debug(String methodName, String message) {
	}

	@Override
	public void info(String methodName, String message) {
	}

	@Override
	public void warn(String methodName, String message) {
	}

	@Override
	public void error(String methodName, String message, Throwable e) {
	}

}
