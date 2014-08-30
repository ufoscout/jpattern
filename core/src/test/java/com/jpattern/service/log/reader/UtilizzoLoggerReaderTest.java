package com.jpattern.service.log.reader;

import com.jpattern.core.BaseApplicationTest;
import com.jpattern.logger.ILogger;
import com.jpattern.service.log.mock.WrapperReaderLogger;
import com.jpattern.service.log.mock.WrapperRunnableApplication;
import com.jpattern.service.log.reader.ILoggerReaderService;
import com.jpattern.service.log.reader.MessageFilter;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/apr/2010
 */
public class UtilizzoLoggerReaderTest extends BaseApplicationTest {

    
    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testUtilizzo() throws Exception {
        
        ILogger logger = getProvider().getLoggerService().logger(this.getClass());
        ILoggerReaderService loggerReader = getProvider().getLoggerReaderService();
        
        WrapperRunnableApplication wrapperApplication1 = new WrapperRunnableApplication(logger);
        WrapperRunnableApplication wrapperApplication2 = new WrapperRunnableApplication(logger);
        WrapperRunnableApplication wrapperApplication3 = new WrapperRunnableApplication(logger);
        
        WrapperReaderLogger  wrapperReaderLogger1 = new WrapperReaderLogger("WrapperError:", loggerReader,  new MessageFilter("[ERROR]"));
        WrapperReaderLogger  wrapperReaderLogger2 = new WrapperReaderLogger("WrapperUfo:", loggerReader, new MessageFilter("[UFO]"));
        WrapperReaderLogger  wrapperReaderLogger3 = new WrapperReaderLogger("WrapperTutto:", loggerReader);
        
        Thread t1 = new Thread (wrapperApplication1);
        Thread t2 = new Thread (wrapperApplication2);
        Thread t3 = new Thread (wrapperApplication3);
        Thread loggerviewer1 = new Thread (wrapperReaderLogger1);
        Thread loggerviewer2 = new Thread (wrapperReaderLogger2);
        Thread loggerviewer3 = new Thread (wrapperReaderLogger3);
        
        t1.start();
        t2.start();
        t3.start();
        loggerviewer1.start();
        loggerviewer2.start();
        loggerviewer3.start();
        
        stopAll(1000, wrapperApplication1, wrapperApplication2, wrapperApplication3, wrapperReaderLogger1, wrapperReaderLogger2, wrapperReaderLogger3);
        
    }

    private void stopAll(long milleseconds, WrapperRunnableApplication wrapperApplication1, WrapperRunnableApplication wrapperApplication2, WrapperRunnableApplication wrapperApplication3, 
    		WrapperReaderLogger wrapperReaderLogger1, WrapperReaderLogger wrapperReaderLogger2, WrapperReaderLogger wrapperReaderLogger3) throws InterruptedException {

            Thread.sleep(milleseconds);
            wrapperApplication1.stop();
            wrapperApplication2.stop();
            wrapperApplication3.stop();
            wrapperReaderLogger1.stop();
            wrapperReaderLogger2.stop();
            wrapperReaderLogger3.stop();
    }
}
