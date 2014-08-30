package com.jpattern.service.mail;

import javax.mail.PasswordAuthentication;

import com.jpattern.core.util.ValueUtilExt;

/**
 * 
 * @author Francesco Cina'
 *
 * 16 Feb 2011
 */
public class MailSenderAuthenticator extends javax.mail.Authenticator {
	
	private PasswordAuthentication authentication;

	public MailSenderAuthenticator(String username, String password) {
		authentication = new PasswordAuthentication(ValueUtilExt.stringNotNull(username,""), ValueUtilExt.stringNotNull(password,""));
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return authentication;
	}
}
