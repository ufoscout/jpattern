package com.jpattern.jobexecutor.mock;

import java.io.IOException;
import java.util.List;

import com.jpattern.jobexecutor.socket.ICommunicationClientStrategy;

/**
 * 
 * @author Francesco Cina'
 *
 * 03/apr/2010
 */
public class MockCommunicationClientStrategyReadList implements ICommunicationClientStrategy {

	private static final long serialVersionUID = 1L;
	private List<String> readList;

	public MockCommunicationClientStrategyReadList(List<String> readList) {
		this.readList = readList;
	}
	
	public String read() throws IOException {
		String result = "MockCommunicationClientStrategyReadList default message";
		if (readList != null && readList.size()>0) {
			result = readList.get(0);
			readList.remove(0);
		}
		return result;
	}

	public void write(String message) {
		System.out.println("write message: ");
		System.out.println(message);
	}

}
