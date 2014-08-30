package com.jpattern.service.log;

import com.jpattern.core.BaseTest;
import com.jpattern.logger.ILogger;
import com.jpattern.service.log.IExecutor;
import com.jpattern.service.log.LoggerServiceBuilder;
import com.jpattern.service.log.SystemOutExecutor;
import com.jpattern.service.log.event.DebugEvent;
import com.jpattern.service.log.event.ErrorEvent;
import com.jpattern.service.log.event.InfoEvent;
import com.jpattern.service.log.event.TraceEvent;
import com.jpattern.service.log.event.WarnEvent;

/**
 * 
 * @author Francesco Cina'
 * 
 *         09/apr/2010
 */
public class ILoggerServiceTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testLogger() throws Exception {

		MockExecutor mockExecutor = new MockExecutor();

		IExecutor executor = new SystemOutExecutor(mockExecutor);

		LoggerServiceBuilder loggerServiceBuilder = new LoggerServiceBuilder( executor);
		ILoggerService loggerService = loggerServiceBuilder.buildService();
		ILogger logger = loggerService.logger(this.getClass());

		logger.trace("testLogger", "trace message");
		assertEquals("TRACE", mockExecutor.lastEventName);

		logger.debug("testLogger", "debug message");
		assertEquals("DEBUG", mockExecutor.lastEventName);

		logger.info("testLogger", "info message");
		assertEquals("INFO", mockExecutor.lastEventName);

		logger.error("testLogger", "error message");
		assertEquals("ERROR", mockExecutor.lastEventName);

		logger.warn("testLogger", "warn message");
		assertEquals("WARN", mockExecutor.lastEventName);

