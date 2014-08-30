package com.jpattern.rest.command;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jpattern.BaseTest;
import com.jpattern.rest.command.JsonToObjectCommand;
import com.jpattern.rest.command.ObjectToJsonStringCommand;
import com.jpattern.shared.command.IBaseCommand;
import com.jpattern.shared.command.IBaseCommandResult;
import com.jpattern.shared.command.BaseCommandResult;
import com.jpattern.logger.SystemOutLoggerFactory;
import com.jpattern.shared.result.ErrorMessage;
import com.jpattern.shared.result.IResult;
import com.jpattern.shared.result.facade.CommandFacadeResult;
import com.jpattern.shared.result.facade.ICommandFacadeResult;
import com.jpattern.shared.util.GenericWrapper;

/**
 * 
 * @author Francesco Cina'
 *
 * 11/mag/2011
 */
public class JsonCommandTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testObjectToJson1() throws Exception {
		List<String> list = new ArrayList<String>();
		StringBuffer jsonString = new StringBuffer();
		IBaseCommand command = new ObjectToJsonStringCommand(list , jsonString);
		IBaseCommandResult result = command.exec();
		assertTrue( result.isValid() );
		assertTrue( jsonString.length()>0 );
		System.out.println("jsonString: " + jsonString);
	}
	
	public void testObjectToJson2() throws Exception {
		List<String> list = new ArrayList<String>();
		
		String entry1 = "entry1_" + new Date().getTime();
		list.add(entry1);
		String entry2 = "entry2_" + new Date().getTime();
		list.add(entry2);
		StringBuffer jsonString = new StringBuffer();
		IRestCommand command = new ObjectToJsonStringCommand(list , jsonString);
		command.logger(new SystemOutLoggerFactory());
		IBaseCommandResult result = command.exec();
		assertTrue( result.isValid() );
		assertTrue( jsonString.length()>0 );
		System.out.println("jsonString: " + jsonString);
		
		assertTrue(jsonString.toString().contains(entry1));
		assertTrue(jsonString.toString().contains(entry2));
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testJsonToObject1() throws Exception {
		String entry1 = "entry1_" + new Date().getTime();
		String entry2 = "entry2_" + new Date().getTime();
		StringBuffer json = new StringBuffer("[\"" + entry1 + "\",\"" + entry2 + "\"]");

		GenericWrapper<List> resultWrapper = new GenericWrapper<List>(null);
		
		IRestCommand command = new JsonToObjectCommand<List>(List.class, resultWrapper, json);
		command.logger(new SystemOutLoggerFactory());
		IBaseCommandResult result = command.exec();
		assertTrue( result.isValid() );
		
		List<String> returnedObject = (List<String>) resultWrapper.getValue();
		
		System.out.println("returnedObject: " + returnedObject);
		assertNotNull(returnedObject);
		assertEquals( 2 , returnedObject.size() );
		assertTrue( returnedObject.contains(entry1) );
		assertTrue( returnedObject.contains(entry2) );
	}
	
	public void testJsonResult() throws Exception {
		String entry1 = "entry1_" + new Date().getTime();
		String entry2 = "entry2_" + new Date().getTime();
		List<String> arrayList = new ArrayList<String>();
		arrayList.add(entry1);
		arrayList.add(entry2);
		IResult commandResult = new BaseCommandResult();
		commandResult.getErrorMessages().add(new ErrorMessage(entry2, entry1, new String[]{entry2,entry1,entry2}));
		
		ICommandFacadeResult<List<String>> commandFacadeResult = new CommandFacadeResult<List<String>>(commandResult, arrayList);
		
		StringBuffer outJsonString = new StringBuffer();
		GenericWrapper<MyCommandResult> resultWrapper = new GenericWrapper<MyCommandResult>(null);
		
		IRestCommand command = new ObjectToJsonStringCommand(commandFacadeResult, outJsonString);
		command = new JsonToObjectCommand<MyCommandResult>(MyCommandResult.class, resultWrapper, outJsonString, command);
		command.logger(new SystemOutLoggerFactory());
		IBaseCommandResult result = command.exec();
		
		System.out.println( "outJsonString: " + outJsonString );
		System.out.println( result.asString() );
		assertTrue( result.isValid() );
		
		assertEquals( commandFacadeResult.getErrorMessages().size() , resultWrapper.getValue().getErrorMessages().size() );
		assertEquals( commandFacadeResult.getReturnedObject().size() , resultWrapper.getValue().getReturnedObject().size() );
		
		
		List<String> returnList = resultWrapper.getValue().getReturnedObject();
		assertTrue( returnList.containsAll( arrayList ) );
		
		MyErrorMessage returnedErrorMessage = resultWrapper.getValue().getErrorMessages().get(0);
		
		assertEquals( entry2 , returnedErrorMessage.getName() ) ;
		assertEquals( entry1 , returnedErrorMessage.getMessage() ) ;
		assertEquals( entry2 , returnedErrorMessage.getParameters().get(0) ) ;
		assertEquals( entry1 , returnedErrorMessage.getParameters().get(1) ) ;
		assertEquals( entry2 , returnedErrorMessage.getParameters().get(2) ) ;
		
	}
	
}
