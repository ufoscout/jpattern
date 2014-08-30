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
 * 12/ago/2010
 */
public class WarningExecutorStrategy implements IExecutorStrategy {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void exec(AExecutor executor, TraceEvent event) {
	}

	public void exec(AExecutor executor, DebugEvent event) {
	}

	public void exec(AExecutor executor, InfoEvent event) {
	}

	public void exec(AExecutor executor, WarnEvent event) {
		executor.what(event);
	}

	public void exec(AExecutor executor, ErrorEvent event) {
		executor.what(event);
	}

}
