package com.jpattern.service.log.reader;
/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 17/ago/09 20:24:36
 *
 * @version $Id$
 */
public class NullMessageReader implements IMessageReader {

    private static final long serialVersionUID = 1L;

    public IQueueMessage read() {
    	return new QueueMessage(0, "", false);
    }

	public IQueueMessage read(long lastReadedMessageId) {
		return new QueueMessage(lastReadedMessageId, "", false);
	}

}
