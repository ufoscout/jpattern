package com.jpattern.service.log.reader;


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
import com.jpattern.service.log.reader.IQueueMessages;
import com.jpattern.service.log.reader.NullQueueMessages;
import com.jpattern.service.log.reader.QueueExecutor;
import com.jpattern.service.log.reader.QueueMessages;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/apr/2010
 */
public class EventQueueTest extends BaseTest {
    
    private Roster eventRoster;
	private IQueueMessages queueError;
	private IQueueMessages queueDebug;
	private IQueueMessages queueInfo;
	private ILogger logger;

	protected void setUp() throws Exception {
        super.setUp();
        
        queueInfo = new NullQueueMessages();
        queueDebug = new QueueMessages(2);
        queueError = new QueueMessages(3);
        
        InfoEvent infoEvent = new InfoEvent();
        ErrorEvent errorEvent = new ErrorEvent();
        DebugEvent debugEvent = new DebugEvent(); 
        TraceEvent traceEvent = new TraceEvent(); 
        WarnEvent warnEvent = new WarnEvent();
        
        SystemOutExecutor printWriterExecutor = new SystemOutExecutor();

        QueueExecutor codaExecutor = new QueueExecutor( queueError, printWriterExecutor);
        
        Trigger triggerEvent = new Trigger(codaExecutor);

        eventRoster = new Roster(triggerEvent);
        eventRoster.addEvent(infoEvent);
        eventRoster.addEvent(errorEvent);
        eventRoster.addEvent(debugEvent);
        eventRoster.addEvent(traceEvent);
        eventRoster.addEvent(warnEvent);

        
        logger = new Logger( this.getClass() , traceEvent, debugEvent, infoEvent, warnEvent, errorEvent, new NullLoggerCallback());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    
    public void testEventCoda_1() throws Exception {
        
        assertEquals(0,queueInfo.size());
        assertEquals(0,queueDebug.size());
        assertEquals(0,queueError.size());
        
        MockApplication application = new MockApplication(logger);
        application.faiQualcheCosaInInfo();
        application.faiQualcheCosaInErrore();
        application.faiQualcheCosaInErroreSenzaEccezione();
        application.faiQualcheCosaInDebug();
        
        assertEquals(0,queueInfo.size());
        assertEquals(0,queueDebug.size());
        assertEquals(3,queueError.size());
        
        application.faiQualcheCosaInInfo();
        application.faiQualcheCosaInErrore();
        application.faiQualcheCosaInErroreSenzaEccezione();
        application.faiQualcheCosaInDebug();
        
        application.faiQualcheCosaInInfo();
        application.faiQualcheCosaInErrore();
        application.faiQualcheCosaInErroreSenzaEccezione();
        application.faiQualcheCosaInDebug();
        
        application.faiQualcheCosaInInfo();
        application.faiQualcheCosaInErrore();
        application.faiQualcheCosaInErroreSenzaEccezione();
        application.faiQualcheCosaInDebug();
        
        assertEquals(0,queueInfo.size());
        assertEquals(0,queueDebug.size());
        assertEquals(3,queueError.size());
    }
    
 public void testEventCoda_2() throws Exception {
                
        assertEquals(0,queueInfo.size());
        assertEquals(0,queueDebug.size());
        assertEquals(0,queueError.size());
        
        MockApplication application = new MockApplication(logger);
        
        eventRoster.suspendEvent("INFO");
        application.faiQualcheCosaInInfo();
        application.faiQualcheCosaInErrore();
        application.faiQualcheCosaInErroreSenzaEccezione();
        application.faiQualcheCosaInDebug();
        
        assertEquals(0,queueInfo.size());
        assertEquals(0,queueDebug.size());
        assertEquals(3,queueError.size());
        
        eventRoster.suspendEvent("ERROR");
        eventRoster.reinstateEvent("INFO");
        application.faiQualcheCosaInInfo();
        application.faiQualcheCosaInErrore();
        application.faiQualcheCosaInErroreSenzaEccezione();
        application.faiQualcheCosaInDebug();
        
        assertEquals(0,queueInfo.size());
        assertEquals(0,queueDebug.size());
        assertEquals(3,queueError.size());
        
        eventRoster.suspendEvent("DEBUG");
        application.faiQualcheCosaInInfo();
        application.faiQualcheCosaInErrore();
        application.faiQualcheCosaInErroreSenzaEccezione();
        application.faiQualcheCosaInDebug();
         
        assertEquals(0,queueInfo.size());
        assertEquals(0,queueDebug.size());
        assertEquals(3,queueError.size());
    }
}
