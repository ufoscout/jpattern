package com.jpattern.gwt.client.communication.rest;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Timer;
import com.jpattern.gwt.client.BaseGwtTestCase;
import com.jpattern.gwt.client.NullServerCallService;
import com.jpattern.gwt.client.command.ACommand;
import com.jpattern.gwt.client.command.ICommandCallBack;
import com.jpattern.gwt.client.command.ICommandResult;
import com.jpattern.gwt.client.communication.ServerCallGetCommand;
import com.jpattern.gwt.client.util.GenericWrapper;
import com.jpattern.gwt.shared.IResultObject;
import com.jpattern.gwt.shared.IWebResultResultObject;

/**
 * 
 * @author Francesco Cina'
 *
 * 12 Apr 2011
 */
public class GetCallCommandTestGwt extends BaseGwtTestCase {

    private String input = "GetProxyTestGwt";
	private int repeat = 5;
	
	@Override
	protected void gwtTestCaseSetUp() throws Exception {
		assertTrue( !(getProvider().getServerCallService() instanceof NullServerCallService) );
	}

	@Override
	protected void gwtTestCaseTearDown() throws Exception {
	}

	public void testGetCallCommand() throws Exception {
		  Timer timer = new Timer() {

				public void run() {
			    	String baseurl = GWT.getHostPageBaseURL().replace(GWT.getModuleName() + "/", ""); 
			    	System.out.println( "webapp context url = " + baseurl);
					String url = baseurl + "rest/echo";
					url = URL.encode(url);
					Map<String, String> keyValuesMap = new HashMap<String, String>();
					keyValuesMap.put("input", input);
					keyValuesMap.put("repeat", "" + repeat);
					
					GenericWrapper<IWebResultResultObject> callResult = new GenericWrapper<IWebResultResultObject>(IWebResultResultObject.class);
					ACommand command = new ServerCallGetCommand<IWebResultResultObject>(keyValuesMap, new StringBuffer(url), callResult ); 
					command.visit(getProvider());
					command.exec(new MyEvent(callResult));
										
			    }
			  };

			  delayTestFinish(10000);
			  timer.schedule(10);
	}
	
	
	class MyEvent implements ICommandCallBack {

		private final GenericWrapper<IWebResultResultObject> callResult;

		public MyEvent(GenericWrapper<IWebResultResultObject> callResult) {
			this.callResult = callResult;
		}

		@Override
		public void callback(ICommandResult commandResult) {
			
			assertEquals( 0 , commandResult.getErrorMessages().size() );
			assertNotNull( callResult.getValue() );
			assertNotNull( callResult.getValue().getReturnedObject() );
			assertEquals( Response.SC_OK , commandResult.getResponseCode() );
			
			IResultObject resultObject = callResult.getValue().getReturnedObject();
			assertEquals( repeat , resultObject.getMessages().size() );
			
			for (int i=0; i<resultObject.getMessages().size(); i++) {
				assertEquals( i + input, resultObject.getMessages().get(i));
			}
			
			assertTrue( resultObject.isValid() );
			assertEquals("GET", resultObject.getName() );
			
			System.out.println( "OnSuccess end" );
			finishTest();
			
		}

	}
}
