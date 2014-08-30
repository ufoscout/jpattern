package com.jpattern.gwt.client.command;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Timer;
import com.jpattern.gwt.client.BaseGwtTestCase;
import com.jpattern.gwt.client.NullServerCallService;
import com.jpattern.gwt.client.command.CommandChainAsynch;
import com.jpattern.gwt.client.command.ICommandCallBack;
import com.jpattern.gwt.client.communication.ServerCallGetCommand;
import com.jpattern.gwt.client.util.GenericWrapper;
import com.jpattern.gwt.shared.IWebResultString;
import com.jpattern.shared.result.IErrorMessage;

/**
 * 
 * @author Francesco Cina'
 *
 */
public class CommandChainAsynchTestGWT extends BaseGwtTestCase {

	protected static final int SLEEP_FOR = 200;
	private long startTime = 0;
	private MockCommand myCommand1;
	private MockCommand myCommand2;
	private MockCommand myCommand3;
	private GenericWrapper<IWebResultString> callResult0;
	private GenericWrapper<IWebResultString> callResult1;
	private GenericWrapper<IWebResultString> callResult2;
	private GenericWrapper<IWebResultString> callResult3;
	private GenericWrapper<IWebResultString> callResult4;
	private GenericWrapper<IWebResultString> callResult5;


	@Override
	protected void gwtTestCaseSetUp() throws Exception {
		assertTrue( !(getProvider().getServerCallService() instanceof NullServerCallService) );
	}

	@Override
	protected void gwtTestCaseTearDown() throws Exception {
	}

	public void testCommandChainAsynch1() throws Exception {
		  Timer timer = new Timer() {

				public void run() {
			    	String baseurl = GWT.getHostPageBaseURL().replace(GWT.getModuleName() + "/", ""); 
			    	System.out.println( "webapp context url = " + baseurl);
					String url = baseurl + "rest/echo/wait500ms";
					url = URL.encode(url);
					Map<String, String> keyValuesMap = new HashMap<String, String>();
					keyValuesMap.put("sleepFor", "" + SLEEP_FOR);
					
					callResult0 = new GenericWrapper<IWebResultString>(IWebResultString.class);
					callResult1 = new GenericWrapper<IWebResultString>(IWebResultString.class);
					callResult2 = new GenericWrapper<IWebResultString>(IWebResultString.class);
					callResult3 = new GenericWrapper<IWebResultString>(IWebResultString.class);
					callResult4 = new GenericWrapper<IWebResultString>(IWebResultString.class);
					callResult5 = new GenericWrapper<IWebResultString>(IWebResultString.class);
					
					ACommand commandGet0 = new ServerCallGetCommand<IWebResultString>(keyValuesMap, new StringBuffer(url), callResult0 );
					ACommand commandGet1 = new ServerCallGetCommand<IWebResultString>(keyValuesMap, new StringBuffer(url), callResult1 );
					ACommand commandGet2 = new ServerCallGetCommand<IWebResultString>(keyValuesMap, new StringBuffer(url), callResult2 );
					ACommand commandGet3 = new ServerCallGetCommand<IWebResultString>(keyValuesMap, new StringBuffer(url), callResult3 );
					ACommand commandGet4 = new ServerCallGetCommand<IWebResultString>(keyValuesMap, new StringBuffer(url), callResult4 );
					ACommand commandGet5 = new ServerCallGetCommand<IWebResultString>(keyValuesMap, new StringBuffer(url), callResult5 );
					
					CommandChainAsynch commandChain = new CommandChainAsynch();
					myCommand1 = new MockCommand(false, false);
					myCommand2 = new MockCommand(false, true);
					myCommand3 = new MockCommand(false, true);
					commandChain.addCommand(commandGet0);
					commandChain.addCommand(myCommand1);
					commandChain.addCommand(commandGet1);
					commandChain.addCommand(commandGet2);
					commandChain.addCommand(myCommand2);
					commandChain.addCommand(commandGet3);
					commandChain.addCommand(myCommand3);
					commandChain.addCommand(commandGet4);
					commandChain.addCommand(commandGet5);
					
					commandChain.visit(getProvider());
					MyEvent event = new MyEvent(2, true, true, true);
					
					startTime = new Date().getTime();
					System.out.println("Operation start time: " + startTime);
					commandChain.exec(event);
					
			    }
			  };

			  delayTestFinish(10000);
			  timer.schedule(10);
	}
	
	
	class MyEvent implements ICommandCallBack {

		private final int errorSize;
		private final boolean com1;
		private final boolean com2;
		private final boolean com3;

		public MyEvent(int errorSize, boolean com1, boolean com2, boolean com3) {
			this.errorSize = errorSize;
			this.com1 = com1;
			this.com2 = com2;
			this.com3 = com3;
		}

		@Override
		public void callback(ICommandResult commandResult) {
			
			for (IErrorMessage errorMessage : commandResult.getErrorMessages()) {
				System.out.println( "found error: [" + errorMessage.getName() + "] - [" + errorMessage.getMessage() + "]");
			}
			
			
			assertEquals( errorSize, commandResult.getErrorMessages().size() );
			assertEquals( com1 , myCommand1.isExecuted() );
			assertEquals( com2 , myCommand2.isExecuted() );
			assertEquals( com3 , myCommand3.isExecuted() );
			
			assertTrue( callResult0.getValue().getReturnedObject().contains("Slept for " + SLEEP_FOR + "ms") );
			assertTrue( callResult1.getValue().getReturnedObject().contains("Slept for " + SLEEP_FOR + "ms") );
			assertTrue( callResult2.getValue().getReturnedObject().contains("Slept for " + SLEEP_FOR + "ms") );
			assertTrue( callResult3.getValue().getReturnedObject().contains("Slept for " + SLEEP_FOR + "ms") );
			assertTrue( callResult4.getValue().getReturnedObject().contains("Slept for " + SLEEP_FOR + "ms") );
			assertTrue( callResult5.getValue().getReturnedObject().contains("Slept for " + SLEEP_FOR + "ms") );
			
			long endTime = new Date().getTime();
			long operationDuration = endTime - startTime;
			
			System.out.println("Operation started at: " + startTime);
			System.out.println("Operation ended at  : " + endTime);
			System.out.println("Operation duration  : " + operationDuration);
			
			finishTest();
		}
		
	}
	
}
