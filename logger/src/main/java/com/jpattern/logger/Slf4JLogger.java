package com.jpattern.logger;

import java.io.IOException;
import java.io.ObjectInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LocationAwareLogger;

/**
 * 
 * @author Francesco Cina
 * 
 *         18/giu/2011
 */
public class Slf4JLogger implements ILogger {

	private static final String FQCN = Slf4JLogger.class.getName();
	private static final long serialVersionUID = 1L;
	private transient Logger logger;
	private final Class<?> aClass;
	private final ILoggerCallback loggerCallback;

	public Slf4JLogger(Class<?> aClass, ILoggerCallback loggerCallback) {
		this.aClass = aClass;
		this.loggerCallback = loggerCallback;
		logger = LoggerFactory.getLogger(aClass);
	}

	@Override
	public void trace(String method, String message) {
		if (logger instanceof LocationAwareLogger) {
			((LocationAwareLogger) logger).log(null, FQCN,
					LocationAwareLogger.TRACE_INT, message, null, null);
		} else {
			logger.trace(message);
		}
		loggerCallback.trace(method, message);
	}

	@Override
	public void debug(String method, String message) {
		if (logger instanceof LocationAwareLogger) {
			((LocationAwareLogger) logger).log(null, FQCN,
					LocationAwareLogger.DEBUG_INT, message, null, null);
		} else {
			logger.debug(message);
		}
		loggerCallback.debug(method, message);
	}

	@Override
	public void info(String method, String message) {
		if (logger instanceof LocationAwareLogger) {
			((LocationAwareLogger) logger).log(null, FQCN,
					LocationAwareLogger.INFO_INT, message, null, null);
		} else {
			logger.info(message);
		}
		loggerCallback.info(method, message);
	}

	@Override
	public void warn(String method, String message) {
		if (logger instanceof LocationAwareLogger) {
			((LocationAwareLogger) logger).log(null, FQCN,
					LocationAwareLogger.WARN_INT, message, null, null);
		} else {
			logger.warn(message);
		}
		loggerCallback.warn(method, message);
	}

	@Override
	public void error(String method, String message) {
		if (logger instanceof LocationAwareLogger) {
			((LocationAwareLogger) logger).log(null, FQCN,
					LocationAwareLogger.ERROR_INT, message, null, null);
		} else {
			logger.error(message);
		}
		loggerCallback.error(method, message);
	}

	@Override
	public void error(String method, String message, Throwable e) {
		if (logger instanceof LocationAwareLogger) {
			((LocationAwareLogger) logger).log(null, FQCN,
					LocationAwareLogger.ERROR_INT, message, null, e);
		} else {
			logger.error(message, e);
		}
		loggerCallback.error(method, message, e);
	}

	private void readObject(ObjectInputStream in) throws IOException,
	ClassNotFoundException {
		logger = LoggerFactory.getLogger(aClass);
	}

	@Override
	public void trace(String message) {
		if (logger instanceof LocationAwareLogger) {
			((LocationAwareLogger) logger).log(null, FQCN,
					LocationAwareLogger.TRACE_INT, message, null, null);
		} else {
			logger.trace(message);
		}
		loggerCallback.trace( message);
	}

	@Override
	public void debug(String message) {
		if (logger instanceof LocationAwareLogger) {
			((LocationAwareLogger) logger).log(null, FQCN,
					LocationAwareLogger.DEBUG_INT, message, null, null);
		} else {
			logger.debug(message);
		}
		loggerCallback.debug(message);
	}

	@Override
	public void info(String message) {
		if (logger instanceof LocationAwareLogger) {
			((LocationAwareLogger) logger).log(null, FQCN,
					LocationAwareLogger.INFO_INT, message, null, null);
		} else {
			logger.info(message);
		}
		loggerCallback.info(message);
	}

	@Override
	public void warn(String message) {
		if (logger instanceof LocationAwareLogger) {
			((LocationAwareLogger) logger).log(null, FQCN,
					LocationAwareLogger.WARN_INT, message, null, null);
		} else {
			logger.warn(message);
		}
		loggerCallback.warn(message);
	}

	@Override
	public void error(String message) {
		if (logger instanceof LocationAwareLogger) {
			((LocationAwareLogger) logger).log(null, FQCN,
					LocationAwareLogger.ERROR_INT, message, null, null);
		} else {
			logger.error(message);
		}
		loggerCallback.error(message);
	}

	@Override
	public void error(String message, Throwable e) {
		if (logger instanceof LocationAwareLogger) {
			((LocationAwareLogger) logger).log(null, FQCN,
					LocationAwareLogger.ERROR_INT, message, null, e);
		} else {
			logger.error(message, e);
		}
		loggerCallback.error(message, e);
	}

}
