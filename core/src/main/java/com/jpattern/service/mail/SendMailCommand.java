package com.jpattern.service.mail;

import com.jpattern.core.IProvider;
import com.jpattern.core.command.ACommand;
import com.jpattern.core.command.ACommandResult;
import com.jpattern.service.mail.message.FromRecipient;
import com.jpattern.service.mail.message.Recipient;
import com.jpattern.service.mail.message.TransportTextMessage;
import com.jpattern.shared.result.ErrorMessage;

/**
 * 
 * @author Francesco Cina'
 * 
 *         04/nov/2009
 */
public class SendMailCommand extends ACommand<IProvider> {

    private static final String COMMAND_ACTION = "sendmail.failed";
    private static final String COMMAND = "SendMailCommand";

	private StringBuffer from;

	private StringBuffer to;

	private StringBuffer body;

	private StringBuffer subject;

	public SendMailCommand(StringBuffer from, StringBuffer to, StringBuffer subject, StringBuffer body) {
		this.from = from;
		this.to = to;
		this.body = body;
		this.subject = subject;
	}

	@Override
	protected void rollback(ACommandResult result) {
	}

	@Override
	protected void execute(ACommandResult result) {
		try {
			IMailService mailService = getProvider().getMailService();
			IMailSender mailSender = mailService.mailSender();
	
			MailRecipients recipients = new MailRecipients();
			TransportMailMessage transportMailMessage = new TransportMailMessage();
			
			createMailObjects(recipients, transportMailMessage);
					
			// Aggiungo mittente e destinari al mailsender
			mailSender.recipients(recipients);
	
			// invio la mail
			createMessages(result, mailSender.send(transportMailMessage));
		} catch (Exception e) {
			result.addErrorMessage(new ErrorMessage(COMMAND, e.getMessage()));
		}

	}

	protected void createMessages(ACommandResult result, boolean valid) {
		if (!valid) {
			result.getErrorMessages().add(new ErrorMessage(COMMAND, COMMAND_ACTION));
		}

	}

	private void createMailObjects(MailRecipients recipients, TransportMailMessage transportMailMessage) {

		// Creo mittente
		Recipient recipientFROM = new Recipient(from.toString());
		FromRecipient fromrecipient = new FromRecipient(recipientFROM);
		recipients.from(fromrecipient);

		// Creo destinatario
		recipients.addRecipient(new Recipient( to.toString() ));

		// creo il subject
		TransportTextMessage transportTextMessage = new TransportTextMessage(subject.toString());

		// creo il body
		TransportTextMessage transportBodyTextMessage = new TransportTextMessage(body.toString());

		// creo la mail ed aggiungo subject e body
		transportMailMessage.subject(transportTextMessage);
		transportMailMessage.body(transportBodyTextMessage);
	}

}
