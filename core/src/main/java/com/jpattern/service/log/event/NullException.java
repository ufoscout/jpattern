package com.jpattern.service.log.event;

import java.io.PrintWriter;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/apr/2010
 */
public class NullException extends Exception {

	private static final long serialVersionUID = 1L;
	
    public String toString() {
        return "";
    }
    
    public void printStackTrace(PrintWriter printWriter){
    }
}
