package com.jpattern.jobexecutor.socket.starter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * @author Francesco Cina'
 *
 * 10/ago/2010
 */
public class SocketActionFactory {
	
	public static String MANAGER_CONSOLE = "shell";
	public static String START = "start";
	public static String SHUTDOWN = "shutdown";
	public static String SHUTDOWN_BRUTAL = "shutdownBrutal";
	private Map<String,ASocketAction> actionMap;

	public SocketActionFactory() {
		actionMap = new HashMap<String, ASocketAction>();
		
		actionMap.put( START , new StartSocketAction());
		actionMap.put( SHUTDOWN , new ShutdownSocketAction());
		actionMap.put( SHUTDOWN_BRUTAL , new ShutdownBrutalSocketAction());
		actionMap.put( MANAGER_CONSOLE, new ConsoleManagerSocketAction());
		
	}
	
	public boolean existsAction( String actionName ){
		return getActionNames().contains(actionName);
	}

	public List<String> getActionNames() {
		List<String> actionNames = new ArrayList<String>();
		
		for ( Entry<String, ASocketAction> action : actionMap.entrySet() ) {
			actionNames.add( action.getKey() );
		}
		
		return actionNames;
	}

	public ASocketAction getAction(String actionName) {
		ASocketAction actionResult = new NullSocketAction();
		
		for ( Entry<String, ASocketAction> action : actionMap.entrySet() ) {
			if ( action.getKey().equals(actionName)) {
				actionResult = action.getValue();
			}
		}
		
		return actionResult;
	}
	
	
	
	
}
