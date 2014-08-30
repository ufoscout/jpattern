package com.jpattern.service.log.event;

import com.jpattern.core.BaseTest;
import com.jpattern.logger.ILogger;
import com.jpattern.logger.NullLoggerCallback;
import com.jpattern.service.log.Logger;
import com.jpattern.service.log.SystemOutExecutor;
import com.jpattern.service.log.Roster;
import com.jpattern.service.log.event.DebugEvent;
import com.jpattern.service.log.event.ErrorEvent;
import com.jpattern.service.log.event.InfoEvent;
import com.jpattern.service.log.event.TraceEvent;
import com.jpattern.service.log.event.Trigger;
import com.jpattern.service.log.event.WarnEvent;
import com.jpattern.service.log.mock.MockApplication;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/apr/2010
 */
public class EventTest2 extends BaseTest {
    
    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    
    public void testEvent() throws Exception {
        
        InfoEvent infoEvent = new InfoEvent();
        ErrorEvent errorEvent = new ErrorEvent();
        DebugEvent debugEvent = new DebugEvent(); 
        TraceEvent traceEvent = new TraceEvent();
        WarnEvent warnEvent = new WarnEvent();
        
        SystemOutExecutor printWriterExecutor = new SystemOutExecutor();
//        PrintWriterErrorExecutor printWriterErrorExecutor = new PrintWriterErrorExecutor(pErrorWriter, printWriterExecutor);

        Trigger triggerEvent = new Trigger(printWriterExecutor);

        Roster eventRoster = new Roster(triggerEvent);
        eventRoster.addEvent(infoEvent);
        eventRoster.addEvent(errorEvent);
        eventRoster.addEvent(debugEvent);
        eventRoster.addEvent(traceEvent);
        eventRoster.addEvent(warnEvent);
        
        ILogger logger = new Logger(this.getClass() , traceEvent, debugEvent, infoEvent, warnEvent, errorEvent, new NullLoggerCallback());
        
        MockApplication application = new MockApplication(logger);
        application.faiQualcheCosaInInfo();
        application.faiQualcheCosaInErrore();
        application.faiQualcheCosaInErroreSenzaEccezione();
        application.faiQualcheCosaInDebug();
        
        
        System.out.println("=======================================");
        eventRoster.suspendEvent( "INFO");
        
        application.faiQualcheCosaInInfo();
        application.faiQualcheCosaInErrore();
        application.faiQualcheCosaInErroreSenzaEccezione();
        application.faiQualcheCosaInDebug();
        
        System.out.println("=======================================");
        eventRoster.reinstateEvent( "INFO");
        
        application.faiQualcheCosaInInfo();
        application.faiQualcheCosaInErrore();
        application.faiQualcheCosaInErroreSenzaEccezione();
        application.faiQualcheCosaInDebug();
        
        application.faiQualcheCosaInTrace();
        System.out.println("=======================================");
        eventRoster.suspendEvent( "TRACE");
        application.faiQualcheCosaInTrace();
    }
}
