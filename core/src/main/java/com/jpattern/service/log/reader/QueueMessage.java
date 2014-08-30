package com.jpattern.service.log.reader;

/**
 * 
 * @author Francesco Cina
 *
 * 31/lug/2011
 */
public class QueueMessage implements IQueueMessage {

	private final long messageId;
	private final String message;
	private final boolean valid;

	public QueueMessage(long messageId, String message, boolean valid) {
		this.messageId = messageId;
		this.message = message;
		this.valid = valid;
	}
	
	@Override
	public long getMessageId() {
		return messageId;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public boolean isValid() {
		return valid;
	}

}
