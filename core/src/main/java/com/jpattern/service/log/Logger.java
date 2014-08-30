package com.jpattern.service.log;

import java.util.Date;

import com.jpattern.logger.ILogger;
import com.jpattern.logger.ILoggerCallback;
import com.jpattern.service.log.event.DebugEvent;
import com.jpattern.service.log.event.ErrorEvent;
import com.jpattern.service.log.event.InfoEvent;
import com.jpattern.service.log.event.Message;
import com.jpattern.service.log.event.MessageEx;
import com.jpattern.service.log.event.TraceEvent;
import com.jpattern.service.log.event.WarnEvent;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/apr/2010
 */
public class Logger implements ILogger {

	private static final long serialVersionUID = 1L;
	private final TraceEvent trace;
	private final DebugEvent debug;
	private final InfoEvent info;
	private final WarnEvent warn;
	private final ErrorEvent error;
	private final String className;
	private final ILoggerCallback loggerCallback;
	private final String DEFAULT_METHOD_NAME = "";

	public Logger(Class<?> aClass, TraceEvent trace, DebugEvent debug, InfoEvent info, WarnEvent warn, ErrorEvent error, ILoggerCallback loggerCallback) {
		this.trace = trace;
		this.debug = debug;
		this.info = info;
		this.warn = warn;
		this.error = error;
		this.loggerCallback = loggerCallback;
		className = aClass.getName();
	}

	@Override
	public void debug(String method, String message) {
		debug.fireMessage( new Message( className , method , message , new Date() ) );
		loggerCallback.debug(method, message);
	}

	@Override
	public void error(String method, String message) {
		error.fireMessage( new MessageEx( className , method , message , new Date() ) );
		loggerCallback.error(method, message);
	}

	@Override
	public void error(String method, String message, Throwable e) {
		error.fireMessage( new MessageEx( className , method , message , new Date() , e ) );
		loggerCallback.error(method, message, e);
	}

	@Override
	public void info(String method, String message) {
		info.fireMessage( new Message( className , method , message , new Date() ) );
		loggerCallback.info(method, message);
	}

	@Override
	public void trace(String method, String message) {
		trace.fireMessage( new Message( className , method , message , new Date() ) );
		loggerCallback.trace(method, message);
	}

	@Override
	public void warn(String method, String message) {
		warn.fireMessage( new Message( className , method , message , new Date() ) );
		loggerCallback.warn(method, message);
	}

	@Override
	public void debug(String message) {
		debug.fireMessage( new Message( className , DEFAULT_METHOD_NAME , message , new Date() ) );
		loggerCallback.debug( message);
	}

	@Override
	public void error( String message) {
		error.fireMessage( new MessageEx( className , DEFAULT_METHOD_NAME , message , new Date() ) );
		loggerCallback.error( message);
	}

	@Override
	public void error( String message, Throwable e) {
		error.fireMessage( new MessageEx( className , DEFAULT_METHOD_NAME , message , new Date() , e ) );
		loggerCallback.error( message, e);
	}

	@Override
	public void info( String message) {
		info.fireMessage( new Message( className , DEFAULT_METHOD_NAME , message , new Date() ) );
		loggerCallback.info( message);
	}

	@Override
	public void trace( String message) {
		trace.fireMessage( new Message( className , DEFAULT_METHOD_NAME , message , new Date() ) );
		loggerCallback.trace( message);
	}

	@Override
	public void warn(String message) {
		warn.fireMessage( new Message( className , DEFAULT_METHOD_NAME , message , new Date() ) );
		loggerCallback.warn(message);
	}
}
