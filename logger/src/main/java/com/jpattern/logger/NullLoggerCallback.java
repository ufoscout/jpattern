package com.jpattern.logger;

/**
 * 
 * @author Francesco Cina'
 *
 * Nov 22, 2011
 */
public class NullLoggerCallback implements ILoggerCallback {

	private static final long serialVersionUID = 1L;

	@Override
	public void trace(String method, String message) {
	}

	@Override
	public void debug(String method, String message) {
	}

	@Override
	public void info(String method, String message) {
	}

	@Override
	public void warn(String method, String message) {
	}

	@Override
	public void error(String method, String message) {
	}

	@Override
	public void error(String method, String message, Throwable e) {
	}

	@Override
	public void trace(String message) {
	}

	@Override
	public void debug(String message) {
	}

	@Override
	public void info(String message) {
	}

	@Override
	public void warn(String message) {
	}

	@Override
	public void error(String message) {
	}

	@Override
	public void error(String message, Throwable e) {
	}

}
