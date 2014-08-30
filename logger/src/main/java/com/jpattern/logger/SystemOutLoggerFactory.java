package com.jpattern.logger;

/**
 * 
 * @author Francesco Cina
 *
 * 04/giu/2011
 */
public class SystemOutLoggerFactory implements ILoggerFactory {

	private static final long serialVersionUID = 1L;
	private final ILoggerCallback defaultLoggerCallback = new NullLoggerCallback();
	
	@Override
	public ILogger logger(Class<?> aClass) {
		return logger(aClass, defaultLoggerCallback);
	}
	
	@Override
	public ILogger logger(Class<?> clazz, ILoggerCallback loggerCallback) {
		return new SystemOutLogger(clazz, loggerCallback);
	}

}
