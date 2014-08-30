package com.jpattern.gwt.client.communication.rest;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.jpattern.gwt.client.BaseGwtTestCase;
import com.jpattern.gwt.client.NullServerCallService;
import com.jpattern.gwt.client.command.ACommand;
import com.jpattern.gwt.client.command.ICommandCallBack;
import com.jpattern.gwt.client.command.ICommandResult;
import com.jpattern.gwt.client.communication.ServerCallPutCommand;
import com.jpattern.gwt.client.util.GenericWrapper;
import com.jpattern.gwt.shared.IResultObject;
import com.jpattern.gwt.shared.IWebResultResultObject;
import com.jpattern.gwt.shared.ResultObject;

/**
 * 
 * @author Francesco Cina'
 *
 * 12 Apr 2011
 */
public class PutCallCommandTestGwt extends BaseGwtTestCase {

    private String input = "PutProxyTestGwt";
    private ResultObject data = new ResultObject();
	
	@Override
	protected void gwtTestCaseSetUp() throws Exception {
		assertTrue( !(getProvider().getServerCallService() instanceof NullServerCallService) );
	}

	@Override
	protected void gwtTestCaseTearDown() throws Exception {
	}

	public void testPutCallCommand() throws Exception {
		  Timer timer = new Timer() {

				public void run() {
			    	String baseurl = GWT.getHostPageBaseURL().replace(GWT.getModuleName() + "/", ""); 
			    	System.out.println( "webapp context url = " + baseurl);
					String url = baseurl + "rest/echo";
					data.setName(input);
					data.setValid(true);
					data.getMessages().add(input);
					data.getMessages().add(input);
					
					GenericWrapper<IWebResultResultObject> callResult = new GenericWrapper<IWebResultResultObject>(IWebResultResultObject.class);
					GenericWrapper<IResultObject> dataInput = new GenericWrapper<IResultObject>(IResultObject.class);
					dataInput.setValue(data);
					ACommand command = new ServerCallPutCommand<IWebResultResultObject,IResultObject>(dataInput, new StringBuffer(url), callResult ); 
					command.visit(getProvider());
					command.exec(new MyEvent(callResult));
					
					System.out.println("End metodo run");
										
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
			System.out.println( "OnSuccess called" );
			
			assertEquals( 0 , commandResult.getErrorMessages().size() );
			
			IResultObject resultObject = callResult.getValue().getReturnedObject();
			assertEquals( data.getMessages().size() , resultObject.getMessages().size() );
			
			
			assertEquals( data.isValid() , resultObject.isValid());
			assertEquals("PUT", resultObject.getName() );
			
			finishTest();
			
		}

	}
}