		logger.error("testLogger", "error message with exception",
				new NullPointerException());
		assertEquals("ERROR", mockExecutor.lastEventName);
	}

	public void testLoggerErrorLevel() throws Exception {

		MockExecutor mockExecutor = new MockExecutor();

		IExecutor executor = new SystemOutExecutor(mockExecutor);

		LoggerServiceBuilder loggerServiceBuilder = new LoggerServiceBuilder(executor);
		ILoggerService loggerService = loggerServiceBuilder.buildService();

		loggerService.setErrorLoggerLevel();

		ILogger logger = loggerService.logger(this.getClass());

		logger.trace("testLogger", "trace message");
		assertEquals("", mockExecutor.lastEventName);
		logger.debug("testLogger", "debug message");
		assertEquals("", mockExecutor.lastEventName);
		logger.info("testLogger", "info message");
		assertEquals("", mockExecutor.lastEventName);
		logger.error("testLogger", "error message");
		assertEquals("ERROR", mockExecutor.lastEventName);
		logger.warn("testLogger", "warn message");
		assertEquals("ERROR", mockExecutor.lastEventName);
		logger.error("testLogger", "error message with exception",
				new NullPointerException());
		assertEquals("ERROR", mockExecutor.lastEventName);
	}

	public void testLoggerWarnLevel() throws Exception {

		MockExecutor mockExecutor = new MockExecutor();

		IExecutor executor = new SystemOutExecutor(mockExecutor);

		LoggerServiceBuilder loggerServiceBuilder = new LoggerServiceBuilder(executor);
		ILoggerService loggerService = loggerServiceBuilder.buildService();

		loggerService.setWarnLoggerLevel();

		ILogger logger = loggerService.logger(this.getClass());

		logger.trace("testLogger", "trace message");
		assertEquals("", mockExecutor.lastEventName);
		logger.debug("testLogger", "debug message");
		assertEquals("", mockExecutor.lastEventName);
		logger.info("testLogger", "info message");
		assertEquals("", mockExecutor.lastEventName);
		logger.error("testLogger", "error message");
		assertEquals("ERROR", mockExecutor.lastEventName);
		logger.warn("testLogger", "warn message");
		assertEquals("WARN", mockExecutor.lastEventName);
		logger.error("testLogger", "error message with exception",
				new NullPointerException());
		assertEquals("ERROR", mockExecutor.lastEventName);
	}

	public void testLoggerInfoLevel() throws Exception {

		MockExecutor mockExecutor = new MockExecutor();

		IExecutor executor = new SystemOutExecutor( mockExecutor);

		LoggerServiceBuilder loggerServiceBuilder = new LoggerServiceBuilder(executor);
		ILoggerService loggerService = loggerServiceBuilder.buildService();

		loggerService.setErrorLoggerLevel();
		loggerService.setInfoLoggerLevel();

		ILogger logger = loggerService.logger(this.getClass());

		logger.trace("testLogger", "trace message");
		assertEquals("", mockExecutor.lastEventName);
		logger.debug("testLogger", "debug message");
		assertEquals("", mockExecutor.lastEventName);
		logger.info("testLogger", "info message");
		assertEquals("INFO", mockExecutor.lastEventName);
		logger.error("testLogger", "error message");
		assertEquals("ERROR", mockExecutor.lastEventName);
		logger.warn("testLogger", "warn message");
		assertEquals("WARN", mockExecutor.lastEventName);
		logger.error("testLogger", "error message with exception",
				new NullPointerException());
		assertEquals("ERROR", mockExecutor.lastEventName);
	}

	public void testLoggerDebugLevel() throws Exception {

		MockExecutor mockExecutor = new MockExecutor();

		IExecutor executor = new SystemOutExecutor( mockExecutor);

		LoggerServiceBuilder loggerServiceBuilder = new LoggerServiceBuilder(executor);
		ILoggerService loggerService = loggerServiceBuilder.buildService();

		loggerService.setDebugLoggerLevel();

		ILogger logger = loggerService.logger(this.getClass());

		logger.trace("testLogger", "trace message");
		assertEquals("", mockExecutor.lastEventName);
		logger.debug("testLogger", "debug message");
		assertEquals("DEBUG", mockExecutor.lastEventName);
		logger.info("testLogger", "info message");
		assertEquals("INFO", mockExecutor.lastEventName);
		logger.error("testLogger", "error message");
		assertEquals("ERROR", mockExecutor.lastEventName);
		logger.warn("testLogger", "warn message");
		assertEquals("WARN", mockExecutor.lastEventName);
		logger.error("testLogger", "error message with exception",	new NullPointerException());
		assertEquals("ERROR", mockExecutor.lastEventName);
	}

	public void testLoggerTraceLevel() throws Exception {
		
		MockExecutor mockExecutor = new MockExecutor();
		
		IExecutor executor = new SystemOutExecutor( mockExecutor);
		
		LoggerServiceBuilder loggerServiceBuilder = new LoggerServiceBuilder(executor);
		ILoggerService loggerService = loggerServiceBuilder.buildService();
		
		loggerService.setTraceLoggerLevel();
		
		ILogger logger = loggerService.logger(this.getClass());
		
		logger.trace("testLogger", "trace message");
		assertEquals( "TRACE" , mockExecutor.lastEventName );
		logger.debug("testLogger", "debug message");
		assertEquals( "DEBUG" , mockExecutor.lastEventName );
		logger.info("testLogger", "info message");
		assertEquals( "INFO" , mockExecutor.lastEventName );
		logger.error("testLogger", "error message");
		assertEquals( "ERROR" , mockExecutor.lastEventName );
		logger.warn("testLogger", "warn message");
		assertEquals( "WARN" , mockExecutor.lastEventName );
		logger.error("testLogger", "error message with exception", new NullPointerException());
		assertEquals( "ERROR" , mockExecutor.lastEventName );
	}
	
	
	public void testLoggerClasspathLevel() throws Exception {
		
		MockExecutor mockExecutor = new MockExecutor();
		
		IExecutor executor = new SystemOutExecutor( mockExecutor);
		
		LoggerServiceBuilder loggerServiceBuilder = new LoggerServiceBuilder( executor);
		ILoggerService loggerService = loggerServiceBuilder.buildService();
		ILogger logger = loggerService.logger(this.getClass());
		
		
		loggerService.setErrorLoggerLevel( this.getClass().getName() );
		logger.error("testLogger", "error message");
		assertEquals( "ERROR" , mockExecutor.lastEventName );
		logger.warn("testLogger", "warn message");
		assertEquals( "ERROR" , mockExecutor.lastEventName );
		logger.info("testLogger", "info message");
		assertEquals( "ERROR" , mockExecutor.lastEventName );
		logger.debug("testLogger", "debug message");
		assertEquals( "ERROR" , mockExecutor.lastEventName );
		logger.trace("testLogger", "trace message");
		assertEquals( "ERROR" , mockExecutor.lastEventName );
		
		
		loggerService.setWarnLoggerLevel( this.getClass().getName() );
		logger.error("testLogger", "error message");
		assertEquals( "ERROR" , mockExecutor.lastEventName );
		logger.warn("testLogger", "warn message");
		assertEquals( "WARN" , mockExecutor.lastEventName );
		logger.info("testLogger", "info message");
		assertEquals( "WARN" , mockExecutor.lastEventName );
		logger.debug("testLogger", "debug message");
		assertEquals( "WARN" , mockExecutor.lastEventName );
		logger.trace("testLogger", "trace message");
		assertEquals( "WARN" , mockExecutor.lastEventName );
		
		
		loggerService.setInfoLoggerLevel( this.getClass().getName() );
		logger.error("testLogger", "error message");
		assertEquals( "ERROR" , mockExecutor.lastEventName );
		logger.warn("testLogger", "warn message");
		assertEquals( "WARN" , mockExecutor.lastEventName );
		logger.info("testLogger", "info message");
		assertEquals( "INFO" , mockExecutor.lastEventName );
		logger.debug("testLogger", "debug message");
		assertEquals( "INFO" , mockExecutor.lastEventName );
		logger.trace("testLogger", "trace message");
		assertEquals( "INFO" , mockExecutor.lastEventName );
		
		
		loggerService.setDebugLoggerLevel( this.getClass().getName() );
		logger.error("testLogger", "error message");
		assertEquals( "ERROR" , mockExecutor.lastEventName );
		logger.warn("testLogger", "warn message");
		assertEquals( "WARN" , mockExecutor.lastEventName );
		logger.info("testLogger", "info message");
		assertEquals( "INFO" , mockExecutor.lastEventName );
		logger.debug("testLogger", "debug message");
		assertEquals( "DEBUG" , mockExecutor.lastEventName );
		logger.trace("testLogger", "trace message");
		assertEquals( "DEBUG" , mockExecutor.lastEventName );
		
		
		loggerService.setTraceLoggerLevel( this.getClass().getName() );
		logger.error("testLogger", "error message");
		assertEquals( "ERROR" , mockExecutor.lastEventName );
		logger.warn("testLogger", "warn message");
		assertEquals( "WARN" , mockExecutor.lastEventName );
		logger.info("testLogger", "info message");
		assertEquals( "INFO" , mockExecutor.lastEventName );
		logger.debug("testLogger", "debug message");
		assertEquals( "DEBUG" , mockExecutor.lastEventName );
		logger.trace("testLogger", "trace message");
		assertEquals( "TRACE" , mockExecutor.lastEventName );
	}
	
	
	public void testLoggerLevelMap() throws Exception {
		
		MockExecutor mockExecutor = new MockExecutor();
		
		IExecutor executor = new SystemOutExecutor( mockExecutor);
		
		LoggerServiceBuilder loggerServiceBuilder = new LoggerServiceBuilder(executor);
		ILoggerService loggerService = loggerServiceBuilder.buildService();
		
		loggerService.setTraceLoggerLevel();
		
		ILogger logger = loggerService.logger(this.getClass());
		
		loggerService.setLoggerLevel( this.getClass().getName() , ILoggerService.ERROR);
		logger.error("testLogger", "error message");
		assertEquals( "ERROR" , mockExecutor.lastEventName );
		logger.warn("testLogger", "warn message");
		assertEquals( "ERROR" , mockExecutor.lastEventName );
		logger.info("testLogger", "info message");
		assertEquals( "ERROR" , mockExecutor.lastEventName );
		logger.debug("testLogger", "debug message");
		assertEquals( "ERROR" , mockExecutor.lastEventName );
		logger.trace("testLogger", "trace message");
		assertEquals( "ERROR" , mockExecutor.lastEventName );
		
		
		loggerService.setLoggerLevel( this.getClass().getName() , ILoggerService.WARNING);
		logger.error("testLogger", "error message");
		assertEquals( "ERROR" , mockExecutor.lastEventName );
		logger.warn("testLogger", "warn message");
		assertEquals( "WARN" , mockExecutor.lastEventName );
		logger.info("testLogger", "info message");
		assertEquals( "WARN" , mockExecutor.lastEventName );
		logger.debug("testLogger", "debug message");
		assertEquals( "WARN" , mockExecutor.lastEventName );
		logger.trace("testLogger", "trace message");
		assertEquals( "WARN" , mockExecutor.lastEventName );
		
		
		loggerService.setLoggerLevel( this.getClass().getName() , ILoggerService.INFO);
		logger.error("testLogger", "error message");
		assertEquals( "ERROR" , mockExecutor.lastEventName );
		logger.warn("testLogger", "warn message");
		assertEquals( "WARN" , mockExecutor.lastEventName );
		logger.info("testLogger", "info message");
		assertEquals( "INFO" , mockExecutor.lastEventName );
		logger.debug("testLogger", "debug message");
		assertEquals( "INFO" , mockExecutor.lastEventName );
		logger.trace("testLogger", "trace message");
		assertEquals( "INFO" , mockExecutor.lastEventName );
		
		
		loggerService.setLoggerLevel( this.getClass().getName() , ILoggerService.DEBUG);
		logger.error("testLogger", "error message");
		assertEquals( "ERROR" , mockExecutor.lastEventName );
		logger.warn("testLogger", "warn message");
		assertEquals( "WARN" , mockExecutor.lastEventName );
		logger.info("testLogger", "info message");
		assertEquals( "INFO" , mockExecutor.lastEventName );
		logger.debug("testLogger", "debug message");
		assertEquals( "DEBUG" , mockExecutor.lastEventName );
		logger.trace("testLogger", "trace message");
		assertEquals( "DEBUG" , mockExecutor.lastEventName );
		
		
		loggerService.setLoggerLevel( this.getClass().getName() , ILoggerService.TRACE);
		logger.error("testLogger", "error message");
		assertEquals( "ERROR" , mockExecutor.lastEventName );
		logger.warn("testLogger", "warn message");
		assertEquals( "WARN" , mockExecutor.lastEventName );
		logger.info("testLogger", "info message");
		assertEquals( "INFO" , mockExecutor.lastEventName );
		logger.debug("testLogger", "debug message");
		assertEquals( "DEBUG" , mockExecutor.lastEventName );
		logger.trace("testLogger", "trace message");
		assertEquals( "TRACE" , mockExecutor.lastEventName );
		
		loggerService.setLoggerLevel( this.getClass().getName() , ILoggerService.OFF);
		logger.error("testLogger", "error message");
		assertEquals( "TRACE" , mockExecutor.lastEventName );
		logger.warn("testLogger", "warn message");
		assertEquals( "TRACE" , mockExecutor.lastEventName );
		logger.info("testLogger", "info message");
		assertEquals( "TRACE" , mockExecutor.lastEventName );
		logger.debug("testLogger", "debug message");
		assertEquals( "TRACE" , mockExecutor.lastEventName );
		logger.trace("testLogger", "trace message");
		assertEquals( "TRACE" , mockExecutor.lastEventName );
	}
	
	
	class MockExecutor implements IExecutor {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		String lastEventName = "";

		public void execute(TraceEvent event) {
			lastEventName = "TRACE";
		}

		public void execute(DebugEvent event) {
			lastEventName = "DEBUG";
		}

		public void execute(InfoEvent event) {
			lastEventName = "INFO";
		}

		public void execute(WarnEvent event) {
			lastEventName = "WARN";
		}

		public void execute(ErrorEvent event) {
			lastEventName = "ERROR";
		}

		public void setLoggerLevel(String loggerLevel) {
		}

		@Override
		public void addIgnoredPath(String classpath) {
		}

		@Override
		public void removeIgnoredPath(String classpath) {
		}

		@Override
		public boolean isIgnoredPath(String classpath) {
			return false;
		}

	}
}
