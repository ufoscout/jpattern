package com.jpattern.service.log;

import com.jpattern.core.BaseTest;
import com.jpattern.service.log.IExecutor;
import com.jpattern.service.log.IRoster;
import com.jpattern.service.log.SystemOutExecutor;
import com.jpattern.service.log.Roster;
import com.jpattern.service.log.event.DebugEvent;
import com.jpattern.service.log.event.ErrorEvent;
import com.jpattern.service.log.event.ITrigger;
import com.jpattern.service.log.event.InfoEvent;
import com.jpattern.service.log.event.TraceEvent;
import com.jpattern.service.log.event.Trigger;
import com.jpattern.service.log.event.WarnEvent;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/apr/2010
 */
public class RosterTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testRoster1() throws Exception {
		
		MockTraceExecutor mockExecutor = new MockTraceExecutor();
		
		IExecutor executor = new SystemOutExecutor( mockExecutor) ; 
		ITrigger trigger = new Trigger(executor);
		IRoster roster = new Roster( trigger );
		
		TraceEvent traceEvent = new TraceEvent();
		roster.addEvent( traceEvent );
		roster.addEvent( new DebugEvent() );
		roster.addEvent( new InfoEvent() );
		roster.addEvent( new WarnEvent() );
		roster.addEvent( new ErrorEvent() );
		
		
		assertEquals( 0 , mockExecutor.trace );

		traceEvent.fire();
		assertEquals( 1 , mockExecutor.trace );
		
		traceEvent.fire();
		assertEquals( 2 , mockExecutor.trace );
		
		roster.suspendEvent( TraceEvent.EVENT_NAME );
		traceEvent.fire();
		assertEquals( 2 , mockExecutor.trace );
		traceEvent.fire();
		assertEquals( 2 , mockExecutor.trace );
		
		roster.reinstateEvent( TraceEvent.EVENT_NAME );
		traceEvent.fire();
		assertEquals( 3 , mockExecutor.trace );
		traceEvent.fire();
		assertEquals( 4 , mockExecutor.trace );
		
	}
	
	class MockTraceExecutor implements IExecutor {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		int trace = 0;
		
		public void execute(TraceEvent event) {
			trace++;
		}

		public void execute(DebugEvent event) {
		}

		public void execute(InfoEvent event) {
		}

		public void execute(WarnEvent event) {
		}

		public void execute(ErrorEvent event) {
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
