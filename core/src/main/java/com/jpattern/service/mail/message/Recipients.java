package com.jpattern.service.mail.message;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 01/set/09 20:58:25
 *
 * @version $Id$
 */
public class Recipients implements IRecipients {
    

        private static final long serialVersionUID = 1L;
        private List<IRecipient> _recipients;
        private IRecipient _fromRecipient;

        public Recipients() {
            _recipients = new ArrayList<IRecipient>();
        }
        
        public void from(IRecipient aRecipient) {
            _fromRecipient = aRecipient;
        }
        
        public void addRecipient(IRecipient aRecipient) {
                _recipients.add(aRecipient);
        }
        
        public void addRecipient (AuthenticadRecipient authenticadRecipient)
        {
        	_recipients.add(authenticadRecipient);
        }
        
        public void writeOn(IMessageAddress aMessageAddress) {
            aMessageAddress.from(_fromRecipient);
            aMessageAddress.to( _recipients );
        }        
    
}
