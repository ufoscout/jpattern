package com.jpattern.jobexecutor.socket.starter;

import com.jpattern.jobexecutor.socket.starter.SimpleExecutor;

/**
 * 
 * @author Francesco Cina'
 *
 * 03/giu/2010
 */
public class ThreadClientExecutorMock extends Thread {

	private SimpleExecutor clientExecutor;
	
	public ThreadClientExecutorMock(SimpleExecutor clientExecutor) {
		this.clientExecutor = clientExecutor;
	}

	public void run() {
		try {
			clientExecutor.launch();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
