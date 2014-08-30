package com.jpattern.service.mail.message;
/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 01/set/09 21:13:13
 *
 * @version $Id$
 */
public class TransportTextMessage  implements ITransportTextMessage{


    private static final long serialVersionUID = 1L;
    private String _object;

    public TransportTextMessage(String aObject) {
        _object = aObject;
    }
    
    public String content() {
        return _object;
    }

}
