package com.jpattern.jobexecutor.socket.starter;


import com.jpattern.core.BaseTest;
import com.jpattern.jobexecutor.mock.MockConfigurator;
import com.jpattern.jobexecutor.socket.starter.AJobsConfigurator;
import com.jpattern.jobexecutor.socket.starter.SimpleExecutor;
import com.jpattern.jobexecutor.socket.starter.StartSocketAction;



/**
 * 
 * @author Francesco Cina'
 *
 * 08/feb/2010
 */
public class ClientExecutorTest extends BaseTest {
	
	private int adminPort;
	private AJobsConfigurator configurator;

	protected void setUp() throws Exception {
		super.setUp();
		Thread.sleep(250);
		configurator = new MockConfigurator();
		adminPort = configurator.getAdminSocketPort();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		Thread.sleep(250);
	}
	
	
	
	public void testServer1() throws Exception {

		System.out.println("0");
		String[] args = new String[]{"start","a","b","c"};
		
		new ThreadClientExecutorMock( new SimpleExecutor(configurator, args) ).start();
		
		Thread.sleep(500);
		System.out.println("1");
		assertTrue( isServerRunning( adminPort ) );
		System.out.println("2");
		args = new String[]{"shutdown","a","b","c"};
		new ThreadClientExecutorMock( new SimpleExecutor(configurator, args) ).start();
		System.out.println("3");
		Thread.sleep(1000);
		System.out.println("4");
		
		assertTrue( isServerRunning( adminPort ) );
		
	}
	
	public void testServer2() throws Exception {
		String[] args =  new String[]{"shutdown","a","b","c"};
		new ThreadClientExecutorMock( new SimpleExecutor(configurator, args) ).start();
		Thread.sleep(1000);
		assertTrue( isServerRunning( adminPort ) );
	}
	
	public void testServer3() throws Exception {
		String[] args =  null;
		new ThreadClientExecutorMock( new SimpleExecutor(configurator, args) ).start();
		Thread.sleep(1000);
		assertTrue( isServerRunning( adminPort ) );
	}
	
	public boolean isServerRunning(int port) throws Exception {
		StartSocketAction action = new StartSocketAction();
		return action.isServerRunning( "localhost" , port);
	}
	
}
