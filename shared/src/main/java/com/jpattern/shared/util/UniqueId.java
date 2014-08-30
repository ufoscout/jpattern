package com.jpattern.shared.util;


import java.util.Random;

/**
 * 
 * @author Francesco Cina'
 *
 * 12/ago/2010
 */
public class UniqueId {
	
    private static long currentA = System.currentTimeMillis();
    private static Random random = new Random();

    public static synchronized String get() {
        StringBuffer rit = new StringBuffer();
        currentA++;
        rit.append(random.nextLong());
        rit.append(currentA);
        rit.append(random.nextLong());

        return rit.toString().replaceAll("-", "");
    }
    
}
