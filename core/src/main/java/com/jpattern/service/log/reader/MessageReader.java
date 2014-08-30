package com.jpattern.service.log.reader;

/**
 * 
 * @author Francesco Cina' 10/ago/2009
 *
 */
public class MessageReader implements IMessageReader {

    private static final long serialVersionUID = 1L;
    private IQueueMessages _queue;

	public MessageReader(IQueueMessages aQueue) {
		_queue = aQueue;
	}

	public IQueueMessage read() {
		return _queue.peek();
	}

	public IQueueMessage read(long lastReadedMessageId) {
		return _queue.get(lastReadedMessageId);
	}

}
