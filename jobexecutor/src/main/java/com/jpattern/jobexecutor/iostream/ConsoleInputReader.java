package com.jpattern.jobexecutor.iostream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author Francesco Cina'
 *
 * 23/mar/2010
 */
public class ConsoleInputReader implements IInputReader {

	private static final long serialVersionUID = 1L;
	private BufferedReader in;

	public ConsoleInputReader() {
		this.in = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public String read() throws IOException {
		return in.readLine();
	}

}
