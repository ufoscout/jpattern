package com.jpattern.service.log.event;

import java.util.Date;

import com.jpattern.service.log.IExecutor;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/apr/2010
 */
public class ErrorEvent extends AEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String EVENT_NAME = "ERROR";
	private MessageEx message;

	public ErrorEvent() {
		super( EVENT_NAME );
	}
	
	public void execute(IExecutor executor) {
		executor.execute(this);
	}
	
	public MessageEx getMessage() {
		if (message == null) {
			return new MessageEx("","","", new Date(), new NullException());
		}
		return message;
	}

	public synchronized void fireMessage(MessageEx message) {
		if ( !isIgnoredPath( message.getClassName() ) ) {
			this.message = message;
			fire();
		}
	}
	
}
