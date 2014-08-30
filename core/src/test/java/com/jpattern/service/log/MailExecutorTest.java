package com.jpattern.service.log;

import java.util.ArrayList;
import java.util.List;


import com.jpattern.core.BaseApplicationTest;
import com.jpattern.service.log.IExecutor;
import com.jpattern.service.log.MailExecutor;
import com.jpattern.service.log.SystemOutExecutor;
import com.jpattern.service.log.event.DebugEvent;
import com.jpattern.service.log.event.ErrorEvent;
import com.jpattern.service.log.event.InfoEvent;
import com.jpattern.service.log.event.TraceEvent;
import com.jpattern.service.log.event.WarnEvent;
import com.jpattern.service.mail.IMailSender;
import com.jpattern.service.mail.IMailService;
import com.jpattern.service.mail.MailRecipients;
import com.jpattern.service.mail.MailService;
import com.jpattern.service.mail.TransportMailMessage;

/**
 * 
 * @author Francesco Cina'
 *
 * 12/ago/2010
 */
public class MailExecutorTest extends BaseApplicationTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testMailExecutor() throws Exception {
		MockMailSender mockMailSender = new MockMailSender();
		IMailService mailService = new MailService(mockMailSender);
		List<String> mailTo = new ArrayList<String>();
		mailTo.add("mario@poste.it");
		mailTo.add("carlo@poste.it");
		String mailFrom = "mago@wizard.it";
		String mailSubject = "test mail!";
		
		IExecutor mailExecutor = new MailExecutor(mailService, mailTo, mailFrom, mailSubject);
		mailExecutor.setLoggerLevel(ILoggerService.ERROR);
		IExecutor executor = new SystemOutExecutor( mailExecutor);
		
		executor.execute( new DebugEvent());
		executor.execute( new TraceEvent());
		executor.execute( new InfoEvent());
		executor.execute( new WarnEvent());
		
		assertFalse(mockMailSender.send);
		assertFalse(mockMailSender.recipiensAdded);
		
		executor.execute( new ErrorEvent());
		
		assertTrue(mockMailSender.send);
		assertTrue(mockMailSender.recipiensAdded);
		
	}
	
	
	class MockMailSender implements IMailSender {

		boolean recipiensAdded = false;
		boolean send = false;
		
		public void recipients(MailRecipients aRecipients) {
			System.out.println("recipients added");
			recipiensAdded = true;
		}

		public boolean send(TransportMailMessage aMessage) {
			System.out.println("INVIATO");
			send = true;
			return false;
		}
	}
}
