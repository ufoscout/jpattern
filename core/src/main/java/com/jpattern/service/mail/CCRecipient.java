package com.jpattern.service.mail;

import com.jpattern.service.mail.message.IRecipient;

/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 01/set/09 20:52:02
 *
 * @version $Id$
 */
public class CCRecipient  implements IRecipient {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IRecipient _recipient;

    public CCRecipient(IRecipient aRecipient) {
            _recipient = aRecipient;
    }

    public String name() {
        return _recipient.name();
    }

}
