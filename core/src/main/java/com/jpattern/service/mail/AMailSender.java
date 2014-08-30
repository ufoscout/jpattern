package com.jpattern.service.mail;

import java.util.Date;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

/**
 * 
 * @author Francesco Cina
 *
 * 29/giu/2011
 */
public abstract class AMailSender implements IMailSender {

	private MailRecipients _recipients;
	
	@Override
	public void recipients(MailRecipients aRecipients) {
	    _recipients = aRecipients;
	}
	
	@Override
	public boolean send(TransportMailMessage aMessage) {
	    try {
	        
	    	Session session = getSession(); 
	        MimeMessage mimeMessage = new MimeMessage(session);
	        mimeMessage.setSubject(aMessage.subject().content());
	        mimeMessage.setSentDate(new Date());
	        
	        MailMessageAddress mailMessageAddress = new MailMessageAddress(mimeMessage);
	        _recipients.writeOnMessage(mailMessageAddress);
	      
	        if (!mailMessageAddress.valid()) {
	            return false;
	        }
	        mimeMessage.setContent(aMessage.asMultipart());
	      
	        Transport.send(mimeMessage);
	      
	        return true;            
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    	return false;
	    }
	}

	protected abstract Session getSession(); 
}
