package com.jpattern.service.mail;

import com.jpattern.core.IServiceBuilder;

/**
 * 
 * @author Francesco Cina
 *
 * 29/giu/2011
 */
public class MailServiceBuilder implements IServiceBuilder<IMailService> {

	private IMailSender mailSender;

	public MailServiceBuilder(IMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public IMailService buildService() {
		return new MailService(mailSender);
	}

}
