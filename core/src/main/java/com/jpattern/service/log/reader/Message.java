package com.jpattern.service.log.reader;

import java.io.Serializable;
/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 06/ago/09 13:06:28
 *
 * @version $Id$
 */
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    private String _message;

    public Message(String aMessage) {
            _message = aMessage;
    }
    
    public final String message() {
        return _message;
    }
}
