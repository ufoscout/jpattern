package com.jpattern.service.mail;

import java.util.Iterator;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.jpattern.service.mail.message.IMessageAddress;
import com.jpattern.service.mail.message.IRecipient;

/**
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 01/set/09 21:51:20
 * @version $Id$
 */
public class MailMessageAddress implements IMessageAddress {

    private MimeMessage _mimeMessage;

    private boolean _valid = true;


    public MailMessageAddress(MimeMessage mimeMessage) throws Exception{
        _mimeMessage = mimeMessage;
    }

    public void from(IRecipient aRecipient) {
        try {
        	
            _mimeMessage.setFrom(new InternetAddress(aRecipient.name()));
        }
        catch (AddressException e) {
        	_valid = false;
        }
        catch (MessagingException e) {
            _valid = false;
        }

    }

    public boolean valid() {
        return _valid;
    }

    public void to(List<IRecipient> aRecipients) {
    	
        addRecipients(aRecipients, Message.RecipientType.TO);
    }
    public void bcc(List<IRecipient> aRecipients) {
        addRecipients(aRecipients, Message.RecipientType.BCC);
    }

    public void cc(List<IRecipient> aRecipients) {
        addRecipients(aRecipients, Message.RecipientType.CC);
    }    

    private void addRecipients(List<IRecipient> aRecipients, RecipientType aRecipientType) {
        try {
            _mimeMessage.setRecipients(aRecipientType, toArray(aRecipients));
        }
        catch (MessagingException e) {
            _valid = false;
        }
    }

    private InternetAddress[] toArray(List<IRecipient> recipients) {

        InternetAddress[] internetAddress = new InternetAddress[recipients.size()];
        Iterator<IRecipient> iterator = recipients.listIterator();
        int i = 0;
        while (iterator.hasNext()) {
            forEach(i, internetAddress, (IRecipient) iterator.next());
            i++;
        }

        return internetAddress;
    }

    private void forEach(int counter, InternetAddress[] internetAddress, IRecipient recipient) {
        try {
            internetAddress[counter] = new InternetAddress(recipient.name());
        }
        catch (AddressException e) {
        	_valid = false;
        }
    }

	public void password(String password) {
	}

	public void user(String username) {
		
	}



}
