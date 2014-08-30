package com.jpattern.service.log;

import com.jpattern.core.IServiceBuilder;
import com.jpattern.service.log.event.DebugEvent;
import com.jpattern.service.log.event.ErrorEvent;
import com.jpattern.service.log.event.ITrigger;
import com.jpattern.service.log.event.InfoEvent;
import com.jpattern.service.log.event.TraceEvent;
import com.jpattern.service.log.event.Trigger;
import com.jpattern.service.log.event.WarnEvent;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/apr/2010
 */
public class LoggerServiceBuilder implements IServiceBuilder<ILoggerService> {

	private IExecutor executor;

	public LoggerServiceBuilder(IExecutor executor) {
		this.executor = executor;
	}

	public ILoggerService buildService() {
		
		TraceEvent trace = new TraceEvent();
		DebugEvent debug = new DebugEvent();
		InfoEvent info = new InfoEvent();
		WarnEvent warn = new WarnEvent();
		ErrorEvent error = new ErrorEvent();
		
		ITrigger trigger = new Trigger( executor );
		IRoster roster = new Roster( trigger );
		
		roster.addEvent(trace);
		roster.addEvent(debug);
		roster.addEvent(info);
		roster.addEvent(warn);
		roster.addEvent(error);
		
		ILoggerService logger = new LoggerService( roster, trace, debug, info, warn, error);
		
		return logger;
	}

}
