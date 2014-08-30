package com.jpattern.jobexecutor.socket.starter;

import java.util.Map.Entry;

import com.jpattern.jobexecutor.IJob;
import com.jpattern.jobexecutor.IJobExecutionStrategy;
import com.jpattern.jobexecutor.socket.SocketJobThreadPool;


/**
 * 
 * @author Francesco Cina'
 *
 * 05/giu/2010
 */
public class StartSocketAction extends ASocketAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doAction(AJobsConfigurator configurator, boolean serverRunning) throws Exception {
		if ( serverRunning ) {
			System.out.println("The application is already started!");
			return;
		}
		
		// Starting server Socket
		System.out.println("Starting server...");

		configurator.initialize();
		
		SocketJobThreadPool socketJobThreadPool = new SocketJobThreadPool( configurator.getApplicationName() , configurator.getAdminSocketPort());
		
		for ( Entry<IJob, IJobExecutionStrategy> jobEntry : configurator.getJobs().entrySet()) {
			socketJobThreadPool.addJob(jobEntry.getKey(), jobEntry.getValue());
		}
    	
		socketJobThreadPool.start();

		Thread.sleep(1500);

    	
		//Sending "start" signal to server socket
//		ICommunicationClientStrategy communicationClientStrategy = new IdentifySocketStrategyDecorator( configurator.getApplicationName() , new StartStrategy() );
//		SocketClient socketClient = new SocketClient(configurator.getApplicationName(), "localhost", configurator.getAdminSocketPort(), communicationClientStrategy);
//		socketClient.start();
		
		System.out.println("Process started.");
	}

}
