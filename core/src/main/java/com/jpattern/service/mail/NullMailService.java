package com.jpattern.service.mail;

import java.io.Serializable;

/**
 * 
 * @author Francesco Cina'
 *
 * 12/ago/2010
 */
public class NullMailService implements IMailService, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "";
	}

	@Override
	public IMailSender mailSender() {
		return new NullMailSender();
	}

	@Override
	public void stopService() {
	}

}
