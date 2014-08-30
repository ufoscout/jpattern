package com.jpattern.jobexecutor.console;

import com.jpattern.jobexecutor.IWrappedResult;
import com.jpattern.jobexecutor.iostream.ICommunicationChannel;

/**
 * 
 * @author Francesco Cina'
 *
 * 04/apr/2010
 */
public class WrappedCommunicationChannelResult implements IWrappedResult {

	private StringBuilder result = new StringBuilder();

	private ICommunicationChannel communicatioChannel;
	
	private static final long serialVersionUID = 1L;

	public WrappedCommunicationChannelResult(ICommunicationChannel communicatioChannel) {
		this.communicatioChannel = communicatioChannel;
	}

	public void add(String aLine) {
		communicatioChannel.write(aLine);
		result.append(aLine);
		result.append("\n");
	}

	public void clean() {
		result = new StringBuilder();
	}

	public String result() {
		return result.toString();
	}

}
