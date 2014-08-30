package com.jpattern.jobexecutor.socket.starter;

/**
 * 
 * @author Francesco Cina'
 *
 * 04/apr/2010
 */
public class SimpleExecutor {

	private String[] args;
	private AJobsConfigurator configurator;

	public SimpleExecutor(AJobsConfigurator configurator, String[] args) {
		
		if ((args == null) || (args.length == 0)) {
			args = new String[1];
			args[0] = "";
		}
		
		this.configurator = configurator;
		this.args = args;

	}
	
	public void launch() throws Exception {
		SocketActionFactory socketActionFactory = new SocketActionFactory();
		
		if ( socketActionFactory.existsAction( args[0]) ) {
			ASocketAction socketAction = socketActionFactory.getAction( args[0] );
			socketAction.exec(configurator);
		}
		else {
			System.out.println("Unknown parameter: " + args[0] + ". Valid parameters are:");
			for (String parameter : socketActionFactory.getActionNames()) {
				System.out.println( parameter );
			}
		}
		
	}

}
