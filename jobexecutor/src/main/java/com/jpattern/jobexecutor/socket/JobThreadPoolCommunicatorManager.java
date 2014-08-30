package com.jpattern.jobexecutor.socket;

import java.io.IOException;

import com.jpattern.jobexecutor.IBooleanWrapper;
import com.jpattern.jobexecutor.ICommandExecutorHandler;
import com.jpattern.jobexecutor.IJobThreadPool;
import com.jpattern.jobexecutor.IWrappedResult;
import com.jpattern.jobexecutor.console.EntryPointExecutorCommand;
import com.jpattern.jobexecutor.console.WrappedCommunicationChannelResult;
import com.jpattern.jobexecutor.core.BooleanWrapper;
import com.jpattern.jobexecutor.iostream.ICommunicationChannel;

/**
 * 
 * @author Francesco Cina'
 *
 * 29/mar/2010
 */
public class JobThreadPoolCommunicatorManager implements ICommunicatorManager {

	private static final long serialVersionUID = 1L;
	private IJobThreadPool jobThreadPool;

	public JobThreadPoolCommunicatorManager( IJobThreadPool jobThreadPool ) {
		this.jobThreadPool = jobThreadPool;
	}
	
	public void execute(ICommunicationChannel communicatioChannel) {
		IBooleanWrapper stopCommand = new BooleanWrapper(false);
		ICommandExecutorHandler command = new EntryPointExecutorCommand(jobThreadPool );
		
		while (!stopCommand.getValue()) {
			
			try {
//				System.err.println("leggo:");
				String readedValue = communicatioChannel.read();
				
//				System.err.println("letto: "  + readedValue);
				IWrappedResult wrapperResult = new WrappedCommunicationChannelResult(communicatioChannel);
				command = command.exec(readedValue, wrapperResult, stopCommand);
				//communicatioChannel.write(wrapperResult.result());
				
			} catch (IOException e) {
//				e.printStackTrace();
				stopCommand.setValue(true);
			}
			
		}
		communicatioChannel.close();
	}

	public String getName() {
		return jobThreadPool.getName();
	}
	
}
