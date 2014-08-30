package com.jpattern.batch.logger;

import com.jpattern.logger.ILogger;
import com.jpattern.logger.ILoggerFactory;
import com.jpattern.logger.NullLoggerFactory;
import com.jpattern.logger.Slf4JLoggerFactory;

/**
 * 
 * @author Francesco Cina
 *
 * 04/giu/2011
 */
public abstract class JobPoolLogger  {

	private static ILoggerFactory loggerFactory;

	public static void setLoggerFactory(final ILoggerFactory loggerFactory) {
		JobPoolLogger.loggerFactory = loggerFactory;
	}

	public static ILogger getLogger(final Class<?> clazz) {
		if ( loggerFactory == null ) {
			try {
				loggerFactory = new Slf4JLoggerFactory();
				//Here a class not found exception could be thrown
				loggerFactory.logger(clazz);
			} catch (Throwable e) {
				System.err.println("JobPool warning: Slf4J library not found in classpath. Include it or set a different ILoggerFactory through " + JobPoolLogger.class.getCanonicalName() + "'s setLoggerFactory method");
				loggerFactory = new NullLoggerFactory();
			}
		}
		return loggerFactory.logger(clazz);
	}

}
