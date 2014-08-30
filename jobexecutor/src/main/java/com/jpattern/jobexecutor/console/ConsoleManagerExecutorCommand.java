package com.jpattern.jobexecutor.console;

import java.util.ArrayList;
import java.util.List;

import com.jpattern.jobexecutor.IBooleanWrapper;
import com.jpattern.jobexecutor.ICommandExecutorHandler;
import com.jpattern.jobexecutor.IJobThreadPool;
import com.jpattern.jobexecutor.IWrappedResult;
import com.jpattern.jobexecutor.command.ListJobsCommand;
import com.jpattern.jobexecutor.command.PauseJobCommand;
import com.jpattern.jobexecutor.command.ResumeJobCommand;

/**
 * 
 * @author Francesco Cina'
 *
 * 10/ago/2010
 */
public class ConsoleManagerExecutorCommand implements ICommandExecutorHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String START = "resume";
	public static String STOP = "pause";
	public static String QUIT = "q";
	
	private IJobThreadPool jobThreadPool;

	public ConsoleManagerExecutorCommand(IJobThreadPool jobThreadPool) {
		this.jobThreadPool = jobThreadPool;
	}
	
	public String getCommandDescription() {
		return "JobThreadPool administration tool";
	}

	public ICommandExecutorHandler exec(String commandLine, IWrappedResult wrappedResult, IBooleanWrapper stopCommandExecution) {
		String[] tokens = tokenize(commandLine, " ");
		
		if (tokens.length>=1 && tokens[0].equals(QUIT)) {
			stopCommandExecution.setValue(true);
			return new NullCommandExecutorHandler();
		}

		stopJob( tokens );
		startJob( tokens );
		
		wrappedResult.add("");
		wrappedResult.add("");
		wrappedResult.add("");
		wrappedResult.add("");
		wrappedResult.add("");
		wrappedResult.add("JOB-THREAD-POOL MANAGER CONSOLE FOR '" + jobThreadPool.getName() + "'");
		wrappedResult.add("List of all jobs:");
		wrappedResult.add("");
		
		List<String> resultJobsName = new ArrayList<String>();
		List<String> executorWrappedList = new ExecutorWrappedList<String>(resultJobsName, new StatusJobExecutor(wrappedResult , jobThreadPool));
		new ListJobsCommand(jobThreadPool, executorWrappedList).exec(); 	
		
		wrappedResult.add("");
		wrappedResult.add("To start or stop a job use '" + START +" JOBNAME' or '" + STOP +" JOBNAME'. To quit console use '" + QUIT + "'");
		wrappedResult.add("");
			
		return this;
	}

	private void startJob(String[] tokens) {
		if (tokens.length>1 && tokens[0].equals(START)) {
			new ResumeJobCommand(jobThreadPool, tokens[1]).exec();
		}		
	}

	private void stopJob(String[] tokens) {
		if (tokens.length>1 && tokens[0].equals(STOP)) {
			new PauseJobCommand(jobThreadPool, tokens[1]).exec();
		}	
	}
	
	/**
	 * Restituisce un array di tutte le sottostringhe risultanti dallo split di una stringa source
	 * in base al pattern passato
	 * @param source La stringa da splittare
	 * @param pattern il pattern regex in base al quale splittare la stringa source
	 * @return
	 */
	protected String[] tokenize(String source, String pattern) {
		return source.split(pattern);
	}

}
