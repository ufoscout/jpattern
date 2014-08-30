package com.jpattern.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jpattern.BaseTest;
import com.jpattern.rest.RestServletResourceMap;
import com.jpattern.rest.action.IDeleteAction;
import com.jpattern.rest.action.IGetAction;
import com.jpattern.rest.action.IPostAction;
import com.jpattern.rest.action.IPutAction;
import com.jpattern.rest.action.NullAction;
import com.jpattern.rest.domain.URLPath;
import com.jpattern.logger.SystemOutLoggerFactory;
import com.jpattern.shared.result.facade.ICommandFacadeResult;

/**
 * 
 * @author Francesco Cina'
 *
 * 11/mag/2011
 */
public class RestServletConfigurationTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testBestPath() throws Exception {
		RestServletResourceMap restServletresourceMap = new RestServletResourceMap(new SystemOutLoggerFactory());
		
		Map<String, Object> addressMap = new HashMap<String, Object>();
		
		addressMap.put("path1", new Object());
		addressMap.put("path1/path2", new Object());
		addressMap.put("path2", new Object());
		addressMap.put("path2/path1", new Object());
		addressMap.put("/path3", new Object());
		addressMap.put("/path3/path4", new Object());
		
		assertEquals( "" , restServletresourceMap.getBestKey("", addressMap) );
		assertEquals( "" , restServletresourceMap.getRelativePath("", "") );
		
		assertEquals( "path1" , restServletresourceMap.getBestKey("path1", addressMap) );
		assertEquals( "" , restServletresourceMap.getRelativePath("path1", "path1") );
		
		assertEquals( "" , restServletresourceMap.getBestKey("path12", addressMap) );
		assertEquals( "path12" , restServletresourceMap.getRelativePath("path12", "") );
		
		assertEquals( "path1" , restServletresourceMap.getBestKey("path1/path3", addressMap) );
		assertEquals( "/path3" , restServletresourceMap.getRelativePath("path1/path3", "path1") );
		
		assertEquals( "path1" , restServletresourceMap.getBestKey("path1/path3/path2", addressMap) );
		assertEquals( "/path3/path2" , restServletresourceMap.getRelativePath("path1/path3/path2", "path1") );
		
		assertEquals( "path1/path2" , restServletresourceMap.getBestKey("path1/path2", addressMap) );
		assertEquals( "" , restServletresourceMap.getRelativePath("path1/path2", "path1/path2") );
		
		assertEquals( "path1/path2" , restServletresourceMap.getBestKey("path1/path2/path3", addressMap) );
		assertEquals( "/path3" , restServletresourceMap.getRelativePath("path1/path2/path3", "path1/path2") );
		
		assertEquals( "path2" , restServletresourceMap.getBestKey("path2/path3", addressMap) );
		assertEquals( "/path3" , restServletresourceMap.getRelativePath("path2/path3", "path2") );
		
		assertEquals( "path2/path1" , restServletresourceMap.getBestKey("path2/path1", addressMap) );
		assertEquals( "" , restServletresourceMap.getRelativePath("path2/path1", "path2/path1") );
		
		assertEquals( "path2/path1" , restServletresourceMap.getBestKey("path2/path1/path3", addressMap) );
		assertEquals( "/path3" , restServletresourceMap.getRelativePath("path2/path1/path3", "path2/path1") );
		
		assertEquals( "" , restServletresourceMap.getBestKey("/", addressMap) );
		assertEquals( "/" , restServletresourceMap.getRelativePath("/", "") );
		
		assertEquals( "/path3" , restServletresourceMap.getBestKey("/path3", addressMap) );
		assertEquals( "" , restServletresourceMap.getRelativePath("/path3", "/path3") );
		
		assertEquals( "/path3" , restServletresourceMap.getBestKey("/path3/hello", addressMap) );
		assertEquals( "/hello" , restServletresourceMap.getRelativePath("/path3/hello", "/path3") );
	}

	public void testDirectServerCall() throws Exception {
		RestServletResourceMap serverCallService = new RestServletResourceMap(new SystemOutLoggerFactory());
		MyServerCallAction serverCallAction = new MyServerCallAction();
		
		serverCallService.addAction("path", serverCallAction);
		
		IGetAction nullAction = serverCallService.getAction("path1").action();
		System.out.println("1");
		assertTrue(nullAction instanceof NullAction);
		System.out.println("2");
		nullAction.get(new HashMap<String, List<String>>(), "", new URLPath());
		System.out.println("3");
		
		serverCallService.postAction("").action().post(new HashMap<String, List<String>>(), "", new URLPath());
		serverCallService.putAction("we").action().put(new HashMap<String, List<String>>(), "", new URLPath());
		serverCallService.deleteAction("hello").action().delete(new HashMap<String, List<String>>(), "", new URLPath());
		assertEquals( "" , serverCallAction.lastAction );
		
		serverCallService.getAction("path").action().get(new HashMap<String, List<String>>(), "", new URLPath());
		assertEquals( "GET" , serverCallAction.lastAction );
		
		serverCallService.postAction("path").action().post(new HashMap<String, List<String>>(), "", new URLPath());
		assertEquals( "POST" , serverCallAction.lastAction );
		
		serverCallService.putAction("path").action().put(new HashMap<String, List<String>>(), "", new URLPath());
		assertEquals( "PUT" , serverCallAction.lastAction );
		
		serverCallService.deleteAction("path").action().delete(new HashMap<String, List<String>>(), "", new URLPath());
		assertEquals( "DELETE" , serverCallAction.lastAction );

	}

	
	class MyServerCallAction implements IPostAction, IDeleteAction, IGetAction, IPutAction {

		private static final long serialVersionUID = 1L;
		public String lastAction = "";
		
		@Override
		public ICommandFacadeResult<?> put(Map<String, List<String>> parameters, String path, URLPath urlPath) {
			lastAction = "PUT";
			return null;
		}

		@Override
		public ICommandFacadeResult<?> get(Map<String, List<String>> parameters, String path, URLPath urlPath) {
			lastAction = "GET";
			return null;
		}

		@Override
		public ICommandFacadeResult<?> delete(Map<String, List<String>> parameters, String path, URLPath urlPath) {
			lastAction = "DELETE";
			return null;
		}

		@Override
		public ICommandFacadeResult<?> post(Map<String, List<String>> parameters, String path, URLPath urlPath) {
			lastAction = "POST";
			return null;
		}
		
	}
}
