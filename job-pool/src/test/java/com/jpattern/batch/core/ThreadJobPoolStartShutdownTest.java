package com.jpattern.batch.core;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jpattern.batch.BaseTest;
import com.jpattern.batch.JobPool;

/**
 * 
 * @author Francesco Cina'
 *
 * Apr 15, 2012
 */
public class ThreadJobPoolStartShutdownTest extends BaseTest {

	@Override
	protected void setUp() throws Exception {
	}

	@Override
	protected void tearDown() throws Exception {
	}

	@Test
	public void testStartShutdown() {
		final JobPool pool = new ThreadJobPool("");

		assertFalse(pool.isStarted());
		assertFalse(pool.isShutdown());

		pool.start();
		assertTrue(pool.isStarted());
		assertFalse(pool.isShutdown());

		pool.shutdown(false);
		assertFalse(pool.isStarted());
		assertTrue(pool.isShutdown());

		try {
			//cannot restart after shutdown
			pool.start();
			assertTrue(false);
		} catch (final RuntimeException e) {
		}
	}

}
