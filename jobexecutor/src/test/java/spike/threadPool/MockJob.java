package spike.threadPool;

import com.jpattern.jobexecutor.IJob;
import com.jpattern.jobexecutor.IJobResult;

public class MockJob implements IJob {

	private static final long serialVersionUID = 1L;
	private String name;

	public MockJob(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public IJobResult execute() throws Exception {
		System.gc();
		return null;
	}

}
