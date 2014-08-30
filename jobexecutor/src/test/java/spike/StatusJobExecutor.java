package spike;

import com.jpattern.jobexecutor.IJobThreadPool;
import com.jpattern.jobexecutor.command.StatusJobCommand;

/**
 * 
 * @author Francesco Cina'
 *
 * 10/ago/2010
 */
public class StatusJobExecutor implements IExecutor<String> {

	private StringBuffer _text;
	private IJobThreadPool _jobThreadPool;

	public StatusJobExecutor(StringBuffer text, IJobThreadPool jobThreadPool) {
		_text = text;
		_jobThreadPool = jobThreadPool;
	}
	
	public void exec(String aString) {
		StringBuffer jobState = new StringBuffer();
		new StatusJobCommand(_jobThreadPool, aString, jobState ).exec();
		_text.append("job: " + aString + " --> status: " + jobState.toString());
	}

}
