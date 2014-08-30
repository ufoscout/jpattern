package com.jpattern.jobexecutor.core;

/**
 * 
 * @author Francesco Cina'
 *
 * 27/mar/2010
 */
public class PauseThread {
	
	public static long DEFAULT_PAUSE_MILLISECONDS = 10;
	
	public long pauseMilliseconds;
	
	public void pauseThread() {
		pauseThread( DEFAULT_PAUSE_MILLISECONDS );
	}

	public void pauseThread(long pauseMillisecond) {
		try {
			Thread.sleep(DEFAULT_PAUSE_MILLISECONDS);
		} catch (InterruptedException e) {}
	}
	
	

}
