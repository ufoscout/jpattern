package com.jpattern.service.log.reader;

import com.jpattern.core.BaseApplicationTest;
import com.jpattern.core.IProvider;
import com.jpattern.core.command.ACommand;
import com.jpattern.core.command.ACommandResult;
import com.jpattern.logger.ILogger;
import com.jpattern.shared.util.GenericWrapper;

/**
 * 
 * @author Francesco Cina'
 *
 * 6 May 2011
 */
public class TailCommandTest extends BaseApplicationTest {

	private ILoggerReaderService loggerReaderService;
	private ILogger logger;

	protected void setUp() throws Exception {
		super.setUp();
		
		loggerReaderService = getProvider().getLoggerReaderService();
		logger = getProvider().getLoggerService().logger(this.getClass());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testTailWeb1() throws Exception {
		IMessageReader aMessageReader = loggerReaderService.messageReader();
		logger.debug("","messaggio di debug");

		
		System.out.println("--- READ : " + aMessageReader.read() + " ---");	
		System.out.println("--- READ : " + aMessageReader.read() + " ---");
		
		logger.info("","messaggio di info");
		logger.error("","messaggio di error");
		System.out.println("--- READ : " + aMessageReader.read() + " ---");
	}
	
	
	public void testTailWeb2() throws Exception {
		
		logger.info("","messaggio di info");
		logger.error("","messaggio di error");
		
		GenericWrapper<IQueueMessage> resultMessage = new GenericWrapper<IQueueMessage>(null);
		
		ACommand<IProvider> tailCommand = new TailCommand(resultMessage, 0, new NullMessageFilter());
		ACommandResult result = tailCommand.exec(getProvider());
		assertTrue(result.isValid());
		
		assertTrue( resultMessage.getValue().getMessage().length() > 0 );
		System.out.println("--- message.toString() : " + resultMessage.getValue().getMessage() + " ---");
		tailCommand.exec(getProvider());
		System.out.println("--- message.toString() : " + resultMessage.getValue().getMessage() + " ---");
		
	}

}
