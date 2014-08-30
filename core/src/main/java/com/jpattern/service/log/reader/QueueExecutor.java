package com.jpattern.service.log.reader;

import com.jpattern.service.log.AExecutor;
import com.jpattern.service.log.IExecutor;
import com.jpattern.service.log.NullExecutor;
import com.jpattern.service.log.event.DebugEvent;
import com.jpattern.service.log.event.ErrorEvent;
import com.jpattern.service.log.event.InfoEvent;
import com.jpattern.service.log.event.TraceEvent;
import com.jpattern.service.log.event.WarnEvent;

/**
 * @author Francesco Cinà 07/ago/2009
 */
public class QueueExecutor extends AExecutor {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IQueueMessages queueMessages;

    public QueueExecutor(IQueueMessages aQueueMessages) {
        this(aQueueMessages, new NullExecutor());
    }    
    

    public QueueExecutor(IQueueMessages aQueueMessages, IExecutor aExecutor) {
    	super(aExecutor);
        this.queueMessages = aQueueMessages;
    }

    public void what(InfoEvent aEvent) {
        queueMessages.offer( getMessageFormatter().toString(aEvent.getName(), aEvent.getMessage()));
    }

    public void what(DebugEvent aEvent) {
    	queueMessages.offer( getMessageFormatter().toString(aEvent.getName(), aEvent.getMessage()));
    }

    public void what(ErrorEvent aEvent) {
    	queueMessages.offer( getMessageFormatter().toStringWithStackTrace(aEvent.getName(), aEvent.getMessage()));
    }
    
    public void what(TraceEvent aEvent) {
    	queueMessages.offer( getMessageFormatter().toString(aEvent.getName(), aEvent.getMessage()));
    }

    public void what(WarnEvent aEvent) {
    	queueMessages.offer( getMessageFormatter().toString(aEvent.getName(), aEvent.getMessage()));
    }


}
