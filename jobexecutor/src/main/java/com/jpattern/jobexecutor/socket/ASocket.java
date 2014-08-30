package com.jpattern.jobexecutor.socket;

import java.io.Serializable;


/**
 * 
 * @author Francesco Cina'
 *
 * 06/feb/2010
 */
public abstract class ASocket extends Thread implements Serializable {

	public static String CONNECTION_ACCEPTED = "connectionAccepted";
	public static String CLOSE_CONNECTION = "closeConnection";
	
	private static final long serialVersionUID = 1L;
	protected boolean stopSocket = false;
	protected boolean started = false;
	private String socketName;

	public ASocket(String name) {
		this.socketName = name;
	}
	
	public final void run() {
		if(!started) {
			started = true;
			initialize();
		}
	}
	
	public String getSocketName() {
		return socketName;
	}
	
	public abstract void closeConnection();
	
	public abstract void initialize();
	
	public void stopSocket() {
		closeConnection();
		stopSocket = true;
//		this.interrupt();
	}
}
