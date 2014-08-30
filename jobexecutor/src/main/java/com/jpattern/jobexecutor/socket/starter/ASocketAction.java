package com.jpattern.jobexecutor.socket.starter;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;

/**
 * 
 * @author Francesco Cina'
 *
 * 05/giu/2010
 */
public abstract class ASocketAction implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public void exec(AJobsConfigurator configurator) throws Exception {
		boolean serverRunning = isServerRunning("localhost", configurator.getAdminSocketPort());
		doAction(configurator, serverRunning);
	}

	
	protected abstract void doAction(AJobsConfigurator configurator, boolean serverRunning) throws Exception;

	
	public boolean isServerRunning( String host, int port ) {
		boolean serverRunning = false;
		
		ServerSocket socketTest = null;
		try {
			socketTest = new ServerSocket(port);
		} catch (IOException e) {
			serverRunning = true;
		} finally {
			try {
				if (socketTest != null ) {
					socketTest.close();
				}
			} catch (IOException e) {
			}
		}

		return serverRunning;
	}
}
