package com.jpattern.service.log.mock;

import com.jpattern.service.log.reader.IFilter;
import com.jpattern.service.log.reader.ILoggerReaderService;
import com.jpattern.service.log.reader.NullMessageFilter;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/apr/2010
 */
public class WrapperReaderLogger implements Runnable {

    private ILoggerReaderService _loggerReader;
    private boolean _start= true;
	private IFilter _filter;
	private String _wrapperName;

    public WrapperReaderLogger(String aWrapperName, ILoggerReaderService loggerReader) {
    	this(aWrapperName, loggerReader, new NullMessageFilter());
    }
    
    public WrapperReaderLogger(String aWrapperName, ILoggerReaderService loggerReader, IFilter aFilter) {
    	_wrapperName = aWrapperName;
    	_loggerReader=  loggerReader;
        _filter = aFilter;
       }
    
    public void run() {
     
        while (_start) {
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println( "=================");
            System.out.println( "========" + _wrapperName + ": " + _loggerReader.messageReader(_filter).read() + "=========");
            System.out.println( "=================");
        }
        
    }
    
    public void stop() {
        _start = false;
    }

}
