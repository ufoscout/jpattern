package com.jpattern.service.mail;

/**
 * 
 * @author Francesco Cina'
 *
 * 25/set/2011
 */
public class MailService implements IMailService {

	public static String MAIL_SERVICE = "mailService";
	private IMailSender senderMail;

	public MailService(IMailSender mailsender) {
        senderMail = mailsender;
    }

	@Override
    public String getName() {
        return MAIL_SERVICE;
    }

    @Override
    public IMailSender mailSender() {
        return senderMail;
    }

	@Override
	public void stopService() {
	}

}
