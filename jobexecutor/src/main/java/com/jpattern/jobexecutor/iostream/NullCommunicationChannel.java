package com.jpattern.jobexecutor.iostream;

import java.io.IOException;


public class NullCommunicationChannel implements ICommunicationChannel {

	private static final long serialVersionUID = 1L;

	public void close() {
	}

	public String read() throws IOException {
		return "";
	}

	public void run() {
	}

	public void write(String message) {
	}

	public String identify() throws IOException {
		return "";
	}

}
