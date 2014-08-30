package com.jpattern.service.log;

import java.util.List;

import com.jpattern.service.log.event.DebugEvent;
import com.jpattern.service.log.event.ErrorEvent;
import com.jpattern.service.log.event.InfoEvent;
import com.jpattern.service.log.event.TraceEvent;
import com.jpattern.service.log.event.WarnEvent;
import com.jpattern.service.mail.IMailSender;
import com.jpattern.service.mail.IMailService;
import com.jpattern.service.mail.MailRecipients;
import com.jpattern.service.mail.TransportMailMessage;
import com.jpattern.service.mail.message.FromRecipient;
import com.jpattern.service.mail.message.Recipient;
import com.jpattern.service.mail.message.TransportTextMessage;

/**
 * 
 * @author Francesco Cina'
 *
 * 12/ago/2010
 */
public class MailExecutor extends AExecutor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IMailService mailService;
	private List<String> mailTo;
	private String mailFrom;
	private String mailSubject;

	public MailExecutor(IMailService mailService, List<String> mailTo, String mailFrom, String mailSubject) {
		this(mailService, mailTo, mailFrom, mailSubject, new NullExecutor());
	}
	
	public MailExecutor(IMailService mailService, List<String> mailTo, String mailFrom, String mailSubject, IExecutor successor) {
		super(successor);
		this.mailService = mailService;
		this.mailTo = mailTo;
		this.mailFrom = mailFrom;
		this.mailSubject = mailSubject;
	}

    public void what(InfoEvent event) {
        sendMail( getMessageFormatter().toString(event.getName(), event.getMessage()) );
    }

    public void what(DebugEvent event) {
    	sendMail( getMessageFormatter().toString(event.getName(), event.getMessage()) );
    }

    public void what(ErrorEvent event) {
    	sendMail( getMessageFormatter().toStringWithStackTrace(event.getName(), event.getMessage()) );
    }
    
    public void what(TraceEvent event) {
    	sendMail( getMessageFormatter().toString(event.getName(), event.getMessage()) );
    }

	public void what(WarnEvent event) {
		sendMail( getMessageFormatter().toString(event.getName(), event.getMessage()) );		
	}
	

	private void sendMail(String messagge) {
		IMailSender mailSender = mailService.mailSender();
		MailRecipients recipients = new MailRecipients();
		TransportMailMessage transportMailMessage = new TransportMailMessage();
		
		// Creo mittente
		recipients.from(new FromRecipient(new Recipient(mailFrom)));

		// Creo destinatari
		for (String destinatario : mailTo) {
			recipients.addRecipient(new Recipient( destinatario ));
		}

		// creo il subject
		TransportTextMessage transportTextMessage = new TransportTextMessage(mailSubject);

		// creo il body
		TransportTextMessage transportBodyTextMessage = new TransportTextMessage(messagge);

		// creo la mail ed aggiungo subject e body
		transportMailMessage.subject(transportTextMessage);
		transportMailMessage.body(transportBodyTextMessage);
				
		// Aggiungo mittente e destinari al mailsender
		mailSender.recipients(recipients);

		// invio la mail
		try {
			mailSender.send(transportMailMessage);
		} catch (Exception e) {
		}		
	}
	
}
