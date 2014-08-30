package com.jpattern.service.log;


import java.io.PrintWriter;

import com.jpattern.service.log.event.DebugEvent;
import com.jpattern.service.log.event.ErrorEvent;
import com.jpattern.service.log.event.InfoEvent;
import com.jpattern.service.log.event.TraceEvent;
import com.jpattern.service.log.event.WarnEvent;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/apr/2010
 */
public class PrintWriterExecutor extends AExecutor {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PrintWriter printWriter;

    public PrintWriterExecutor(PrintWriter writer) {
    	this(writer, new NullExecutor());
    }
    
    public PrintWriterExecutor(PrintWriter writer, IExecutor successor) {
    	super( successor );
        this.printWriter = writer;
    }

    public void what(InfoEvent event) {
        printWriter.println( getMessageFormatter().toString(event.getName(), event.getMessage()) );
    }

    public void what(DebugEvent event) {
    	printWriter.println( getMessageFormatter().toString(event.getName(), event.getMessage()) );
    }

    public void what(ErrorEvent event) {
    	printWriter.println( getMessageFormatter().toStringWithStackTrace(event.getName(), event.getMessage()) );
    }
    
    public void what(TraceEvent event) {
    	printWriter.println( getMessageFormatter().toString(event.getName(), event.getMessage()) );
    }

	public void what(WarnEvent event) {
		printWriter.println( getMessageFormatter().toString(event.getName(), event.getMessage()) );		
	}

}
