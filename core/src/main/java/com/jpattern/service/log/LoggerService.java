package com.jpattern.service.log;

import java.util.HashMap;
import java.util.Map;

import com.jpattern.logger.ILogger;
import com.jpattern.logger.ILoggerCallback;
import com.jpattern.logger.NullLoggerCallback;
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
public class LoggerService implements ILoggerService {

	private static final long serialVersionUID = 1L;
	public static final String LOGGER_SERVICE = "LoggerService";
	private IRoster roster;
	private ErrorEvent error;
	private WarnEvent warn;
	private InfoEvent info;
	private DebugEvent debug;
	private TraceEvent trace;
	private Map<String, ILoggerServiceIgnorePathStrategy> ignorePathStrategyMap = new HashMap<String, ILoggerServiceIgnorePathStrategy>();

	public LoggerService( IRoster roster , TraceEvent trace , DebugEvent debug , InfoEvent info , WarnEvent warn , ErrorEvent error ) {
		this.trace = trace; 
		this.debug = debug;
		this.info = info;
		this.warn = warn;
		this.error = error;
		this.roster = roster;
		
		ignorePathStrategyMap.put(OFF, new OffLoggerServiceIgnorePathStrategy());
		ignorePathStrategyMap.put(TRACE, new TraceLoggerServiceIgnorePathStrategy());
		ignorePathStrategyMap.put(DEBUG, new DebugLoggerServiceIgnorePathStrategy());
		ignorePathStrategyMap.put(INFO, new InfoLoggerServiceIgnorePathStrategy());
		ignorePathStrategyMap.put(WARNING, new WarningLoggerServiceIgnorePathStrategy());
		ignorePathStrategyMap.put(ERROR, new ErrorLoggerServiceIgnorePathStrategy());
		
	}
	
	public ILogger logger(Class<?> aClass) {
		return logger(aClass, new NullLoggerCallback());
	}
	
	@Override
	public ILogger logger(Class<?> aClass, ILoggerCallback loggerCallback) {
		return new Logger(aClass, trace, debug, info, warn, error, loggerCallback);
	}

	public String getName() {
		return LOGGER_SERVICE;
	}

	public void setDebugLoggerLevel() {
		roster.suspendEvent( TraceEvent.EVENT_NAME );
		roster.reinstateEvent( DebugEvent.EVENT_NAME );
		roster.reinstateEvent( InfoEvent.EVENT_NAME );
		roster.reinstateEvent( WarnEvent.EVENT_NAME );
		roster.reinstateEvent( ErrorEvent.EVENT_NAME );
	}

	public void setDebugLoggerLevel(String classpath) {
		trace.addIgnoredPath(classpath);
		debug.removeIgnoredPath(classpath);
		info.removeIgnoredPath(classpath);
		warn.removeIgnoredPath(classpath);
		error.removeIgnoredPath(classpath);
	}

	public void setErrorLoggerLevel() {
		roster.suspendEvent( TraceEvent.EVENT_NAME );
		roster.suspendEvent( DebugEvent.EVENT_NAME );
		roster.suspendEvent( InfoEvent.EVENT_NAME );
		roster.suspendEvent( WarnEvent.EVENT_NAME );
		roster.reinstateEvent( ErrorEvent.EVENT_NAME );
	}

	public void setErrorLoggerLevel(String classpath) {
		trace.addIgnoredPath(classpath);
		debug.addIgnoredPath(classpath);
		info.addIgnoredPath(classpath);
		warn.addIgnoredPath(classpath);
		error.removeIgnoredPath(classpath);
	}

	public void setInfoLoggerLevel() {
		roster.suspendEvent( TraceEvent.EVENT_NAME );
		roster.suspendEvent( DebugEvent.EVENT_NAME );
		roster.reinstateEvent( InfoEvent.EVENT_NAME );
		roster.reinstateEvent( WarnEvent.EVENT_NAME );
		roster.reinstateEvent( ErrorEvent.EVENT_NAME );
	}

	public void setInfoLoggerLevel(String classpath) {
		trace.addIgnoredPath(classpath);
		debug.addIgnoredPath(classpath);
		info.removeIgnoredPath(classpath);
		warn.removeIgnoredPath(classpath);
		error.removeIgnoredPath(classpath);
	}

	public void setTraceLoggerLevel() {
		roster.reinstateEvent( TraceEvent.EVENT_NAME );
		roster.reinstateEvent( DebugEvent.EVENT_NAME );
		roster.reinstateEvent( InfoEvent.EVENT_NAME );
		roster.reinstateEvent( WarnEvent.EVENT_NAME );
		roster.reinstateEvent( ErrorEvent.EVENT_NAME );
	}

	public void setTraceLoggerLevel(String classpath) {
		trace.removeIgnoredPath(classpath);
		debug.removeIgnoredPath(classpath);
		info.removeIgnoredPath(classpath);
		warn.removeIgnoredPath(classpath);
		error.removeIgnoredPath(classpath);
	}

	public void setWarnLoggerLevel() {
		roster.suspendEvent( TraceEvent.EVENT_NAME );
		roster.suspendEvent( DebugEvent.EVENT_NAME );
		roster.suspendEvent( InfoEvent.EVENT_NAME );
		roster.reinstateEvent( WarnEvent.EVENT_NAME );
		roster.reinstateEvent( ErrorEvent.EVENT_NAME );
	}

	public void setWarnLoggerLevel(String classpath) {
		trace.addIgnoredPath(classpath);
		debug.addIgnoredPath(classpath);
		info.addIgnoredPath(classpath);
		warn.removeIgnoredPath(classpath);
		error.removeIgnoredPath(classpath);
	}
	
	public void setOffLoggerLevel() {
		roster.suspendEvent( TraceEvent.EVENT_NAME );
		roster.suspendEvent( DebugEvent.EVENT_NAME );
		roster.suspendEvent( InfoEvent.EVENT_NAME );
		roster.suspendEvent( WarnEvent.EVENT_NAME );
		roster.suspendEvent( ErrorEvent.EVENT_NAME );
	}

	public void setOffLoggerLevel(String classpath) {
		trace.addIgnoredPath(classpath);
		debug.addIgnoredPath(classpath);
		info.addIgnoredPath(classpath);
		warn.addIgnoredPath(classpath);
		error.addIgnoredPath(classpath);
	}

	public void setLoggerLevel(String classpath, String loggerLevel) {
		if (ignorePathStrategyMap.containsKey(loggerLevel)) {
			ignorePathStrategyMap.get(loggerLevel).ignorePath(this, classpath);
		}
		
	}

	@Override
	public void stopService() {
	}

}
