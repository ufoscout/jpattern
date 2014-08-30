package com.jpattern.service.mail;

/**
 * 
 * @author Francesco Cina'
 *
 * 12/ago/2010
 */
public class NullMailSender implements IMailSender {

	public void recipients(MailRecipients aRecipients) {
	}

	public boolean send(TransportMailMessage aMessage) {
		return false;
	}

}
