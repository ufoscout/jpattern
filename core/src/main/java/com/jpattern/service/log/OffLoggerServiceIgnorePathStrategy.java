package com.jpattern.service.log;


/**
 * 
 * @author Francesco Cina'
 *
 * 12/ago/2010
 */
public class OffLoggerServiceIgnorePathStrategy implements ILoggerServiceIgnorePathStrategy {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void ignorePath(ILoggerService loggerService, String path) {
		loggerService.setOffLoggerLevel(path);
	}

}
