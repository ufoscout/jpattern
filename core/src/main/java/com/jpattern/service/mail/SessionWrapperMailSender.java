package com.jpattern.service.mail;

import javax.mail.Session;


/**
 * 
 * @author Francesco Cina
 *
 * 29/giu/2011
 */
public class SessionWrapperMailSender extends AMailSender { 

	private final Session session;

    public SessionWrapperMailSender(Session session) {
        this.session = session;
    }

	@Override
	protected Session getSession() {
		return session;
	}

}
