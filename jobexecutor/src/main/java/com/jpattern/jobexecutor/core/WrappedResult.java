package com.jpattern.jobexecutor.core;

import com.jpattern.jobexecutor.IWrappedResult;

public class WrappedResult implements IWrappedResult {

	private static final long serialVersionUID = 1L;
	private StringBuffer buffer = new StringBuffer();

	public void add(String aLine) {
		buffer.append(aLine);
		buffer.append("\n");
	}

	public String result() {
		return buffer.toString();
	}

	public void clean() {
		buffer.setLength(0);
	}

}
