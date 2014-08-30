package com.jpattern.gwt.client.communication.rest;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.jpattern.gwt.client.BaseGwtTestCase;
import com.jpattern.gwt.client.communication.AProxy;
import com.jpattern.gwt.client.communication.ICallbackAction;
import com.jpattern.gwt.client.communication.rest.PostProxy;
import com.jpattern.gwt.client.serializer.AutoBeanObjectSerializer;
import com.jpattern.gwt.shared.IResultObject;
import com.jpattern.gwt.shared.IWebResultResultObject;
import com.jpattern.gwt.shared.ResultObject;

/**
 * 
 * @author Francesco Cina'
 *
 * 12 Apr 2011
 */
public class PostProxyTestGwt extends BaseGwtTestCase {

    private String input = "PostProxyTestGwt";
    ResultObject data = new ResultObject();
	
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
	
	public void testValidPostProxy() throws Exception {
		  Timer timer = new Timer() {

				public void run() {
			    	String baseurl = GWT.getHostPageBaseURL().replace(GWT.getModuleName() + "/", ""); 
			    	System.out.println( "webapp context url = " + baseurl);
					String url = baseurl + "rest/echo";
					data.setName(input);
					data.setValid(true);
					data.getMessages().add(input);
					data.getMessages().add(input);
					
					AProxy<IWebResultResultObject> proxy = new PostProxy<IWebResultResultObject,IResultObject>(new MyCallback(), new AutoBeanObjectSerializer<IWebResultResultObject>(getBeanFactory(), IWebResultResultObject.class, getProvider()), url, new AutoBeanObjectSerializer<IResultObject>(getBeanFactory(), IResultObject.class, getProvider()), data, getProvider());
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
	
	
	public void testNotValidPostProxy() throws Exception {
		  Timer timer = new Timer() {

				public void run() {
			    	String baseurl = GWT.getHostPageBaseURL().replace(GWT.getModuleName() + "/", ""); 
			    	System.out.println( "webapp context url = " + baseurl);
					String url = baseurl + "rest/echo";
					data.setName(input);
					data.setValid(false);
					data.getMessages().add(input);
					data.getMessages().add(input);
					data.getMessages().add(input);
					AProxy<IWebResultResultObject> proxy = new PostProxy<IWebResultResultObject,IResultObject>(new MyCallback(), new AutoBeanObjectSerializer<IWebResultResultObject>(getBeanFactory(), IWebResultResultObject.class, getProvider()), url, new AutoBeanObjectSerializer<IResultObject>(getBeanFactory(), IResultObject.class, getProvider()), data, getProvider());
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
			assertEquals( data.getMessages().size() , resultObject.getMessages().size() );
			
			
			assertEquals( data.isValid() , resultObject.isValid());
			assertEquals("POST", resultObject.getName() );
			
			finishTest();
		}

		@Override
		public void onError(Throwable exception, int responseCode) {
			System.out.println( "OnError called" );
			assertTrue(false);
			finishTest();
		}
	}
}
