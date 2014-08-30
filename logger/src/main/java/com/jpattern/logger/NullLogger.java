package com.jpattern.logger;

import java.io.Serializable;


/**
 * 
 * @author Francesco Cina'
 *
 * 21/giu/2010
 */
public class NullLogger implements ILogger, Serializable {

	private static final long serialVersionUID = 1L;
	private final ILoggerCallback loggerCallback;

	public NullLogger(ILoggerCallback loggerCallback) {
		this.loggerCallback = loggerCallback;

	}

	@Override
	public void debug(String method, String message) {
		loggerCallback.trace(method, message);
	}

	@Override
	public void error(String method, String message) {
		loggerCallback.error(method, message);
	}

	@Override
	public void error(String method, String message, Throwable exception) {
		loggerCallback.error(method, message, exception);
	}

	@Override
	public void info(String method, String message) {
		loggerCallback.info(method, message);
	}

	@Override
	public void trace(String method, String message) {
		loggerCallback.trace(method, message);
	}

	@Override
	public void warn(String method, String message) {
		loggerCallback.warn(method, message);
	}

	@Override
	public void debug(String message) {
		loggerCallback.trace( message);
	}

	@Override
	public void error(String message) {
		loggerCallback.error( message);
	}

	@Override
	public void error(String message, Throwable exception) {
		loggerCallback.error( message, exception);
	}

	@Override
	public void info(String message) {
		loggerCallback.info( message);
	}

	@Override
	public void trace( String message) {
		loggerCallback.trace( message);
	}

	@Override
	public void warn(String message) {
		loggerCallback.warn(message);
	}

}
