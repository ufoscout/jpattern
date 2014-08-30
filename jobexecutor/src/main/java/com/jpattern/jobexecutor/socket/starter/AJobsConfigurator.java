package com.jpattern.jobexecutor.socket.starter;

import java.io.Serializable;
import java.util.Map;

import com.jpattern.jobexecutor.IJob;
import com.jpattern.jobexecutor.IJobExecutionStrategy;


/**
 * 
 * @author Francesco Cina'
 *
 * 08/feb/2010
 */
public abstract class AJobsConfigurator implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String applicationName;
	private IAdminSocketPortReader adminSocketPortReader;

	public AJobsConfigurator(String applicationName, IAdminSocketPortReader adminSocketPortReader) {
		this.applicationName = applicationName;
		this.adminSocketPortReader = adminSocketPortReader;
	}
	
	public final String getApplicationName() {
		return applicationName;
	}
	
	public abstract Map<IJob, IJobExecutionStrategy> getJobs();
	
	public abstract void initialize() throws Exception;

	public int getAdminSocketPort() throws Exception {
		return adminSocketPortReader.getPort();
	}

}
