package com.jpattern.gwt.client.presenter;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.ui.HasWidgets;
import com.jpattern.gwt.client.IApplicationProvider;
import com.jpattern.gwt.client.navigationevent.INavigationEvent;
import com.jpattern.gwt.client.navigationevent.INavigationEventCallback;
import com.jpattern.gwt.client.navigationevent.INavigationEventData;
import com.jpattern.gwt.client.navigationevent.INavigationEventWrapper;
import com.jpattern.gwt.client.view.IView;
import com.jpattern.shared.result.IErrorMessage;

/**
 * 
 * @author Francesco Cina'
 *
 * 20 Apr 2011
 */
public interface IPresenter {

	/**
	 * Return the navigationEvent which generated the presenter
	 * @return
	 */
	INavigationEvent getNavigationEvent();
	
	/**
	 * Return whether the presenter ended is initialisation and has been rendered
	 * @return
	 */
	boolean isInitialized();
	
	/**
	 * Send a event start notification and clear the error area before the execution of a new event
	 */
	void onEventStart();
	
	/**
	 * Send a event end notification to the notification area
	 */
	void onEventEnd();
	
	/**
	 * Called by the IEvent when the result of the execution is not valid
	 * @param errorMessages
	 */
	void onEventError(List<IErrorMessage> errorMessages);
	
	/**
	 * Initialize the presenter and show the related IView
	 */
	void initPresenter();

	/**
	 * Set the parent presenter
	 * @param parentPresenter
	 */
	void setParent(IPresenter parentPresenter);
	
	/**
	 * Return an ordered list of all the presenter names in the current hierarchy, from the root parent to the leaf
	 * @return
	 */
	void hierarchy(List<String> hierarchyResult);

	/**
	 * Return the IView object associated with this presenter
	 * @return
	 */
	IView getView();
	
	/**
	 * Register a new navigation event throwable by the current presenter
	 * @param navigationEvent
	 * @param eventTarget the target area where the new view will be rendered
	 * @param navigationEventCallback
	 */
	void registerNavigationEvent(INavigationEvent navigationEvent, HasWidgets eventTarget, INavigationEventCallback navigationEventCallback);
	
	/**
	 * Register a new navigation event throwable by the current presenter
	 * @param navigationEvent
	 * @param eventTarget the target area where the new view will be rendered
	 * @param navigationEventCallback
	 */
	void registerNavigationEvent(INavigationEventWrapper navigationEventWrapper, HasWidgets eventTarget, INavigationEventCallback navigationEventCallback);
	
	/**
	 * Launch a navigation event based on his name.
	 * If an event with that name is present in the current Presenter is thrown, otherwise the event is propagated to the parent presenter 
	 * @param registerHistory indicate if the event must be registered in the navigation history, this must be true only if the event has been thrown by an explicit user action
	 * @param navigationEventName
	 */
	void launchNavigationEvent(String navigationEventName, boolean registerHistory);
	
	/**
	 * Launch a navigation event based on his name.
	 * If an event with that name is present in the current Presenter is thrown, otherwise the event is propagated to the parent presenter 
	 * @param registerHistory indicate if the event must be registered in the navigation history, this must be true only if the event has been thrown by an explicit user action
	 * @param navigationEventName
	 */
	void launchNavigationEvent(String[] navigationEventsName, boolean registerHistory);
	
	/**
	 * Launch a navigation event based on his name.
	 * If an event with that name is present in the current Presenter is thrown, otherwise the event is propagated to the parent presenter 
	 * @param queryStringValues a map with key value pair to be passed at the navigationEvent exec() method to build the new Presenter
	 * @param registerHistory indicate if the event must be registered in the navigation history, this must be true only if the event has been thrown by an explicit user action
	 * @param navigationEventName
	 */
	void launchNavigationEvent(String navigationEventName, boolean registerHistory, Map<String, String> queryStringValues);
	
	/**
	 * Launch a navigation event based on his name.
	 * If an event with that name is present in the current Presenter is thrown, otherwise the event is propagated to the parent presenter 
	 * @param queryStringValues a map with key value pair to be passed at the navigationEvent exec() method to build the new Presenter
	 * @param registerHistory indicate if the event must be registered in the navigation history, this must be true only if the event has been thrown by an explicit user action
	 * @param navigationEventName
	 */
	void launchNavigationEvent(String[] navigationEventsName, boolean registerHistory, Map<String, String> queryStringValues);
	
	/**
	 * Launch a navigation event based on his name.
	 * If an event with that name is present in the current Presenter is thrown, otherwise the event is propagated to the parent presenter.
	 * The onlyIfTargetNotUsed parameter is used in case that the event is not thrown immediately but scheduled for a future execution. In that case the event will be 
	 * launched only if the target area has not been reserved by one another NavigationEvent.
	 * @param navigationEventsName
	 * @param queryStringValues a map with key value pair to be passed at the navigationEvent exec() method to build the new Presenter
	 * @param registerHistory indicate if the event must be registered in the navigation history, this must be true only if the event has been thrown by an explicit user action
	 * @param onlyIfTargetNotUsed
	 */
	void launchNavigationEvent(String[] navigationEventsName, boolean registerHistory, Map<String, String> queryStringValues, boolean onlyIfTargetNotUsed);
	
	/**
	 * Return information related to a registered INavigationEvent
	 * @return
	 */
	INavigationEventData getNavigationEventData(String navigationEventName);

	/**
	 * Return the presenter's name. It correspond to the name of the NavigationEvent which created the presenter.
	 * @return
	 */
	String getName();
	
	/**
	 * This method is called when the view related to this Presenter is destroyed.
	 * In fact this is a real destructor for the presenter.
	 */
	void onUnload();

	/**
	 * 
	 * @param provider
	 */
	void setProvider(IApplicationProvider provider);
	
	/**
	 * return a list of all the roles allowed to access the presenter instance
	 * @return
	 */
	String[] getAllowedRole();

	/**
	 * Return true if the current userSession is authorised to access this presenter  
	 * @return
	 */
	boolean isUserAuthorized();

	IApplicationProvider getProvider();

}
