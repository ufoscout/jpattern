package com.jpattern.gwt.client.history;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.jpattern.gwt.client.NullApplicationProvider;
import com.jpattern.gwt.client.logger.ILogger;
import com.jpattern.gwt.client.logger.ALoggerService;
import com.jpattern.gwt.client.navigationevent.INavigationEvent;
import com.jpattern.gwt.client.presenter.IPresenter;
import com.jpattern.gwt.client.presenter.NullPresenter;
import com.jpattern.gwt.client.util.QueryString;

/**
 * 
 * @author Francesco Cina
 *
 * 27/lug/2011
 */
public class HistoryManager implements IHistoryManager {
	
	private static String TOKEN_SEPARATOR = "_";
	
	private final ILogger logger;
	private final IHistoryManagerObserver historyManagerObserver;
	private final IHistoryEngine historyEngine;
	private IPresenter rootPresenter = new NullPresenter(new NullApplicationProvider());

	public HistoryManager(IHistoryEngine historyEngine, IHistoryManagerObserver historyManagerObserver, ALoggerService loggerService) {
		this.logger = loggerService.getLogger(getClass());
		this.historyManagerObserver = historyManagerObserver;
		this.historyEngine = historyEngine;
		historyEngine.init(this);
	}

	@Override
	public void onEvent(String eventName) {
		logger.debug("onEvent", "Event " + eventName);
		IEventToken eventToken = generateEventToken(eventName);
		String[] tokens = generateTokenList( eventToken.getToken() );
		Map<String, String> queryStringMap = QueryString.toMap(eventToken.getQueryString());
		launchEvents( tokens , queryStringMap);
		historyManagerObserver.onEvent(eventName);
	}

	@Override
	public void registerHistory(IPresenter parentPresenter, Map<String, String> queryStringValues, String[] childrenEvent, boolean registerEvent, INavigationEvent navigationEvent) {
		logger.debug("registerHistory", "Method called");
		if (registerEvent) {
			List<String> presenterHierarchy = new ArrayList<String>();
			parentPresenter.hierarchy(presenterHierarchy);
			presenterHierarchy.add(navigationEvent.getName());
			
			String token = "";
			if (presenterHierarchy.size()>0 || queryStringValues.size()>0) {
				token = generateTokenString(presenterHierarchy);
				for (int i=0; i<childrenEvent.length; i++) {
					token = token + TOKEN_SEPARATOR + childrenEvent[i]; 
				}
				token = token + QueryString.toQueryString(queryStringValues);
				historyEngine.registerEvent(token);
				logger.info("registerHistory", "New token registered: " + token);
			}
			
			historyManagerObserver.onRegisterHistory(token, presenterHierarchy);
		} else {
			logger.debug("registerHistory", "No new token created. Launch now navigationEvent with name: " + navigationEvent.getName());
			navigationEvent.launch(parentPresenter, queryStringValues, childrenEvent);
		}
	}

	public String generateTokenString(List<String> presenterHierarchy) {
		StringBuffer token = new StringBuffer();
		int size = presenterHierarchy.size();
		for (int i=0; i<size; i++) {
			token.append( presenterHierarchy.get(i) );
			if (i!=(size-1)) {
				token.append(TOKEN_SEPARATOR);
			}
		}
		return token.toString();
	}
	
	public String[] generateTokenList(String tokenString) {
		String[] result = tokenString.split(TOKEN_SEPARATOR);
		return result;
	}
	
	public void launchEvents(String[] tokens , Map<String, String> queryStringMap) {
		logger.info("launchEvents", "Launch event");
		for (int i=0; i<tokens.length; i++) {
			logger.debug("launchEvents", "Event Token " + i + " " + tokens[i]);
		}
		for (Entry<String, String> entry : queryStringMap.entrySet()) {
			logger.debug("launchEvents", "QueryString element: key=[" + entry.getKey() + "] - value [" + entry.getValue() + "]");
		}
		rootPresenter.launchNavigationEvent(tokens, false, queryStringMap);
	}
	
	@Override
	public void updateState() {
		historyEngine.updateState();		
	}
	
	public IEventToken generateEventToken(String eventName) {
		String queryString = "";
		String token = eventName;
		if (eventName.contains("?")) {
			token = eventName.substring(0, eventName.lastIndexOf("?"));
			queryString = eventName.substring(eventName.lastIndexOf("?"));
		}
		return new EventToken(token, queryString);
	}

	@Override
	public void setRootPresenter(IPresenter presenter) {
		this.rootPresenter = presenter;	
		logger.info("setRootPresenter", "New parent presenter setted");
	}

}
