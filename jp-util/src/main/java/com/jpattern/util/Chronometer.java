package com.jpattern.util;

import java.io.Serializable;



/**
 *
 * @author    <em>Marco Cimatti</em>
 * @version   1.0
 */
public final class Chronometer implements Serializable {

	private static final long serialVersionUID = 1L;

	private long counter;
	private long startedAt;
	private boolean started;


	/**
	 * Create a new {@link Chronometer}
	 *
	 * @see   #reset()
	 * @see   #start()
	 * @see   #pause()
	 */
	public Chronometer() { reset(); }

	/**
	 * Stops and reset the {@link Chronometer}
	 */
	public final void reset() {
		synchronized (this) {
			counter = 0;
			started = false;
		}
	}

	/**
	 * Restarts the {@link Chronometer} (does NOT reset it before)
	 *
	 * @see   #pause()
	 */
	public void start() {
		synchronized (this) {
			startedAt = System.currentTimeMillis();
			started = true;
		}
	}

	/**
	 * Pauses the {@link Chronometer}. Use #start() to restart it without reset it before.
	 *
	 * @see   #start()
	 */
	public void pause() {
		synchronized (this) {
			counter += System.currentTimeMillis() - startedAt;
			started = false;
		}
	}

	/**
	 * @return the total milliseconds counted by the {@link Chronometer}
	 */
	public long read() {
		synchronized (this) {
			return started ? (counter + System.currentTimeMillis()) - startedAt : counter;
		}
	}

}
