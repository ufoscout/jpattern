package com.jpattern.service.log.slf4j;

import org.slf4j.LoggerFactory;

import com.jpattern.service.log.AExecutor;
import com.jpattern.service.log.IExecutor;
import com.jpattern.service.log.NullExecutor;
import com.jpattern.service.log.event.DebugEvent;
import com.jpattern.service.log.event.ErrorEvent;
import com.jpattern.service.log.event.IMessage;
import com.jpattern.service.log.event.InfoEvent;
import com.jpattern.service.log.event.MessageEx;
import com.jpattern.service.log.event.TraceEvent;
import com.jpattern.service.log.event.WarnEvent;

/**
 * 
 * @author Francesco Cina'
 *
 * 12/ago/2010
 */
public class Slf4JExecutor extends AExecutor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Slf4JExecutor() {
		super(new NullExecutor());
	}
	
	public Slf4JExecutor(IExecutor successor) {
		super(successor);
	}

	@Override
	public void what(TraceEvent event) {
		IMessage message = event.getMessage();
		LoggerFactory.getLogger(message.getClassName()).trace(message.getMessage());
	}

	@Override
	public void what(DebugEvent event) {
		IMessage message = event.getMessage();
		LoggerFactory.getLogger(message.getClassName()).debug(message.getMessage());
	}

	@Override
	public void what(InfoEvent event) {
		IMessage message = event.getMessage();
		LoggerFactory.getLogger(message.getClassName()).info(message.getMessage());
	}

	@Override
	public void what(WarnEvent event) {
		IMessage message = event.getMessage();
		LoggerFactory.getLogger(message.getClassName()).warn(message.getMessage());
	}

	@Override
	public void what(ErrorEvent event) {
		MessageEx message = event.getMessage();
		LoggerFactory.getLogger(message.getClassName()).error(message.getMessage(), message.getException());
	}

}
