package com.jpattern.rest.command;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jpattern.BaseTest;
import com.jpattern.rest.command.ObjectToJsonStreamCommand;
import com.jpattern.rest.command.ObjectToJsonStringCommand;
import com.jpattern.shared.command.IBaseCommandResult;
import com.jpattern.shared.command.BaseCommandResult;
import com.jpattern.logger.SystemOutLoggerFactory;
import com.jpattern.shared.result.ErrorMessage;
import com.jpattern.shared.result.IResult;
import com.jpattern.shared.result.facade.CommandFacadeResult;
import com.jpattern.shared.result.facade.ICommandFacadeResult;

/**
 * 
 * @author Francesco Cina'
 *
 * 11/mag/2011
 */
public class ObjectToJsonStreamCommandTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testObjectToJsonStream() throws Exception {
		
		String entry1 = "entry1_" + new Date().getTime();
		String entry2 = "entry2_" + new Date().getTime();
		List<String> arrayList = new ArrayList<String>();
		arrayList.add(entry1);
		arrayList.add(entry2);
		IResult commandResult = new BaseCommandResult();
		commandResult.getErrorMessages().add(new ErrorMessage(entry2, entry1, new String[]{entry2,entry1,entry2}));
		
		ICommandFacadeResult<List<String>> commandFacadeResult = new CommandFacadeResult<List<String>>(commandResult, arrayList);
		StringBuffer outJsonString = new StringBuffer();
		IRestCommand command = new ObjectToJsonStringCommand(commandFacadeResult, outJsonString);
		command.logger(new SystemOutLoggerFactory());
		IBaseCommandResult result = command.exec();
		System.out.println( "ObjectToJsonStringCommand result: " + outJsonString );
		assertTrue( result.isValid() );
		
		OutputStream outputStream = new ByteArrayOutputStream();
		command = new ObjectToJsonStreamCommand(commandFacadeResult, outputStream);
		command.logger(new SystemOutLoggerFactory());
		result = command.exec();
		assertTrue( result.isValid() );
		
		String streamJsonResult = outputStream.toString();
		System.out.println( "ObjectToJsonStreamCommand result: " + streamJsonResult );
		
		assertTrue( streamJsonResult.equals(outJsonString.toString()) );
		
	}

}
