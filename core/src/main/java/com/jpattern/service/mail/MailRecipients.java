package com.jpattern.service.mail;

import java.util.ArrayList;

import com.jpattern.service.mail.message.IMessageAddress;
import com.jpattern.service.mail.message.IRecipient;
import com.jpattern.service.mail.message.IRecipients;
import com.jpattern.service.mail.message.Recipients;

/**
 * 
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 01/set/09 22:38:57
 *
 * @version $Id$
 */
public class MailRecipients implements IRecipients {

    private static final long serialVersionUID = 1L;
    private IRecipients _recipients;
    private ArrayList<IRecipient> _ccrecipients;
    private ArrayList<IRecipient> _bccrecipients;

    public MailRecipients() {
        _recipients = new Recipients();
        _ccrecipients = new ArrayList<IRecipient>();
        _bccrecipients = new ArrayList<IRecipient>();        
    }
    
    public void from(IRecipient aRecipient) {
        _recipients.from( aRecipient );
    }
    
    public void addRecipient(IRecipient aRecipient) {
        _recipients.addRecipient(aRecipient);
    }
    
 
    public void addRecipient(CCRecipient aRecipient) {
        _ccrecipients.add(aRecipient);
    }

    public void addRecipient(BCCRecipient aRecipient) {
        _bccrecipients.add(aRecipient);
        
    }

    public void writeOnMessage(MailMessageAddress aMessageAddress) {
       
    	aMessageAddress.cc(  _ccrecipients);
    	
        aMessageAddress.bcc(  _bccrecipients);
        
        writeOn(aMessageAddress);
    }
    
    public void writeOn(IMessageAddress aMessageAddress) {
   
    	_recipients.writeOn(aMessageAddress);
    }


}
