package com.jpattern.logger;

import java.io.IOException;
import java.io.ObjectInputStream;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * 
 * @author Francesco Cina
 *
 * 18/giu/2011
 */
public class Log4JLogger implements ILogger {

	private static final String FQCN = Log4JLogger.class.getName();
	private static final long serialVersionUID = 1L;
	private transient Logger logger;
	private final Class<?> aClass;
	private final ILoggerCallback loggerCallback;

	public Log4JLogger(Class<?> aClass, ILoggerCallback loggerCallback) {
		this.aClass = aClass;
		this.loggerCallback = loggerCallback;
		logger = Logger.getLogger(aClass);
	}

	@Override
	public void trace(String method, String message) {
		logger.log(FQCN, Level.TRACE, message, null);
		loggerCallback.trace(method, message);
	}

	@Override
	public void debug(String method, String message) {
		logger.log(FQCN, Level.DEBUG, message, null);
		loggerCallback.debug(method, message);
	}

	@Override
	public void info(String method, String message) {
		logger.log(FQCN, Level.INFO, message, null);
		loggerCallback.info(method, message);
	}

	@Override
	public void warn(String method, String message) {
		logger.log(FQCN, Level.WARN, message, null);
		loggerCallback.warn(method, message);
	}

	@Override
	public void error(String method, String message) {
		logger.log(FQCN, Level.ERROR, message, null);
		loggerCallback.error(method, message);
	}

	@Override
	public void error(String method, String message, Throwable e) {
		logger.log(FQCN, Level.ERROR, message, e);
		loggerCallback.error(method, message, e);
	}

	@Override
	public void trace(String message) {
		logger.log(FQCN, Level.TRACE, message, null);
		loggerCallback.trace(message);
	}

	@Override
	public void debug(String message) {
		logger.log(FQCN, Level.DEBUG, message, null);
		loggerCallback.debug(message);
	}

	@Override
	public void info(String message) {
		logger.log(FQCN, Level.INFO, message, null);
		loggerCallback.info( message);
	}

	@Override
	public void warn(String message) {
		logger.log(FQCN, Level.WARN, message, null);
		loggerCallback.warn(message);
	}

	@Override
	public void error(String message) {
		logger.log(FQCN, Level.ERROR, message, null);
		loggerCallback.error(message);
	}

	@Override
	public void error(String message, Throwable e) {
		logger.log(FQCN, Level.ERROR, message, e);
		loggerCallback.error(message, e);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		logger = Logger.getLogger(aClass);
	}
}
