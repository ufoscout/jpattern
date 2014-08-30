package com.jpattern.gwt.client.presenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gwt.user.client.ui.HasWidgets;
import com.jpattern.gwt.client.IApplicationProvider;
import com.jpattern.gwt.client.NullApplicationProvider;
import com.jpattern.gwt.client.bus.IGlobalEventObserver;
import com.jpattern.gwt.client.logger.ILogger;
import com.jpattern.gwt.client.navigationevent.INavigationEvent;
import com.jpattern.gwt.client.navigationevent.INavigationEventCallback;
import com.jpattern.gwt.client.navigationevent.INavigationEventData;
import com.jpattern.gwt.client.navigationevent.INavigationEventWrapper;
import com.jpattern.gwt.client.navigationevent.NavigationEventData;
import com.jpattern.gwt.client.navigationevent.NavigationEventWrapper;
import com.jpattern.gwt.client.navigationevent.NullNavigationEventData;
import com.jpattern.gwt.client.session.ISecurityContext;
import com.jpattern.gwt.client.view.IShowViewStrategy;
import com.jpattern.gwt.client.view.IView;
import com.jpattern.shared.result.IErrorMessage;

/**
 * 
 * @author Francesco Cina'
 *
 * 14 Apr 2011
 */

public abstract class APresenter<T extends IView> implements IPresenter, IGlobalEventObserver {
	
	private final IShowViewStrategy<T> showViewStrategy;
	private IPresenter parentPresenter;
	private final Map<String, INavigationEventData> registeredNavigationEvents = new HashMap<String, INavigationEventData>();
	private final Map<String[], Map<String,String>> initialNavigationEvents = new HashMap<String[], Map<String,String>>();
	private final List<String> initialNavigationEventsToIgnore = new ArrayList<String>();
	private boolean initialized = false;
	private IApplicationProvider provider;
	private ILogger logger;
	private IHierarchyStrategy hierarchyStrategy = new DefaultHierarchyStrategy();
	private boolean waitAsynchReady = false;
	private final IPresenterDefinition presenterDefinition;

	/**
	 * 
	 * @param showViewStrategy
	 * @param navigationEvent the navigationEvent that created the presenter
	 */
	public APresenter(IShowViewStrategy<T> showViewStrategy, IPresenterDefinition presenterDefinition) {
		this.showViewStrategy = showViewStrategy;
		this.presenterDefinition = presenterDefinition;
		showViewStrategy.getView().visit(this);
	}
	
	@Override
	public final void onEventError(List<IErrorMessage> errorMessages) {
		getLogger().debug("onEventError", "Send " + errorMessages.size() + " to errorArea");
		showViewStrategy.getView().getNotificationArea().addErrorMessages(errorMessages);
	}
	
	@Override
	public final void onEventStart() {
		getLogger().debug("onEventStart", "Method called");
		showViewStrategy.getView().getNotificationArea().clear();
		showViewStrategy.getView().getNotificationArea().operationStart();
	}
	
	@Override
	public final void onEventEnd() {
		getLogger().debug("onEventEnd", "Method called");
		showViewStrategy.getView().getNotificationArea().operationFinished();
	}
	
	@Override
	public final T getView() {
		return showViewStrategy.getView();
	}
	
	/**
	 * This is the first step performed to obtain a complete visualisation of the associated IView.
	 * This method is executed only once when the Presenter is created.
	 * This method is called just after the IShowViewStrategy.onLoadStart() method and before the
	 * IShowViewStrategy.onLoadCompleted() method.
	 * 
	 * Here is the point where asynchronous actions should be performed. If you need to end the execution 
	 * of the asynchronous actions before display the view, you must call the 
	 * It's VERY important to call the ready() method when all the actions are performed otherwise
	 * the preRender() phase will never be called and the IView should not be completely rendered. 
	 */
	protected abstract void init();
	
	/**
	 * This is the second step performed to a complete visualization of the associated IView.
	 * this method is performed every time the render() method is called on the Presenter.
	 * This is performed just before the IShowViewStrategy.onLoadStart() method.
	 * This method is called before the display of the IView.
	 * DO NOT call ready() inside this method! This generates an infinite loop!
	 */
	protected abstract void preRender();
	
	/**
	 * This method can be called only inside the "init()" method.
	 * When called the rendering of the view is not performed until the ready() method is not explicitly called.
	 */
	protected final void waitAsynchReady() {
		waitAsynchReady  = true;
	}
	
