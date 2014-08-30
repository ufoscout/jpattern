package com.jpattern.service.log.reader;


/**
 * 
 * @author Francesco Cinà 10/ago/2009
 *
 */
public class MessageFilter implements IFilter {

	private static final long serialVersionUID = 1L;
	private String _filterString;

	public MessageFilter(String aFilterString){
		_filterString = aFilterString;
	}

	public IQueueMessage what(IQueueMessage aMessage) {
		if (!aMessage.getMessage().contains( _filterString ))
			return new QueueMessage(0, "", false);
		return aMessage;
	}

}
