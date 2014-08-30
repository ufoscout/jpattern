package com.jpattern.gwt.client.session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jpattern.gwt.client.AService;

/**
 * 
 * @author Francesco Cina'
 *
 * Nov 28, 2011
 */
public class Session extends AService implements ISession {

	SecurityContext securityContext = new SecurityContext();
	Map<String, Object> attributes = new HashMap<String, Object>();
	private List<ISecurityContextObserver> securityContextObservers = new ArrayList<ISecurityContextObserver>();
	private List<ISessionObserver> sessionObservers = new ArrayList<ISessionObserver>();
	
	@Override
	public void login(IUserData userData) {
		securityContext.setUserData(userData);
		for (ISecurityContextObserver observer : securityContextObservers) {
			observer.onUserLogin(userData);
		}
	}

	@Override
	public void logout() {
		IUserData userData = securityContext.getUserData();
		securityContext.setUserData(new UserData("",false));
		cleanSession();
		for (ISecurityContextObserver observer : securityContextObservers) {
			observer.onUserLogout(userData);
		}
	}
	
	@Override
	public void cleanSession() {
		attributes = new HashMap<String, Object>();
		for (ISessionObserver observer : sessionObservers) {
			observer.onSessionClean();
		}
	}

	@Override
	public ISecurityContext getSecurityContext() {
		return securityContext;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getAttribute(String key, Class<T> aClass) {
		return (T) attributes.get(key);
	}

	@Override
	public void addAttribute(String key, Object value) {
		attributes.put(key, value);
		for (ISessionObserver observer : sessionObservers) {
			observer.onAttributeAdded(key);
		}
	}

	@Override
	public void removeAttribute(String key) {
		attributes.remove(key);
		for (ISessionObserver observer : sessionObservers) {
			observer.onAttributeRemoved(key);
		}
	}

	@Override
	public void addObserver(ISecurityContextObserver observer) {
		securityContextObservers.add(observer);
	}

	@Override
	public void removeObserver(ISecurityContextObserver observer) {
		securityContextObservers.remove(observer);
	}
	
	@Override
	public void addObserver(ISessionObserver observer) {
		sessionObservers.add(observer);
	}

	@Override
	public void removeObserver(ISessionObserver observer) {
		sessionObservers.remove(observer);
	}
}
