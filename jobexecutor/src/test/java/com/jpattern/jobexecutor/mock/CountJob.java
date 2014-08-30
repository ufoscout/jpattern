package com.jpattern.jobexecutor.mock;

import com.jpattern.jobexecutor.IJob;
import com.jpattern.jobexecutor.IJobResult;
import com.jpattern.jobexecutor.core.NullJobResult;

/**
 * 
 * @author Francesco Cina'
 *
 * 08/feb/2010
 */
public class CountJob implements IJob {

	private static final long serialVersionUID = 1L;
	private int howMany;
	private StringBuffer responseBuffer;
	private String name;

	public CountJob(String name,  int howMany) {
		this.name = name;
		this.howMany = howMany;
		this.responseBuffer = OutputMock.output;
	}

	public IJobResult execute() throws Exception {
		for( int i = 0; i<howMany; i++){
			System.out.println( getName() + ": count " + i);
			responseBuffer.append(getName() + ": count " + i + "\n");
		}
		return new NullJobResult();
	}

	public String getName() {
		return name;
	}

}
