package com.jpattern.util;


import java.util.Random;

/**
 * 
 * @author Francesco Cina'
 *
 * 12/ago/2010
 */
public abstract class UniqueId {

	private static long currentA = System.currentTimeMillis();
	private static Random random = new Random();

	public static synchronized String get() {
		final StringBuffer rit = new StringBuffer();
		currentA++;
		rit.append(random.nextLong());
		rit.append(currentA);

		return rit.toString().replaceAll("-", "");
	}

}