	/**
	 * This method must be called when all the necessary operations to load necessary data are ended.
	 * If the "waitAsynchReady()" method has been explicitly called this method is not executed automatically and
	 * must be explicitly called. Usually this is called inside the callback action of an asynchronous event launched in the "init()" method. 
	 * This causes the final rendering of the IView. 
	 */
	protected final void ready() {
		if (waitAsynchReady) {
			doReady();
		}
	}

	private final void doReady() {
		preRender();
		showViewStrategy.onLoadCompleted();
		initialized = true;
		launchInitialEvents();
	}
	
	@Override
	public final void setParent(IPresenter parentPresenter) {
		this.parentPresenter = parentPresenter;
		showViewStrategy.setParent(getParentPresenter().getNavigationEventData(getNavigationEvent().getName()).getEventTarget());
	}
	
	@Override
	public final void initPresenter() {
		if (!isUserAuthorized()) return;
		if (!initialized) {
			waitAsynchReady = false;
			showViewStrategy.onLoadStart();
			getProvider().getBusService().addGlobalEventObserver(this);
			init();
			getView().init();
			if (!waitAsynchReady) {
				doReady();
			}
		} else {
			doReady();
		}
	}
	
	@Override
	public final boolean isUserAuthorized() {
		ISecurityContext securityContext = getProvider().getSession().getSecurityContext();
		if (presenterDefinition.requireAuthentication() && !securityContext.isUserValid()) {
			return false;
		}
		return securityContext.isUserInRole(presenterDefinition.getAllowedRoles());
	}
	
	@Override
	public final void hierarchy(List<String> hierarchyResult) {
		getParentPresenter().hierarchy(hierarchyResult);
		hierarchyStrategy.hierarchy(hierarchyResult, getName());
	}
	
	@Override
	public final void registerNavigationEvent(INavigationEvent navigationEvent, HasWidgets eventTarget, INavigationEventCallback navigationEventCallback) {
		registerNavigationEvent(new NavigationEventWrapper(navigationEvent), eventTarget, navigationEventCallback);
	}
	
	@Override
	public final void registerNavigationEvent(INavigationEventWrapper navigationEventWrapper, HasWidgets eventTarget,	INavigationEventCallback navigationEventCallback) {
		registeredNavigationEvents.put(navigationEventWrapper.getName(), new NavigationEventData(navigationEventWrapper, eventTarget, navigationEventCallback));
	}
	
	@Override
	public final INavigationEventData getNavigationEventData(String navigationEventName) {
		if (registeredNavigationEvents.containsKey(navigationEventName)) {
			return registeredNavigationEvents.get(navigationEventName);
		}
		return new NullNavigationEventData();
	}

	
	protected final IPresenter getParentPresenter() {
		if (parentPresenter==null) {
			parentPresenter = new NullPresenter(new NullApplicationProvider());
		}
		return parentPresenter;
	}


	@Override
	public final INavigationEvent getNavigationEvent() {
		return presenterDefinition.getNavigationEvent();
	}

	@Override
	public final boolean isInitialized() {
		return initialized;
	}

	private final void addInitialNavigationEvent(String[] navigationEventsName, Map<String, String> queryStringValues, boolean onlyIfTargetNotUsed) {
		ILogger logger = getLogger();
		logger.info("addInitialNavigationEvent", "registering initial event: " + navigationEventsName[0]);
		INavigationEventData newEventData = getNavigationEventData(navigationEventsName[0]);
		HasWidgets eventTarget = newEventData.getEventTarget();

		Iterator<String[]> mapIter = initialNavigationEvents.keySet().iterator();
		while (mapIter.hasNext()) {
			String[] element = mapIter.next();
			if (getNavigationEventData(element[0]).getEventTarget().equals(eventTarget)) {
				logger.debug("addInitialNavigationEvent", "Found another event with the same target, ignore current event? " + onlyIfTargetNotUsed);
				if (onlyIfTargetNotUsed) {
					return; 
				}
				logger.debug("addInitialNavigationEvent", "Found another event with the same target, removing it. Removed event name: "	+ element[0]);
				mapIter.remove();
			}
		}
		initialNavigationEvents.put(navigationEventsName, queryStringValues);
	}
	
	@Override
	public final void launchNavigationEvent(String navigationEventName, boolean registerHistory) {
		launchNavigationEvent(new String[]{navigationEventName}, registerHistory);
	}
	
