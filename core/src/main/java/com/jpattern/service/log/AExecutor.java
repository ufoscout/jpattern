package com.jpattern.service.log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jpattern.service.log.event.DebugEvent;
import com.jpattern.service.log.event.ErrorEvent;
import com.jpattern.service.log.event.InfoEvent;
import com.jpattern.service.log.event.TraceEvent;
import com.jpattern.service.log.event.WarnEvent;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/apr/2010
 */
public abstract class AExecutor implements IExecutor {

	private static final long serialVersionUID = 1L;
	private IExecutor successor;
	private IMessageFormatter messageFormatter;

	private IExecutorStrategy executorStrategy = new TraceExecutorStrategy();
	private Map<String, IExecutorStrategy> strategyMap = new HashMap<String, IExecutorStrategy>();
	private List<String> ignoredPathList = new ArrayList<String>();
	
	public AExecutor(IExecutor successor) {
		this.successor = successor;
		
		strategyMap.put(ILoggerService.OFF, new NullExecutorStrategy());
		strategyMap.put(ILoggerService.TRACE, new TraceExecutorStrategy());
		strategyMap.put(ILoggerService.DEBUG, new DebugExecutorStrategy());
		strategyMap.put(ILoggerService.INFO, new InfoExecutorStrategy());
		strategyMap.put(ILoggerService.WARNING, new WarningExecutorStrategy());
		strategyMap.put(ILoggerService.ERROR, new ErrorExecutorStrategy());
		
	}
	
	
	public final void execute(TraceEvent event) {
		successor.execute(event);
		if (!isIgnoredPath(event.getMessage().getClassName())) executorStrategy.exec(this, event);
	}

	public final void execute(DebugEvent event) {
		successor.execute(event);
		if (!isIgnoredPath(event.getMessage().getClassName())) executorStrategy.exec(this, event);
	}

	public final void execute(InfoEvent event) {
		successor.execute(event);
		if (!isIgnoredPath(event.getMessage().getClassName())) executorStrategy.exec(this, event);		
	}

	public final void execute(WarnEvent event) {
		successor.execute(event);
		if (!isIgnoredPath(event.getMessage().getClassName())) executorStrategy.exec(this, event);
	}

	public final void execute(ErrorEvent event) {
		successor.execute(event);
		if (!isIgnoredPath(event.getMessage().getClassName())) executorStrategy.exec(this, event);
	}
	
	public final IMessageFormatter getMessageFormatter() {
		if ( messageFormatter == null) {
			messageFormatter = new DefaultMessageFormatter();
		}
		return messageFormatter;
	}

	public final void setMessageFormatter(IMessageFormatter messageFormatter) {
		this.messageFormatter = messageFormatter;
	}

	public abstract void what(TraceEvent event);
	public abstract void what(DebugEvent event);
	public abstract void what(InfoEvent event);
	public abstract void what(WarnEvent event);
	public abstract void what(ErrorEvent event);

	public final void setLoggerLevel(String loggerLevel) {
		if (strategyMap.containsKey(loggerLevel)) {
			executorStrategy = strategyMap.get(loggerLevel);
		}
	}
	
	@Override
	public void addIgnoredPath(String classpath) {
		if ( classpath == null) {
			return;
		}
		removeIgnoredPath( classpath );
		ignoredPathList.add( classpath ); 
	}
	
	@Override
	public void removeIgnoredPath(String classpath) {
		if ( classpath == null) {
			return;
		}
		if (ignoredPathList.contains(classpath) ) {
			ignoredPathList.remove( classpath ); 
		}
	}
	
	@Override
	public boolean isIgnoredPath( String classpath) {
		boolean result = false;
		for (String classpathIgnored : ignoredPathList) {
			if ( classpath.startsWith( classpathIgnored ) ) {
				result = true;
			}
		}
		return result;
	}
	
}
