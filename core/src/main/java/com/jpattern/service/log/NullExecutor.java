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
public class NullExecutor implements IExecutor {

	private static final long serialVersionUID = 1L;

	public void execute(TraceEvent event) {
	}

	public void execute(DebugEvent event) {
	}

	public void execute(InfoEvent event) {
	}

	public void execute(WarnEvent event) {
	}

	public void execute(ErrorEvent event) {
	}

	public void setLoggerLevel(String loggerLevel) {
	}

	@Override
	public void addIgnoredPath(String classpath) {
	}

	@Override
	public void removeIgnoredPath(String classpath) {
	}

	@Override
	public boolean isIgnoredPath(String classpath) {
		return false;
	}

}
