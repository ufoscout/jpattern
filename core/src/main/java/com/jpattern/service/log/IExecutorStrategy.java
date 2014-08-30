package com.jpattern.service.log;

import java.io.Serializable;

import com.jpattern.service.log.event.DebugEvent;
import com.jpattern.service.log.event.ErrorEvent;
import com.jpattern.service.log.event.InfoEvent;
import com.jpattern.service.log.event.TraceEvent;
import com.jpattern.service.log.event.WarnEvent;

/**
 * 
 * @author Francesco Cina'
 *
 * 12/ago/2010
 */
public interface IExecutorStrategy extends Serializable {

	void exec( AExecutor executor, TraceEvent event);
	void exec( AExecutor executor, DebugEvent event);
	void exec( AExecutor executor, InfoEvent event);
	void exec( AExecutor executor, WarnEvent event);
	void exec( AExecutor executor, ErrorEvent event);
	
}
