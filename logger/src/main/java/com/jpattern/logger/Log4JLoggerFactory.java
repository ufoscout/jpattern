package com.jpattern.logger;

/**
 * 
 * @author Francesco Cina
 *
 * 18/giu/2011
 */
public class Log4JLoggerFactory implements ILoggerFactory {

	private static final long serialVersionUID = 1L;
	private final ILoggerCallback defaultLoggerCallback = new NullLoggerCallback();
	
	@Override
	public ILogger logger(Class<?> aClass) {
		return logger(aClass, defaultLoggerCallback);
	}

	@Override
	public ILogger logger(Class<?> aClass, ILoggerCallback loggerCallback) {
		return new Log4JLogger( aClass , loggerCallback );
	}

}
