package com.jpattern.service.log.event;

import com.jpattern.service.log.IExecutor;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/apr/2010
 */
public class TraceEvent extends AEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String EVENT_NAME = "TRACE";
	private IMessage message;

	public TraceEvent() {
		super( EVENT_NAME );
	}
	
	public void execute(IExecutor executor) {
		executor.execute(this);
	}
	
	public IMessage getMessage() {
		if (message == null) {
			return new NullMessage();
		}
		return message;
	}

	public synchronized void fireMessage(IMessage message) {
		if ( !isIgnoredPath( message.getClassName() ) ) {
			this.message = message;
			fire();
		}
	}

}
