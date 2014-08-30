package com.jpattern.gwt.client.logger;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Francesco Cina'
 *
 * 12 Apr 2011
 */
public class GwtLogger implements ILogger {
	
	private final Logger logger;
	
	public GwtLogger(String name) {
		logger = Logger.getLogger(name);
	}
	
	@Override
	public void trace(String methodName, String message) {
		logger.finest(methodName + " - " + message);
		
	}
	
	@Override
	public void debug(String methodName, String message) {
		logger.fine(methodName + " - " + message);
	}

	@Override
	public void info(String methodName, String message) {
		logger.info(methodName + " - " + message);
	}

	@Override
	public void warn(String methodName, String message) {
		logger.warning(methodName + " - " + message);
	}

	@Override
	public void error(String methodName, String message, Throwable e) {
		logger.log(Level.SEVERE, methodName + " - " + message, e);
	}

}
