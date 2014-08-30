package com.jpattern.service.mail.message;

import java.io.Serializable;

/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 01/set/09 23:13:18
 *
 * @version $Id$
 */
public interface ITransportTextMessage extends Serializable {

    String content();
}
