package com.jpattern.service.log.reader;
/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 10/ago/09 19:02:52
 *
 * @version $Id$
 */
public class NullMessageFilter implements IFilter {

	private static final long serialVersionUID = 1L;

	public IQueueMessage what(IQueueMessage message) {
		return message;
	}

}
