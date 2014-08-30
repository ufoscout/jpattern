package com.jpattern.service.log.reader;

import java.util.List;
import java.util.Vector;

/**
 * @author Francesco Cina 07/ago/2009
 */

public class QueueMessages implements IQueueMessages {

	private static final long serialVersionUID = 1L;

	private int _maxCodaLenght = 1;

	private List<IQueueMessage> messageList = new Vector<IQueueMessage>();
	private long lastMessageId = 0l;
	private long firstMessageId = 0l;
	
    public QueueMessages(int maxCodaLength) {
        if (maxCodaLength > 1) {
            _maxCodaLenght = maxCodaLength;
        }
    }

    public int size() {
        return messageList.size();
    }

    public void offer(String aMessage) {
    	synchronized (messageList) {
    		if (messageList.size() >= _maxCodaLenght) {
   				 firstMessageId++;
    			 messageList.remove(0);
            }
   			 messageList.add(new QueueMessage(lastMessageId, aMessage, true));
   			 lastMessageId++;
		}
    }

    public IQueueMessage peek() {
    	synchronized (messageList) {
	        if (messageList.size() == 0) {
	            return new QueueMessage(0, "", false);
	        }
	        return messageList.get(0);
    	}
    }

    public IQueueMessage poll() {
    	synchronized (messageList) {
	        if (messageList.size() == 0) {
	        	return new QueueMessage(0, "", false);
	        }
	        IQueueMessage result = messageList.get(0);
	        messageList.remove(0);
	        return result;
    	}
    }

	public IQueueMessage get(long index) {
    	synchronized (messageList) {
	        if (index < firstMessageId) {
	        	return peek();
	        }
	        if (index >= lastMessageId) {
	            return new QueueMessage(index, "", false);
	        }
	        return  messageList.get( (int) (index - firstMessageId) );
    	}
	}

}
