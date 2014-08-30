package com.jpattern.service.log.event;

import java.util.Date;


import com.jpattern.core.BaseApplicationTest;
import com.jpattern.service.log.IExecutor;
import com.jpattern.service.log.event.DebugEvent;
import com.jpattern.service.log.event.ErrorEvent;
import com.jpattern.service.log.event.IEvent;
import com.jpattern.service.log.event.ITrigger;
import com.jpattern.service.log.event.InfoEvent;
import com.jpattern.service.log.event.MessageEx;
import com.jpattern.service.log.event.TraceEvent;
import com.jpattern.service.log.event.Trigger;
import com.jpattern.service.log.event.WarnEvent;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/apr/2010
 */
public class EventTest extends BaseApplicationTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	
	public void testLogger1() throws Exception {
		
		IExecutor executor = new MockExecutor();
		ITrigger trigger = new Trigger( executor );
		IEvent trace = new TraceEvent();
		trace.acceptITrigger(trigger);
		trace.fire();
		assertTrue( ((MockExecutor) executor).eseguitoTrace );
		
		((MockExecutor) executor).eseguitoTrace = false;
		
		trace.acceptITrigger(null);
		trace.fire();
		assertFalse( ((MockExecutor) executor).eseguitoTrace );
		
	}
	
	public void testLogger2() throws Exception {
		
		IExecutor executor = new MockExecutor();
		ITrigger trigger = new Trigger( executor );
		ErrorEvent error = new ErrorEvent();
		error.acceptITrigger(trigger);
		
		Date now = new Date();
		MessageEx message = new MessageEx("testClass", "testMethod", "testMessage", now, new Exception() );
		error.fireMessage(message);
		
		assertTrue( ((MockExecutor) executor).eseguitoError );
	}
	
	public void testIgnoredPath() throws Exception {
		
		IEvent event = new TraceEvent();
	
		assertFalse( event.isIgnoredPath("org.mage") );
		
		event.addIgnoredPath("org.mage");
		event.addIgnoredPath("org.mage");
		assertTrue( event.isIgnoredPath("org.mage") );
		assertTrue( event.isIgnoredPath("org.mage.log") );
		assertFalse( event.isIgnoredPath("it.org.mage") );
		
		
		event.removeIgnoredPath("org.mage");
		assertFalse( event.isIgnoredPath("org.mage") );
		assertFalse( event.isIgnoredPath("org.mage.log") );
		assertFalse( event.isIgnoredPath("it.org.mage") );
		
	}
	
	
	public void testIgnoredPath2() throws Exception {
		
		IExecutor executor = new MockExecutor();
		ITrigger trigger = new Trigger( executor );
		ErrorEvent error = new ErrorEvent();
		error.acceptITrigger(trigger);
		
		Date now = new Date();
		MessageEx message = new MessageEx("testClass", "testMethod", "testMessage", now, new Exception() );
		error.fireMessage(message);
		
		assertTrue( ((MockExecutor) executor).eseguitoError );
		
		((MockExecutor) executor).eseguitoError = false;
		error.addIgnoredPath("test");
		error.fireMessage(message);
		assertFalse( ((MockExecutor) executor).eseguitoError );
		
		error.removeIgnoredPath("test");
		error.fireMessage(message);
		assertTrue( ((MockExecutor) executor).eseguitoError );
		
	}
	
	
	class MockExecutor implements IExecutor {

		private static final long serialVersionUID = 1L;
		private boolean eseguitoTrace = false;
		private boolean eseguitoError = false;
		
		public void execute(TraceEvent event) {
			System.out.println("ESEGUITO " );
			eseguitoTrace = true;
		}

		public void execute(DebugEvent event) {
		}

		public void execute(InfoEvent event) {
		}

		public void execute(WarnEvent event) {
		}

		public void execute(ErrorEvent event) {
			eseguitoError = true;
			System.out.println("EVENT: " + event.getName() );
			System.out.println(  );
			MessageEx message = event.getMessage();
			System.out.println( message.getClassName() );
			System.out.println( message.getMethod() );
			System.out.println( message.getMessage() );
			System.out.println( message.getExceptionType() );
			System.out.println( message.getExceptionStackTrace());
			
			assertEquals( "testClass" , message.getClassName() );
			assertEquals( "testMethod" , message.getMethod() );
			assertEquals( "testMessage" , message.getMessage() );
			assertEquals( "java.lang.Exception" , message.getExceptionType() );
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
