package com.jpattern.jobexecutor.mock;

import java.util.HashMap;
import java.util.Map;

import com.jpattern.jobexecutor.IJob;
import com.jpattern.jobexecutor.IJobExecutionStrategy;
import com.jpattern.jobexecutor.core.NullJobExecutionStrategy;
import com.jpattern.jobexecutor.socket.starter.AJobsConfigurator;
import com.jpattern.jobexecutor.socket.starter.SimpleAdminSocketPortReader;

/**
 * 
 * @author Francesco Cina'
 *
 * 08/feb/2010
 */
public class MockConfigurator extends AJobsConfigurator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int ADMIN_PORT = 5001;
	private Map<IJob, IJobExecutionStrategy> jobs = new HashMap<IJob, IJobExecutionStrategy>();

	public MockConfigurator(){
		super( "testServr" , new SimpleAdminSocketPortReader(ADMIN_PORT) );
	}
	
	@Override
	public Map<IJob, IJobExecutionStrategy> getJobs() {
		return jobs;
	}

	@Override
	public void initialize() {
		jobs.put( new CountJob( "job1" , 10) , new NullJobExecutionStrategy());
		jobs.put( new CountJob( "job2" , 20) , new NullJobExecutionStrategy());
		jobs.put( new CountJob( "job3" , 30) , new NullJobExecutionStrategy());
	}
	
}
