package com.jpattern.logger;

/**
 * 
 * @author Francesco Cina
 *
 * 18/giu/2011
 */
public class Slf4JLoggerFactory implements ILoggerFactory {

	private static final long serialVersionUID = 1L;
	private final ILoggerCallback defaultLoggerCallback = new NullLoggerCallback();
	
	@Override
	public ILogger logger(Class<?> aClass) {
		return logger(aClass, defaultLoggerCallback);
	}
	
	@Override
	public ILogger logger(Class<?> aClass, ILoggerCallback loggerCallback) {
		return new Slf4JLogger(aClass, loggerCallback);
	}

}
