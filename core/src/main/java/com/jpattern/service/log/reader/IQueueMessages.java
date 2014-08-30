package com.jpattern.service.log.reader;

import java.io.Serializable;


/**
 * 
 * @author Francesco Cinà 07/ago/2009
 *
 */
public interface IQueueMessages extends Serializable {
	
	int size();
	
	IQueueMessage get(long messageId);

	IQueueMessage peek();

	IQueueMessage poll();
	
	void offer(String aMessage);
	
//	Iterator<String> iterator();

}
