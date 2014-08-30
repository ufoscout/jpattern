package com.jpattern.service.log.reader;

import java.io.Serializable;

/**
 * 
 * @author Francesco Cina
 *
 * 31/lug/2011
 */
public interface IMessageReader extends Serializable {

	/**
	 * read the first message in the queue.
	 * It's the same of read(-1);
	 * @return
	 */
	IQueueMessage read();
	
	/**
	 * Read the message with a specific id
	 * @param lastReadedMessageId
	 * @return
	 */
	IQueueMessage read(long lastReadedMessageId);
	
}
