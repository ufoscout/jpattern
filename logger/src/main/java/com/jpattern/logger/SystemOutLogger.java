package com.jpattern.logger;

import java.util.Date;

/**
 * 
 * @author Francesco Cina
 *
 * 04/giu/2011
 */
public class SystemOutLogger implements ILogger {

	private static final String DEFAULT_METHOD_NAME = "";
	private static final long serialVersionUID = 1L;
	private final String className;
	private final ILoggerCallback loggerCallback;

	public SystemOutLogger(Class<?> clazz, ILoggerCallback loggerCallback) {
		this.loggerCallback = loggerCallback;
		className = clazz.getName();
	}

	@Override
	public void trace(String method, String message) {
		log("[TRACE]", className , method, message);
		loggerCallback.trace(method, message);
	}

	@Override
	public void debug(String method, String message) {
		log("[DEBUG]", className , method, message);
		loggerCallback.debug(method, message);
	}

	@Override
	public void info(String method, String message) {
		log("[INFO ]", className , method, message);
		loggerCallback.info(method, message);
	}

	@Override
	public void warn(String method, String message) {
		log("[WARN ]", className , method, message);
		loggerCallback.warn(method, message);
	}

	@Override
	public void error(String method, String message) {
		log("[ERROR]", className , method, message);
		loggerCallback.error(method, message);
	}

	@Override
	public void error(String method, String message, Throwable e) {
		log("[ERROR]", className , method, message);
		log(e);
		loggerCallback.error(method, message, e);
	}

	private void log(String level, String className, String method, String message) {
		final StringBuffer log = new StringBuffer();
		log.append("[" + new Date() + "] ");
		log.append(level);
		log.append(" [" + className + "] ");
		log.append("[" + method + "] ");
		log.append("[" + message + "] ");
		System.out.println( log );
	}

	private void log(Throwable e) {
		e.printStackTrace();
	}

	@Override
	public void trace(String message) {
		trace(DEFAULT_METHOD_NAME, message);
	}

	@Override
	public void debug(String message) {
		debug(DEFAULT_METHOD_NAME, message);
	}

	@Override
	public void info(String message) {
		info(DEFAULT_METHOD_NAME, message);
	}

	@Override
	public void warn(String message) {
		warn(DEFAULT_METHOD_NAME, message);
	}

	@Override
	public void error(String message) {
		error(DEFAULT_METHOD_NAME, message);
	}

	@Override
	public void error(String message, Throwable e) {
		error(DEFAULT_METHOD_NAME, message, e);
	}
}
