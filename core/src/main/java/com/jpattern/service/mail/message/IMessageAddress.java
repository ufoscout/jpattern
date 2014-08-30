package com.jpattern.service.mail.message;

import java.util.List;

/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 01/set/09 21:47:43
 *
 * @version $Id$
 */
public interface IMessageAddress {

    void from(IRecipient aRecipient);
    
    void to(List<IRecipient> aRecipients);
    
    void user(String username);
    void password (String password);
    
    boolean valid();
    
}
