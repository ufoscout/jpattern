package com.jpattern.logger;

/**
 * 
 * @author Francesco Cina'
 *
 * Nov 22, 2011
 */
public class WrappersLoggerCallback implements ILoggerCallback{

	private static final long serialVersionUID = 1L;
	private final ILogger[] loggers;

	public WrappersLoggerCallback(ILogger[] loggers) {
		this.loggers = loggers;
	}

	@Override
	public void trace(String method, String message) {
		for (final ILogger logger : loggers) {
			logger.trace(method, message);
		}
	}

	@Override
	public void debug(String method, String message) {
		for (final ILogger logger : loggers) {
			logger.debug(method, message);
		}
	}

	@Override
	public void info(String method, String message) {
		for (final ILogger logger : loggers) {
			logger.info(method, message);
		}
	}

	@Override
	public void warn(String method, String message) {
		for (final ILogger logger : loggers) {
			logger.warn(method, message);
		}
	}

	@Override
	public void error(String method, String message) {
		for (final ILogger logger : loggers) {
			logger.error(method, message);
		}
	}

	@Override
	public void error(String method, String message, Throwable e) {
		for (final ILogger logger : loggers) {
			logger.error(method, message, e);
		}
	}

	@Override
	public void trace( String message) {
		for (final ILogger logger : loggers) {
			logger.trace( message);
		}
	}

	@Override
	public void debug( String message) {
		for (final ILogger logger : loggers) {
			logger.debug( message);
		}
	}

	@Override
	public void info( String message) {
		for (final ILogger logger : loggers) {
			logger.info( message);
		}
	}

	@Override
	public void warn(String message) {
		for (final ILogger logger : loggers) {
			logger.warn( message);
		}
	}

	@Override
	public void error( String message) {
		for (final ILogger logger : loggers) {
			logger.error(message);
		}
	}

	@Override
	public void error( String message, Throwable e) {
		for (final ILogger logger : loggers) {
			logger.error( message, e);
		}
	}

}
