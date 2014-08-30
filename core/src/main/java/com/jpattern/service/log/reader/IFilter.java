package com.jpattern.service.log.reader;

import java.io.Serializable;

/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 10/ago/09 12:59:06
 *
 * @version $Id$
 */
public interface IFilter extends Serializable  {

	IQueueMessage what(IQueueMessage message);

}
