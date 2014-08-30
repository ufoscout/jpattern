package com.jpattern.service.log.file;

import com.jpattern.service.log.AExecutor;
import com.jpattern.service.log.IExecutor;
import com.jpattern.service.log.NullExecutor;
import com.jpattern.service.log.event.DebugEvent;
import com.jpattern.service.log.event.ErrorEvent;
import com.jpattern.service.log.event.InfoEvent;
import com.jpattern.service.log.event.TraceEvent;
import com.jpattern.service.log.event.WarnEvent;

/**
 * 
 * @author Francesco Cina'
 *
 * 10/apr/2010
 */
public class LogFileExecutor extends AExecutor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ILogFileExecutorStrategy strategy;

	public LogFileExecutor(ILogFileExecutorStrategy fileExecutorStrategy) {
		this(fileExecutorStrategy, new NullExecutor());
	}
	
	public LogFileExecutor(ILogFileExecutorStrategy fileExecutorStrategy, IExecutor successor) {
		super(successor);
		this.strategy = fileExecutorStrategy;
	}

	public void what(TraceEvent event) {
		write( getMessageFormatter().toString(event.getName(), event.getMessage())  );
	}

	public void what(DebugEvent event) {
		write( getMessageFormatter().toString(event.getName(), event.getMessage())  );
	}

	public void what(InfoEvent event) {
		write( getMessageFormatter().toString(event.getName(), event.getMessage())  );
	}

	public void what(WarnEvent event) {
		write( getMessageFormatter().toString(event.getName(), event.getMessage())  );
	}

	public void what(ErrorEvent event) {
		write( getMessageFormatter().toStringWithStackTrace(event.getName(), event.getMessage())  );
	}

	private void write(String message) {
		strategy.getFileWriter().write(message);
	}
}
