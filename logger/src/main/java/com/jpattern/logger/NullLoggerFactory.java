package com.jpattern.logger;

import java.io.Serializable;

/**
 * 
 * @author Francesco Cina
 *
 * 15/giu/2011
 */
public class NullLoggerFactory implements ILoggerFactory, Serializable {

	private static final long serialVersionUID = 1L;
	private final ILoggerCallback defaultLoggerCallback = new NullLoggerCallback();
	
	@Override
	public ILogger logger(Class<?> aClass) {
		return logger(aClass, defaultLoggerCallback);
	}

	@Override
	public ILogger logger(Class<?> aClass, ILoggerCallback loggerCallback) {
		return new NullLogger(loggerCallback);
	}

}
