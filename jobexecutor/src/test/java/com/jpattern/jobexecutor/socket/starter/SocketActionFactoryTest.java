package com.jpattern.jobexecutor.socket.starter;

import java.util.List;


import com.jpattern.core.BaseTest;
import com.jpattern.jobexecutor.socket.starter.NullSocketAction;
import com.jpattern.jobexecutor.socket.starter.SocketActionFactory;
import com.jpattern.jobexecutor.socket.starter.StartSocketAction;

/**
 * 
 * @author Francesco Cina'
 *
 * 05/giu/2010
 */
public class SocketActionFactoryTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testExists() throws Exception {
		SocketActionFactory socketActionFactory = new SocketActionFactory();
		
		assertTrue( socketActionFactory.existsAction( SocketActionFactory.START ) );
		assertTrue( socketActionFactory.existsAction( SocketActionFactory.SHUTDOWN ) );
		assertFalse( socketActionFactory.existsAction( "ciao mamma" ) );
		
	}
	
	public void testListAction() throws Exception {
		SocketActionFactory socketActionFactory = new SocketActionFactory();
		
		List<String> actionNames = socketActionFactory.getActionNames();
		
		assertNotNull( actionNames );
		assertTrue( actionNames.contains( SocketActionFactory.START ) );
		assertTrue( actionNames.contains( SocketActionFactory.SHUTDOWN ) );
		
	}
	
	public void testReturnType() throws Exception {
		SocketActionFactory socketActionFactory = new SocketActionFactory();
		
		assertTrue( socketActionFactory.getAction("ciao mamma") instanceof NullSocketAction );
		assertTrue( socketActionFactory.getAction(SocketActionFactory.START) instanceof StartSocketAction );
	}
}
