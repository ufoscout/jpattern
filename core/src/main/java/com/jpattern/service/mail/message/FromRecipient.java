package com.jpattern.service.mail.message;


/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 01/set/09 21:01:21
 *
 * @version $Id$
 */
public class FromRecipient implements IRecipient {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IRecipient _recipient;

    public FromRecipient(IRecipient aRecipient) {
            _recipient = aRecipient;
    }

    public String name() {
        return _recipient.name();
    }

}
