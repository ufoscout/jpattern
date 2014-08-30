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
 * 09/apr/2010
 */
public interface IExecutor extends Serializable {
	
	void execute(TraceEvent event);

	void execute(DebugEvent event);
	
	void execute(InfoEvent event);
	
	void execute(WarnEvent event);
	
	void execute(ErrorEvent event);
	
	void setLoggerLevel(String loggerLevel);
	
	void addIgnoredPath(String classpath);

	void removeIgnoredPath(String classpath);

	boolean isIgnoredPath(String classpath);
	
}
