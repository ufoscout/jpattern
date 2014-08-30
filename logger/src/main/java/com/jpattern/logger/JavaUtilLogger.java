package com.jpattern.logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Francesco Cina
 *
 * 18/giu/2011
 */
public class JavaUtilLogger implements ILogger {

	private static final long serialVersionUID = 1L;
	private transient Logger logger;
	private final Class<?> aClass;
	private final ILoggerCallback loggerCallback;

	public JavaUtilLogger(Class<?> aClass, ILoggerCallback loggerCallback) {
		this.aClass = aClass;
		this.loggerCallback = loggerCallback;
		logger = Logger.getLogger( aClass.getName() );
	}

	@Override
	public void trace(String method, String message) {
		logger.finest(message);
		loggerCallback.trace(method, message);
	}

	@Override
	public void debug(String method, String message) {
		logger.fine(message);
		loggerCallback.debug(method, message);
	}

	@Override
	public void info(String method, String message) {
		logger.info(message);
		loggerCallback.info(method, message);
	}

	@Override
	public void warn(String method, String message) {
		logger.warning(message);
		loggerCallback.warn(method, message);
	}

	@Override
	public void error(String method, String message) {
		logger.severe(message);
		loggerCallback.error(method, message);

	}

	@Override
	public void error(String method, String message, Throwable e) {
		logger.log(Level.SEVERE, message, e);
		loggerCallback.error(method, message, e);
	}

	@Override
	public void trace(String message) {
		logger.finest(message);
		loggerCallback.trace(message);
	}

	@Override
	public void debug(String message) {
		logger.fine(message);
		loggerCallback.debug(message);
	}

	@Override
	public void info(String message) {
		logger.info(message);
		loggerCallback.info(message);
	}

	@Override
	public void warn(String message) {
		logger.warning(message);
		loggerCallback.warn(message);
	}

	@Override
	public void error(String message) {
		logger.severe(message);
		loggerCallback.error(message);

	}

	@Override
	public void error(String message, Throwable e) {
		logger.log(Level.SEVERE, message, e);
		loggerCallback.error(message, e);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		logger = Logger.getLogger( aClass.getName() );
	}
}
