package com.jpattern.service.log.slf4j;

import com.jpattern.core.BaseTest;
import com.jpattern.logger.ILogger;
import com.jpattern.service.log.IExecutor;
import com.jpattern.service.log.ILoggerService;
import com.jpattern.service.log.LoggerServiceBuilder;
import com.jpattern.service.log.slf4j.Slf4JExecutor;

/**
 * 
 * @author Francesco Cina'
 *
 * 12/ago/2010
 */
public class Slf4JExecutorTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testSlf4JLogger() throws Exception {
		IExecutor executor = new Slf4JExecutor();

		LoggerServiceBuilder loggerServiceBuilder = new LoggerServiceBuilder(executor);
		ILoggerService loggerService = loggerServiceBuilder.buildService();
		ILogger logger = loggerService.logger(this.getClass());

		logger.trace("testLogger", "trace message");
		System.out.println("---------------------");
		
		logger.debug("testLogger", "debug message");
		System.out.println("---------------------");
		
		logger.info("testLogger", "info message");
		System.out.println("---------------------");

		logger.error("testLogger", "error message");
		System.out.println("---------------------");

		logger.warn("testLogger", "warn message");
		System.out.println("---------------------");

		System.out.println("--------------------- BEGIN: ECCEZIONE GENERATA PER TEST ---------------------");
		logger.error("testLogger", "error message with exception",	new NullPointerException());
		System.out.println("--------------------- END: ECCEZIONE GENERATA PER TEST ---------------------");
	}
}
