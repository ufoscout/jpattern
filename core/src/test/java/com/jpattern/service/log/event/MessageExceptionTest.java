package com.jpattern.service.log.event;

import java.util.Date;


import com.jpattern.core.BaseTest;
import com.jpattern.service.log.event.MessageEx;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/apr/2010
 */
public class MessageExceptionTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testPrintException() throws Exception {
		MessageEx exceptionMessage = new MessageEx( "" , "" , "" , new Date() );
		
		assertEquals( "" , exceptionMessage.getExceptionType() );
		
		assertEquals( "" , exceptionMessage.getExceptionStackTrace() );
	}
}
