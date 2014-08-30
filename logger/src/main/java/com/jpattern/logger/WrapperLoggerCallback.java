package com.jpattern.logger;

/**
 * 
 * @author Francesco Cina'
 *
 * Nov 22, 2011
 */
public class WrapperLoggerCallback implements ILoggerCallback{

	private static final long serialVersionUID = 1L;
	private final ILogger logger;

	public WrapperLoggerCallback(ILogger logger) {
		this.logger = logger;
	}

	@Override
	public void trace(String method, String message) {
		logger.trace(method, message);
	}

	@Override
	public void debug(String method, String message) {
		logger.debug(method, message);
	}

	@Override
	public void info(String method, String message) {
		logger.info(method, message);
	}

	@Override
	public void warn(String method, String message) {
		logger.warn(method, message);
	}

	@Override
	public void error(String method, String message) {
		logger.error(method, message);
	}

	@Override
	public void error(String method, String message, Throwable e) {
		logger.error(method, message, e);
	}

	@Override
	public void trace(String message) {
		logger.trace(message);
	}

	@Override
	public void debug(String message) {
		logger.debug(message);
	}

	@Override
	public void info(String message) {
		logger.info(message);
	}

	@Override
	public void warn(String message) {
		logger.warn(message);
	}

	@Override
	public void error(String message) {
		logger.error(message);
	}

	@Override
	public void error(String message, Throwable e) {
		logger.error(message,e);
	}

}
