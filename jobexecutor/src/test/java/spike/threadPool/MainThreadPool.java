package spike.threadPool;

import java.util.HashMap;
import java.util.Map;

import com.jpattern.jobexecutor.IJob;
import com.jpattern.jobexecutor.IJobExecutionStrategy;
import com.jpattern.jobexecutor.execution.IntervalJobExecutionStrategy;
import com.jpattern.jobexecutor.socket.starter.AJobsConfigurator;
import com.jpattern.jobexecutor.socket.starter.IAdminSocketPortReader;
import com.jpattern.jobexecutor.socket.starter.SimpleAdminSocketPortReader;
import com.jpattern.jobexecutor.socket.starter.SimpleExecutor;

public class MainThreadPool extends AJobsConfigurator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public MainThreadPool(String applicationName, IAdminSocketPortReader adminSocketPortReader) {
		super(applicationName, adminSocketPortReader);
	}


	@Override
	public Map<IJob, IJobExecutionStrategy> getJobs() {
		Map<IJob, IJobExecutionStrategy> map = new HashMap<IJob, IJobExecutionStrategy>();
		map.put(new MockJob("mock0"), new IntervalJobExecutionStrategy( 100 ));
		map.put(new MockJob("mock1"), new IntervalJobExecutionStrategy( 100 ));
		map.put(new MockJob("mock2"), new IntervalJobExecutionStrategy( 100 ));
		map.put(new MockJob("mock3"), new IntervalJobExecutionStrategy( 100 ));
		map.put(new MockJob("mock4"), new IntervalJobExecutionStrategy( 100 ));
		map.put(new MockJob("mock5"), new IntervalJobExecutionStrategy( 100 ));
		map.put(new MockJob("mock6"), new IntervalJobExecutionStrategy( 100 ));
		map.put(new MockJob("mock7"), new IntervalJobExecutionStrategy( 100 ));
		map.put(new MockJob("mock8"), new IntervalJobExecutionStrategy( 100 ));
		map.put(new MockJob("mock9"), new IntervalJobExecutionStrategy( 100 ));
		return map;
	}

	@Override
	public void initialize() throws Exception {
		System.out.println("--- Initialized ---");
	}

	
	public static void main(String[] args) throws Exception {
		IAdminSocketPortReader adminSocketPortReader = new SimpleAdminSocketPortReader(12345);
		AJobsConfigurator jobConfigurator = new MainThreadPool("TEST", adminSocketPortReader);
		String[] command = new String[] {"start"};
		SimpleExecutor simpleExecutor = new SimpleExecutor( jobConfigurator , command);
		simpleExecutor.launch();
	}
}
