package com.jpattern.jobexecutor.socket.starter;

import com.jpattern.jobexecutor.ICommandExecutorHandler;
import com.jpattern.jobexecutor.socket.GenericConsoleComunicationClientStrategy;
import com.jpattern.jobexecutor.socket.ICommunicationClientStrategy;
import com.jpattern.jobexecutor.socket.IdentifySocketStrategyDecorator;
import com.jpattern.jobexecutor.socket.SocketClient;

/**
 * 
 * @author Francesco Cina'
 *
 * 05/giu/2010
 */
public class ConsoleManagerSocketAction extends ASocketAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doAction(AJobsConfigurator configurator, boolean serverRunning) throws Exception {
		if ( !serverRunning ) {
			System.out.println("The application is not running!");
			return;
		}
		
		System.out.println("Starting manager console...");

		ICommunicationClientStrategy strategy = new GenericConsoleComunicationClientStrategy( ICommandExecutorHandler.COMMAND_CONSOLE_MANAGER );
		ICommunicationClientStrategy communicationClientStrategy = new IdentifySocketStrategyDecorator( configurator.getApplicationName() , strategy );
		SocketClient socketClient = new SocketClient(configurator.getApplicationName(), "localhost", configurator.getAdminSocketPort(), communicationClientStrategy);
		socketClient.start();

		System.out.println("Manager console closed.");
	}

}
