package com.jpattern.service.mail;

import java.util.Properties;

import javax.mail.Session;


/**
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 01/set/09 20:30:57
 * @version $Id$
 */
public class MailSender extends AMailSender{

	private Properties _serverproperties;

	private MailSenderAuthenticator authenticator;

    public MailSender(IMailConfig aMailConfig) {

        _serverproperties = new Properties();
        _serverproperties.put("mail.smtp.host", aMailConfig.getStmphost());
        _serverproperties.put("mail.smtp.port", aMailConfig.getStmpport());
        
        if (aMailConfig.isRequiredAuthentication()) {
        	authenticator = new MailSenderAuthenticator(aMailConfig.getUsername(), aMailConfig.getPassword());
			_serverproperties.setProperty("mail.smtp.submitter", authenticator.getPasswordAuthentication().getUserName());
        	_serverproperties.setProperty("mail.smtp.auth", "true");
        }
    }

	@Override
	protected Session getSession() {
		return Session.getInstance(_serverproperties, authenticator);
	}

}
