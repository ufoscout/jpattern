package com.jpattern.jobexecutor.iostream;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * 
 * @author Francesco Cina'
 *
 * 23/mar/2010
 */
public class StreamOutputWriter implements IOutputWriter {
	
	private static final long serialVersionUID = 1L;
	private PrintStream outputStream;

	public StreamOutputWriter(OutputStream outputStream) {
		this.outputStream = new PrintStream( outputStream );
	}

	public void write(String message) {
		outputStream.println( message );
	}

}
