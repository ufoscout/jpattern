package com.jpattern.service.log;

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
public class SystemOutExecutor extends AExecutor {

	private static final long serialVersionUID = 1L;

    public SystemOutExecutor() {
    	this(new NullExecutor());
    }
    
    public SystemOutExecutor(IExecutor successor) {
    	super( successor );
    }

    public void what(InfoEvent event) {
        System.out.println( getMessageFormatter().toString(event.getName(), event.getMessage()) );
    }

    public void what(DebugEvent event) {
    	System.out.println( getMessageFormatter().toString(event.getName(), event.getMessage()) );
    }

    public void what(ErrorEvent event) {
    	System.out.println( getMessageFormatter().toStringWithStackTrace(event.getName(), event.getMessage()) );
    }
    
    public void what(TraceEvent event) {
    	System.out.println( getMessageFormatter().toString(event.getName(), event.getMessage()) );
    }

	public void what(WarnEvent event) {
		System.out.println( getMessageFormatter().toString(event.getName(), event.getMessage()) );		
	}

}
