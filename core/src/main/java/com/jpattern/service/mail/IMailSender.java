package com.jpattern.service.mail;

/**
 * 
 * @author Francesco Cina' 12/nov/2010 - 18.02.49
 * @version $Id$ 
 *
 */
public interface IMailSender {
	
	 void recipients(MailRecipients aRecipients);
	 
	 boolean send(TransportMailMessage aMessage) throws Exception;
	 
}
