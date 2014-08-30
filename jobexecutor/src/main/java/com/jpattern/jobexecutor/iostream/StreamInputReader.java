package com.jpattern.jobexecutor.iostream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 
 * @author Francesco Cina'
 *
 * 23/mar/2010
 */
public class StreamInputReader implements IInputReader {

	private static final long serialVersionUID = 1L;
	private BufferedReader inputStream;

	public StreamInputReader( InputStream inputStream ) {
		this.inputStream = new BufferedReader( new InputStreamReader( inputStream ) );
	}
	
	public String read() throws IOException {
		return inputStream.readLine();
	}

}
