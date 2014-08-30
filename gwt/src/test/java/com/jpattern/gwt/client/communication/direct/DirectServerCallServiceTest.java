package com.jpattern.gwt.client.communication.direct;

import java.util.HashMap;
import java.util.Map;

import com.jpattern.gwt.client.BaseTest;
import com.jpattern.gwt.client.command.CommandResult;
import com.jpattern.gwt.client.communication.AProxy;
import com.jpattern.gwt.client.serializer.NullObjectSerializer;
import com.jpattern.shared.result.facade.ICommandFacadeResult;
import com.jpattern.gwt.shared.IWebResultObject;
import com.jpattern.gwt.shared.WebResult;

/**
 * 
 * @author Francesco Cina'
 *
 * 08/mag/2011
 */
public class DirectServerCallServiceTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testBestPath() throws Exception {
		DirectServerCallService serverCallService = new DirectServerCallService();
		
		Map<String, Object> addressMap = new HashMap<String, Object>();
		
		addressMap.put("path1", new Object());
		addressMap.put("path1/path2", new Object());
		addressMap.put("path2", new Object());
		addressMap.put("path2/path1", new Object());
		
		assertEquals( "" , serverCallService.getBestKey("", addressMap) );
		assertEquals( "path1" , serverCallService.getBestKey("path1", addressMap) );
		assertEquals( "path1" , serverCallService.getBestKey("path1/path3", addressMap) );
		assertEquals( "path1" , serverCallService.getBestKey("path1/path3/path2", addressMap) );
		assertEquals( "path1/path2" , serverCallService.getBestKey("path1/path2", addressMap) );
		assertEquals( "path1/path2" , serverCallService.getBestKey("path1/path2/path3", addressMap) );
		assertEquals( "path2" , serverCallService.getBestKey("path2/path3", addressMap) );
		assertEquals( "path2/path1" , serverCallService.getBestKey("path2/path1", addressMap) );
		assertEquals( "path2/path1" , serverCallService.getBestKey("path2/path1/path3", addressMap) );
	}
	
	public void testDirectServerCall() throws Exception {
		DirectServerCallService serverCallService = new DirectServerCallService();
		serverCallService.setProvider(getProvider());
		MyServerCallAction getCallAction = new MyServerCallAction();
		MyServerCallAction putCallAction = new MyServerCallAction();
		MyServerCallAction deleteCallAction = new MyServerCallAction();
		MyServerCallAction postCallAction = new MyServerCallAction();
		
		serverCallService.addGet("path", getCallAction);
		serverCallService.addPut("path", putCallAction);
		serverCallService.addPost("path", postCallAction);
		serverCallService.addDelete("path", deleteCallAction);
		
		MyServerCallBack getCallBackAction = new MyServerCallBack();
		MyServerCallBack postCallBackAction = new MyServerCallBack();
		MyServerCallBack putCallBackAction = new MyServerCallBack();
		MyServerCallBack deleteCallBackAction = new MyServerCallBack();
		
		Map<String, String> keyValuesMap = new HashMap<String, String>();
		
		AProxy<IWebResultObject> proxy = serverCallService.get( new NullObjectSerializer<IWebResultObject>(IWebResultObject.class), getCallBackAction, "path", keyValuesMap );
		proxy.call();
		assertEquals( "SUCCESS" , getCallBackAction.lastAction );
		assertEquals( "GET" , getCallAction.lastAction );
		
		proxy = serverCallService.delete(  new NullObjectSerializer<IWebResultObject>(IWebResultObject.class), deleteCallBackAction, "path", keyValuesMap );
		proxy.call();
		assertEquals( "SUCCESS" , deleteCallBackAction.lastAction );
		assertEquals( "DELETE" , deleteCallAction.lastAction );
		
		proxy = serverCallService.put(  new NullObjectSerializer<IWebResultObject>(IWebResultObject.class),  new NullObjectSerializer<Object>(Object.class), putCallBackAction, "path", new Object() );
		proxy.call();
		assertEquals( "SUCCESS" , putCallBackAction.lastAction );
		assertEquals( "PUT" , putCallAction.lastAction );
		
		proxy = serverCallService.post(  new NullObjectSerializer<IWebResultObject>(IWebResultObject.class), new NullObjectSerializer<Object>(Object.class), postCallBackAction, "path", new Object() );
		proxy.call();
		assertEquals( "SUCCESS" , postCallBackAction.lastAction );
		assertEquals( "POST" , postCallAction.lastAction );
	}

	
	class MyServerCallAction implements IServerCallPostAction, IServerCallDeleteAction, IServerCallGetAction, IServerCallPutAction {

		public String lastAction = "";
		
		@SuppressWarnings("unchecked")
		@Override
		public <T extends ICommandFacadeResult<?>, Z> T put(Class<T> resultClass, Class<Z> dataClass, String url, Z data) {
			lastAction = "PUT";
			return (T) new WebResult(new CommandResult(), null);
		}

		@SuppressWarnings("unchecked")
		@Override
		public <T extends ICommandFacadeResult<?>> T get(Class<T> resultClass, String url, Map<String, String> keyValuesMap) {
			lastAction = "GET";
			return (T) new WebResult(new CommandResult(), null);
		}

		@SuppressWarnings("unchecked")
		@Override
		public <T extends ICommandFacadeResult<?>> T delete(Class<T> resultClass, String url, Map<String, String> keyValuesMap) {
			lastAction = "DELETE";
			return (T) new WebResult(new CommandResult(), null);
		}

		@SuppressWarnings("unchecked")
		@Override
		public <T extends ICommandFacadeResult<?>, Z> T post(Class<T> resultClass, Class<Z> dataClass, String url, Z data) {
			lastAction = "POST";
			return (T) new WebResult(new CommandResult(), null);
		}
		
	}
}
