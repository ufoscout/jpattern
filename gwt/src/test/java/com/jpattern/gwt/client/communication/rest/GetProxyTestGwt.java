package com.jpattern.gwt.client.communication.rest;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Timer;
import com.jpattern.gwt.client.BaseGwtTestCase;
import com.jpattern.gwt.client.communication.AProxy;
import com.jpattern.gwt.client.communication.ICallbackAction;
import com.jpattern.gwt.client.communication.rest.GetProxy;
import com.jpattern.gwt.client.serializer.AutoBeanObjectSerializer;
import com.jpattern.gwt.shared.IResultObject;
import com.jpattern.gwt.shared.IWebResultResultObject;

/**
 * 
 * @author Francesco Cina'
 *
 * 12 Apr 2011
 */
public class GetProxyTestGwt extends BaseGwtTestCase {

    private String input = "GetProxyTestGwt";
	private int repeat = 5;
	
	@Override
	protected void gwtTestCaseSetUp() throws Exception {
	}

	@Override
	protected void gwtTestCaseTearDown() throws Exception {
	}

	@Override
	public String getModuleName() {
		return "com.jpattern.gwt.jpattern_tests";
	}
	
	public void testGetProxy() throws Exception {
		  Timer timer = new Timer() {

				public void run() {
			    	String baseurl = GWT.getHostPageBaseURL().replace(GWT.getModuleName() + "/", ""); 
			    	System.out.println( "webapp context url = " + baseurl);
					String url = baseurl + "rest/echo";
					url = URL.encode(url);
					Map<String, String> keyValuesMap = new HashMap<String, String>();
					keyValuesMap.put("input", input);
					keyValuesMap.put("repeat", "" + repeat);
					
					AProxy<IWebResultResultObject> proxy = new GetProxy<IWebResultResultObject>( new MyCallback(), new AutoBeanObjectSerializer<IWebResultResultObject>(getBeanFactory(), IWebResultResultObject.class, getProvider()),url, keyValuesMap, getProvider());
					try {
						proxy.call();
					} catch (Exception e) {
						e.printStackTrace();
						assertTrue(false);
					}
					
					System.out.println("End metodo run");
			    }
			  };

			  delayTestFinish(10000);
			  timer.schedule(10);
	}
	
	class MyCallback implements ICallbackAction<IWebResultResultObject> {

		@Override
		public void onSuccess(IWebResultResultObject result, int responseCode) {
			System.out.println( "OnSuccess called" );
			
			assertEquals( 0 , result.getErrorMessages().size() );
			
			IResultObject resultObject = result.getReturnedObject();
			assertEquals( repeat , resultObject.getMessages().size() );
			
			for (int i=0; i<resultObject.getMessages().size(); i++) {
				assertEquals( i + input, resultObject.getMessages().get(i));
			}
			
			assertTrue( resultObject.isValid() );
			assertEquals("GET", resultObject.getName() );
			
			System.out.println( "OnSuccess end" );
			finishTest();
		}

		@Override
		public void onError(Throwable exception, int responseCode) {
			System.out.println( "OnError called" );
			
			new Throwable().printStackTrace();
			
			assertTrue(false);
			finishTest();
		}
	}
	
}