	@Override
	public final void launchNavigationEvent(String[] navigationEventsName, boolean registerHistory) {
		launchNavigationEvent(navigationEventsName, registerHistory, new HashMap<String, String>());
	}
	
	@Override
	public final void launchNavigationEvent(String navigationEventName, boolean registerHistory, Map<String, String> queryStringValues) {
		launchNavigationEvent(new String[]{navigationEventName}, registerHistory, queryStringValues);
	}
	
	@Override
	public final void launchNavigationEvent(String[] navigationEventsName, boolean registerHistory, Map<String, String> queryStringValues) {
		launchNavigationEvent(navigationEventsName, registerHistory, queryStringValues, false);
	}
	
	@Override
	public final void launchNavigationEvent(String[] navigationEventsName, boolean registerHistory, Map<String, String> queryStringValues, boolean onlyIfTargetNotUsed) {
		getLogger().info("launchNavigationEvent", "event launched");
		if ( navigationEventsName!=null && navigationEventsName.length>0 ) {
			if (!initialized) {
				getLogger().debug("launchNavigationEvent", "event " + navigationEventsName[0] + " registered as initial event");
				addInitialNavigationEvent(navigationEventsName, queryStringValues, onlyIfTargetNotUsed);
			} else {
				getLogger().debug("launchNavigationEvent", "event " + navigationEventsName[0] + " execution triggered");
				localLaunchNavigationEvent(navigationEventsName, registerHistory, queryStringValues, onlyIfTargetNotUsed);
			}		
		}
	}
	
	final void localLaunchNavigationEvent(String[] navigationEventsName, boolean registerHistory, Map<String, String> queryStringValues, boolean onlyIfTargetNotUsed) {
		ILogger logger = getLogger();
		logger.info("localLaunchNavigationEvent", "method executed, navigationEventsName[0]=" + navigationEventsName[0]);
			String navigationEventName = navigationEventsName[0];
			String[] childrenEvent = new String[navigationEventsName.length-1];
			for (int i=1; i<navigationEventsName.length; i++) {
				logger.debug("localLaunchNavigationEvent", "navigationEventsName[" + i + "]=" + navigationEventsName[i]);
				childrenEvent[i-1] = navigationEventsName[i];
			}
			if (registeredNavigationEvents.containsKey(navigationEventName)) {
				logger.debug("localLaunchNavigationEvent", "launch event");
				INavigationEventData eventData = registeredNavigationEvents.get(navigationEventName);
				eventData.getNavigationEventWrapper().notifyNavigationEvent(this, queryStringValues, childrenEvent, registerHistory);
				eventData.getNavigationEventCallback().callback();
			} else {
				logger.debug("localLaunchNavigationEvent", "event not registered, passing it to the parentPresenter");
				getParentPresenter().launchNavigationEvent(navigationEventsName, registerHistory, queryStringValues, onlyIfTargetNotUsed);
			}
	}
	
	private final void launchInitialEvents() {
		for (Entry<String[], Map<String, String>> entry : initialNavigationEvents.entrySet()) {
			if ( !initialNavigationEventsToIgnore.contains(entry.getKey()[0]) ) {
				localLaunchNavigationEvent(entry.getKey(), false, entry.getValue(), false);
			}
		}
		initialNavigationEventsToIgnore.clear();
	}

	@Override
	public final String getName() {
		return getNavigationEvent().getName();
	}
	
	@Override
	public final void setProvider(IApplicationProvider provider) {
		this.provider = provider;
		this.logger = null;
		getView().setApplicationProvider(provider);
	}
	
	@Override
	public final IApplicationProvider getProvider() {
		if (provider == null) {
			provider = new NullApplicationProvider();
		}
		return provider;
	}
	
	protected final ILogger getLogger() {
		if (logger == null) {
			logger = getProvider().getLoggerService().getLogger(getClass());
		}
		return logger;
	}

	protected final void setHierarchyStrategy(IHierarchyStrategy hierarchyStrategy) {
		this.hierarchyStrategy = hierarchyStrategy;
	}

	@Override
	public final String[] getAllowedRole() {
		return getNavigationEvent().getAllowedRole();
	}
	
	@Override
	public final void onUnload() {
		getProvider().getBusService().removeGlobalEventObserver(this);
		onUnloadPresenter();
	}
	
	protected abstract void onUnloadPresenter();
}
