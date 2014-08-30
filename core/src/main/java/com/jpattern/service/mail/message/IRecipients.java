package com.jpattern.service.mail.message;

import java.io.Serializable;


/**
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 01/set/09 21:44:24
 * @version $Id$
 */
public interface IRecipients extends Serializable {

    void from(IRecipient aRecipient);

    void addRecipient(IRecipient aRecipient);

    void writeOn(IMessageAddress aMessageAddress);
    
    
}
