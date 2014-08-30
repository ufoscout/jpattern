package com.jpattern.service.log;

import com.jpattern.core.BaseApplicationTest;
import com.jpattern.logger.ILogger;
import com.jpattern.service.log.AExecutor;
import com.jpattern.service.log.IExecutor;
import com.jpattern.service.log.LoggerServiceBuilder;
import com.jpattern.service.log.NullExecutor;
import com.jpattern.service.log.event.DebugEvent;
import com.jpattern.service.log.event.ErrorEvent;
import com.jpattern.service.log.event.InfoEvent;
import com.jpattern.service.log.event.TraceEvent;
import com.jpattern.service.log.event.WarnEvent;

/**
 * 
 * @author Francesco Cina'
 *
 * 12/ago/2010
 */
public class AExecutorTest extends BaseApplicationTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testLoggerExecutor() throws Exception {
		MockAExecutor executor1 = new MockAExecutor(new NullExecutor());
		MockAExecutor executor2 = new MockAExecutor(executor1);
		LoggerServiceBuilder loggerServiceBuilder = new LoggerServiceBuilder(executor2 );
		ILoggerService loggerService = loggerServiceBuilder.buildService();
		ILogger logger = loggerService.logger(this.getClass());
		
		logger.trace("", "");
		assertTrue( executor1.isExecuted() );
		assertTrue( executor2.isExecuted() );
		
		executor1.setLoggerLevel( ILoggerService.TRACE);
		executor2.setLoggerLevel( ILoggerService.DEBUG);
		logger.trace("", "");
		assertTrue( executor1.isExecuted() );
		assertFalse( executor2.isExecuted() );
		
		executor1.setLoggerLevel( ILoggerService.ERROR);
		executor2.setLoggerLevel( ILoggerService.WARNING);
		logger.info("", "");
		assertFalse( executor1.isExecuted() );
		assertFalse( executor2.isExecuted() );
		logger.warn("", "");
		assertFalse( executor1.isExecuted() );
		assertTrue( executor2.isExecuted() );
		
		executor1.setLoggerLevel( ILoggerService.INFO);
		executor2.setLoggerLevel( ILoggerService.WARNING);
		logger.info("", "");
		assertTrue( executor1.isExecuted() );
		assertFalse( executor2.isExecuted() );
		
		executor1.setLoggerLevel( ILoggerService.OFF);
		executor2.setLoggerLevel( ILoggerService.OFF);
		logger.trace("", "");
		logger.debug("", "");
		logger.info("", "");
		logger.warn("", "");
		logger.error("", "");
		assertFalse( executor1.isExecuted() );
		assertFalse( executor2.isExecuted() );
		
		executor1.setLoggerLevel( ILoggerService.TRACE);
		executor1.addIgnoredPath("com.jpattern.core");
		logger.trace("", "");
		assertTrue( executor1.isExecuted() );
		executor1.addIgnoredPath("com.jpattern.service");
		logger.trace("", "");
		assertFalse( executor1.isExecuted() );
	}

	class MockAExecutor extends AExecutor {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private boolean executed = false;
		
		public MockAExecutor(IExecutor successor) {
			super(successor);
		}

		@Override
		public void what(TraceEvent event) {
			executed = true;
		}

		@Override
		public void what(DebugEvent event) {
			executed = true;
		}

		@Override
		public void what(InfoEvent event) {
			executed = true;
		}

		@Override
		public void what(WarnEvent event) {
			executed = true;
		}

		@Override
		public void what(ErrorEvent event) {
			executed = true;
		}
		
		boolean isExecuted() {
			boolean result = executed;
			executed = false;
			return result;
		}
	}
	
}
