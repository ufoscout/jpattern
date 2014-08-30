package com.jpattern.service.mail;

import java.util.Date;


import com.dumbster.smtp.SimpleSmtpServer;
import com.dumbster.smtp.SmtpMessage;
import com.jpattern.core.BaseApplicationTest;
import com.jpattern.core.IProvider;
import com.jpattern.core.command.ACommand;
import com.jpattern.core.command.ACommandResult;
import com.jpattern.service.mail.BCCRecipient;
import com.jpattern.service.mail.CCRecipient;
import com.jpattern.service.mail.MailConfig;
import com.jpattern.service.mail.MailRecipients;
import com.jpattern.service.mail.MailSender;
import com.jpattern.service.mail.SendMailCommand;
import com.jpattern.service.mail.TransportMailMessage;
import com.jpattern.service.mail.message.FromRecipient;
import com.jpattern.service.mail.message.Recipient;
import com.jpattern.service.mail.message.TransportTextMessage;

/**
 * 
 * @author Francesco Cina'
 *
 * 04/mag/2010
 */
public class MailSendTest extends BaseApplicationTest {

	protected void setUp() throws Exception {
		super.setUp();
		System.out.println("START " + new Date());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		System.out.println("STOP  " + new Date());
	}

//	public void testSendMail() throws Exception {
//
////		SimpleSmtpServer server = SimpleSmtpServer.start(MAIL_SERVER_PORT);
//		
//		String to = "marco.maiolico@poste.it";
//		String subject = "ciao ciao";
//		String text = "ciao ciao sono il messaggio";
//		String from = "francesco.cina@postecom.it";
//		
//		Properties properties = new Properties();
//		properties.put("mail.smtp.host", "localhost");
//		properties.put("mail.smtp.port", MAIL_SERVER_PORT );
//		
//		Session session = Session.getDefaultInstance(properties, null);
//
//		MimeMessage message = new MimeMessage(session);
//
//		message.setFrom(new InternetAddress(from));
//		message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
//		message.setRecipient(Message.RecipientType.BCC, new InternetAddress("francesco.cina@postecom.it"));
//		message.setRecipient(Message.RecipientType.CC, new InternetAddress("francesco.cina@postecom.it"));
//
//		message.setSubject(subject);
//		message.setText(text);
//
//		Transport.send(message);
//		
////	    server.stop();
//
////	    assertTrue(server.getReceivedEmailSize() == 1);
////	    SmtpMessage email = (SmtpMessage) server.getReceivedEmail().next();
////	    assertEquals( subject , email.getHeaderValue("Subject") );
////	    assertEquals( from , email.getHeaderValue("From") );
////	    assertEquals( to , email.getHeaderValue("To") );
////	    assertTrue( email.getBody().contains(text) );
//	}

	public void testSendMailWithObject() throws Exception {
		
		SimpleSmtpServer server = SimpleSmtpServer.start(MAIL_SERVER_PORT);

		MailConfig mailConfig = new MailConfig();
		mailConfig.setStmphost("localhost");
		mailConfig.setStmpport("" + MAIL_SERVER_PORT);
		AMailSender mailSender = new MailSender(mailConfig);

		MailRecipients recipients = new MailRecipients();

		Recipient recipient = new Recipient("marco.maiolico@poste.it");
		recipients.addRecipient(recipient);

		Recipient recipientFROM = new Recipient("francesco.cina@postecom.it");
		FromRecipient fromrecipient = new FromRecipient(recipientFROM);
		recipients.from(fromrecipient);
		
		recipients.addRecipient( new CCRecipient( new Recipient( "marco.maiolico@poste.it") ) );
		recipients.addRecipient( new BCCRecipient( new Recipient( "marco.maiolico@poste.it") ) );

		mailSender.recipients(recipients);

		TransportTextMessage transportTextMessage = new TransportTextMessage("XXXX CON DATAciao ciao sono il titolo della mail");
		TransportTextMessage transportBodyTextMessage = new TransportTextMessage("ciao ciao sono il body della mail LEGGI STA DATA");

		TransportMailMessage transportMailMessage = new TransportMailMessage();
		transportMailMessage.subject(transportTextMessage);
		transportMailMessage.body(transportBodyTextMessage);
		
		assertTrue(mailSender.send(transportMailMessage));

	    server.stop();
	    assertEquals(1 , server.getReceivedEmailSize() );
	}

	public void testSendMailCommand() throws Exception {
		
		//String to = ("francesco.cina@postecom.it");
		SimpleSmtpServer server = SimpleSmtpServer.start(MAIL_SERVER_PORT);

		Thread.sleep(100);
		
		String to = ("marco.maiolico@poste.it");
		String from = "francesco.cina@poste.it";
		String subject = "XXXX titolo della mail dal SENDMAIL";
		String body = "ciao ciao sono il body della mail DAL SENDMAIL";
		
		ACommand<IProvider> command = new SendMailCommand(new StringBuffer(from) ,new StringBuffer(to) , new StringBuffer(subject), new StringBuffer(body));
        ACommandResult result = command.exec(getProvider());

        assertTrue(result.isValid());
        
	    server.stop();
	    Thread.sleep(100);
	    assertEquals(1 , server.getReceivedEmailSize() );
	    SmtpMessage email = (SmtpMessage) server.getReceivedEmail().next();
	    System.out.println("email.getBody() : " + email.getBody());
	    assertEquals( subject , email.getHeaderValue("Subject") );
	    assertEquals( from , email.getHeaderValue("From") );
	    assertEquals( to , email.getHeaderValue("To") );
	    assertTrue( email.getBody().contains(body) );
	    
	}

}
