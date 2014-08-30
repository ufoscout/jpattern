package com.jpattern.service.log;

import java.util.HashMap;
import java.util.Map;

import com.jpattern.service.log.event.IEvent;
import com.jpattern.service.log.event.ITrigger;
import com.jpattern.service.log.event.NullTrigger;

/**
 * 
 * @author Francesco Cina'
 *
 * 09/apr/2010
 */
public class Roster implements IRoster {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map<String, IEvent> events;

    private ITrigger triggerEvent;

    public Roster(ITrigger triggerEvent) {
        this.events = new HashMap<String, IEvent>();
        this.triggerEvent = triggerEvent;

    }

	public void addEvent(IEvent aEvent) {
        if (aEvent==null){
        	return;
        }
            
        aEvent.acceptITrigger(triggerEvent);
        events.put(aEvent.getName(), aEvent);
    }
    
    public void suspendEvent(String eventName) {
        if (!events.containsKey(eventName)) 
                return; 
            
        IEvent event = events.get(eventName);
        event.acceptITrigger(new NullTrigger());
    }
    
    public void reinstateEvent(String eventName) {
        if (!events.containsKey(eventName)) 
                return; 
        events.get(eventName).acceptITrigger(triggerEvent);
    }    

}
