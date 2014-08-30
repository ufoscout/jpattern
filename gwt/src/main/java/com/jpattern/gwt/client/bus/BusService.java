package com.jpattern.gwt.client.bus;

import java.util.ArrayList;
import java.util.List;

import com.jpattern.gwt.client.IApplicationProvider;
import com.jpattern.gwt.client.event.IEvent;
import com.jpattern.gwt.client.event.IEventResult;
import com.jpattern.gwt.client.logger.ILogger;

/**
 * 
 * @author Francesco Cina'
 *
 * Dec 5, 2011
 */
public class BusService extends ABusService {
	
	private final List<IGlobalEventObserver> statusObservers = new ArrayList<IGlobalEventObserver>();
	private final List<IEventObserver> eventBusObservers = new ArrayList<IEventObserver>();
	private final List<INavigationEventObserver> navigationEventObservers = new ArrayList<INavigationEventObserver>();
	
	private final ILogger logger;

	public BusService(IApplicationProvider provider) {
		this.logger = provider.getLoggerService().getLogger(getClass());
	}
		
	@Override
	public void addGlobalEventObserver(IGlobalEventObserver statusObserver) {
		statusObservers.add(statusObserver);
	}

	@Override
	public void removeGlobalEventObserver(IGlobalEventObserver statusObserver) {
		statusObservers.remove(statusObserver);
	}
	
	@Override
	public void addEventObserver(IEventObserver eventBusObserver) {
		eventBusObservers.add(eventBusObserver);
	}

	@Override
	public void removeEventObserver(IEventObserver eventBusObserver) {
		eventBusObservers.remove(eventBusObserver);
	}
	
	@Override
	public void addNavigationEventObserver(INavigationEventObserver navigationEventObserver) {
		navigationEventObservers.add(navigationEventObserver);
	}

	@Override
	public void removeNavigationEventObserver(INavigationEventObserver navigationEventObserver) {
		navigationEventObservers.remove(navigationEventObserver);		
	}

	@Override
	public void notifyGlobalEvent(String globalEvent) {
		if (globalEvent!=null) {
			logger.debug("notifyGlobalEvent", "GlobalEvent [" + globalEvent + "] launch notified to the bus");
			for (IGlobalEventObserver observer : statusObservers) {
				observer.onGlobalEvent(globalEvent);
			}			
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <T> void notifyEventStart(IEvent<T> event) {
		if (event!=null) {
			logger.debug("notifyEventStart", "Event class [" + event.getClass() + "] execution start notified to the bus");
			for (IEventObserver observer : eventBusObservers) {
				Class<? extends IEvent<T>> eventClass = (Class<? extends IEvent<T>>) event.getClass();
				observer.onEventStart(eventClass);
			}			
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> void notifyEventEnd(IEvent<T> event, IEventResult<T> eventResult) {
		if (event!=null) {
			Class<? extends IEvent<T>> eventClass = (Class<? extends IEvent<T>>) event.getClass();
			logger.debug("notifyEventEnd", "Event class [" + eventClass + "] execution end notified to the bus. Execution success? " + eventResult.isValid());
			for (IEventObserver observer : eventBusObservers) {
				observer.onEventEnd(eventClass, eventResult);
			}
		}
	}

	@Override
	public void notifyNavigationEvent(String navigationEventName) {
		if (navigationEventName!=null) {
			logger.debug("notifyNavigationEvent", "NavigationEvent [" + navigationEventName + "] launch notified to the bus");
			for (INavigationEventObserver observer : navigationEventObservers) {
				observer.onNavigationEvent(navigationEventName);
			}			
		}
	}
	
	@Override
	public List<IGlobalEventObserver> getStatusObservers() {
		return statusObservers;
	}

	@Override
	public List<IEventObserver> getEventObservers() {
		return eventBusObservers;
	}

	@Override
	public List<INavigationEventObserver> getNavigationEventObservers() {
		return navigationEventObservers;
	}

}
