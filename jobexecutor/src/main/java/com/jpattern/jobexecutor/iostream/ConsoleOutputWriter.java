package com.jpattern.jobexecutor.iostream;

import java.io.PrintStream;

/**
 * 
 * @author Francesco Cina'
 *
 * 23/mar/2010
 */
public class ConsoleOutputWriter implements IOutputWriter {

	private static final long serialVersionUID = 1L;
	private PrintStream out;

	public ConsoleOutputWriter() {
		out = System.out;
	}
	
	public void write(String message) {
		out.println(message);
	}

}
