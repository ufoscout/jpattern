package com.jpattern.service.log.reader;

import java.util.Iterator;

/**
 * 
 * @author Francesco Cinà 07/ago/2009
 *
 */
public class NullQueueMessages implements IQueueMessages{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void offer(String aMessage) {
	}

	public IQueueMessage peek() {
		return new QueueMessage(0, "", false);
	}

	public IQueueMessage poll() {
		return new QueueMessage(0, "", false);
	}

	public int size() {
		return 0;
	}

	public IQueueMessage get(long index) {
		return new QueueMessage(index, "", false);
	}

	public Iterator<String> iterator() {

		return new NullIterator();
	}

}
