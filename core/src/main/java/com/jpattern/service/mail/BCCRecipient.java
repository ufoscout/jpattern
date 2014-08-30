package com.jpattern.service.mail;

import com.jpattern.service.mail.message.IRecipient;

/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 01/set/09 20:56:24
 *
 * @version $Id$
 */
public class BCCRecipient implements IRecipient {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IRecipient _recipient;

    public BCCRecipient(IRecipient aRecipient) {
            _recipient = aRecipient;
    }

    public String name() {
        return _recipient.name();
    }

}
