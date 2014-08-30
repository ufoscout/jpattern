package com.jpattern.service.log.reader;

import com.jpattern.core.BaseTest;
import com.jpattern.core.SystemProvider;
import com.jpattern.logger.ILogger;
import com.jpattern.logger.NullLoggerCallback;
import com.jpattern.service.log.IRoster;
import com.jpattern.service.log.Logger;
import com.jpattern.service.log.Roster;
import com.jpattern.service.log.event.DebugEvent;
import com.jpattern.service.log.event.ErrorEvent;
import com.jpattern.service.log.event.ITrigger;
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
public class LoggerReaderServiceTest extends BaseTest {

    private final int queueLength = 10;

    private IQueueMessages queueInfo;

    private IRoster eventRoster;

    private ILogger logger;

	private SystemProvider applicationProvider;

    @Override
	protected void setUp() throws Exception {
        super.setUp();


        InfoEvent info = new InfoEvent();
        ErrorEvent error = new ErrorEvent();
        DebugEvent debug = new DebugEvent();
        TraceEvent trace = new TraceEvent();
        WarnEvent warn = new WarnEvent();
        
        queueInfo = new QueueMessages(10);
        
        QueueExecutor codaExecutor = new QueueExecutor(queueInfo);

        ITrigger triggerEvent = new Trigger(codaExecutor);
       
        eventRoster = new Roster(triggerEvent);
        
        eventRoster.addEvent(trace);
        eventRoster.addEvent(debug);
        eventRoster.addEvent(info);
        eventRoster.addEvent(warn);
        eventRoster.addEvent(error);

        logger = new Logger(this.getClass(), trace, debug, info, warn, error, new NullLoggerCallback());

        applicationProvider = new SystemProvider();
        applicationProvider.setLoggerReaderServiceBuilder(new LoggerReaderServiceBuilder(queueInfo));
    }

    @Override
	protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testLoggerReaderService1() {

        ILoggerReaderService aLoggerReaderService = applicationProvider.getLoggerReaderService();

        MockApplication mockApplication = new MockApplication(logger);

        for (int i = 0; i < queueLength + 5; i++) {
            mockApplication.faiQualcheCosaInInfo();
            mockApplication.faiQualcheCosaInDebug();
            mockApplication.faiQualcheCosaInErrore();
        }

        for (int i = 0; i < queueLength - 1; i++) {
        	String result = aLoggerReaderService.messageReader().read().getMessage();
            System.out.println( i + " = " + result);
        }
        //System.out.println("=== " + aLoggerReaderService.infoMessage());
        assertNotSame("", aLoggerReaderService.messageReader().read());

        assertTrue( aLoggerReaderService.messageReader().read().getMessage().length() > 0);
        
       // assertTrue( aLoggerReaderService.infoMessage().contains("[INFO]" ) );
      //  assertTrue( aLoggerReaderService.debugMessage().contains("[DEBUG]") );
        String result = aLoggerReaderService.messageReader().read().getMessage();
        System.out.println(result);
        assertTrue( result.contains("[ERROR]") );

    }

}
