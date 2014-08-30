package com.jpattern.service.log.reader;

/**
 * 
 * @author Francesco Cina
 *
 * 31/lug/2011
 */
public interface IQueueMessage {

	long getMessageId();
	
	String getMessage();
	
	boolean isValid();
	
}
