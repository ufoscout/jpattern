package com.jpattern.jobexecutor.mock;

import java.io.IOException;
import java.util.List;

import com.jpattern.jobexecutor.iostream.ICommunicationChannel;

/**
 * 
 * @author Francesco Cina'
 *
 * 03/apr/2010
 */
public class MockCommunicationChannelReadList implements ICommunicationChannel {

	private static final long serialVersionUID = 1L;
	private List<String> readList;

	public MockCommunicationChannelReadList(List<String> readList) {
		this.readList = readList;
	}
	
	public void close() {
	}

	public String read() throws IOException {
		String result = "";
		if (readList != null && readList.size()>0) {
			result = readList.get(0);
			readList.remove(0);
		}
		return result;
	}

	public void run() {
	}

	public void write(String message) {
		System.out.println("write message: ");
		System.out.println(message);
	}

}
