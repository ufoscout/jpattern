package com.jpattern.gwt.client.bus;

import java.util.List;

import com.jpattern.gwt.client.AService;
import com.jpattern.gwt.client.event.IEvent;
import com.jpattern.gwt.client.event.IEventResult;

/**
 * 
 * @author Francesco Cina'
 *
 * Dec 5, 2011
 */
public abstract class ABusService extends AService {

	public abstract void addGlobalEventObserver(IGlobalEventObserver statusObserver);
	
	public abstract void removeGlobalEventObserver(IGlobalEventObserver statusObserver);
	
	public abstract void addEventObserver(IEventObserver eventObserver);
	
	public abstract void removeEventObserver(IEventObserver eventObserver);
	
	public abstract void addNavigationEventObserver(INavigationEventObserver navigationEventObserver);
	
	public abstract void removeNavigationEventObserver(INavigationEventObserver navigationEventObserver);
	
	public abstract void notifyGlobalEvent(String status);

	public abstract <T> void notifyEventStart(IEvent<T> event);
	
	public abstract <T> void notifyEventEnd(IEvent<T> event, IEventResult<T> eventResult);
	
	public abstract void notifyNavigationEvent(String navigationEventName);

	public abstract List<INavigationEventObserver> getNavigationEventObservers();

	public abstract List<IEventObserver> getEventObservers() ;

	public abstract List<IGlobalEventObserver> getStatusObservers() ;
	
}
