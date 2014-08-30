package com.jpattern.gwt.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasVisibility;
import com.google.gwt.user.client.ui.HasWidgets;
import com.jpattern.gwt.client.IApplicationProvider;
import com.jpattern.gwt.client.NullApplicationProvider;
import com.jpattern.gwt.client.navigationevent.INavigationEventWrapper;
import com.jpattern.gwt.client.presenter.IPresenter;

/**
 * 
 * A View based on the gwt Composite component
 * 
 * @author Francesco Cina'
 *
 * 20 Apr 2011
 */
public abstract class AGwtCompositeView<T extends IPresenter> extends Composite implements IView {

	private T presenter;
	private IApplicationProvider provider = new NullApplicationProvider();
	
	@Override
	public final void render(HasWidgets container) {
		if (container!=null) {
		    container.clear();
		    container.add(this);
		}
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public final void visit(IPresenter presenter) {
		this.presenter = (T) presenter;
	}

	@Override
	public final T getPresenter() {
		return presenter;
	}

	@Override
	public final void onUnload() { 
		onUnloadView();
		getPresenter().onUnload();
	}
	
	@Override
	public final void setApplicationProvider(IApplicationProvider provider) {
		this.provider = provider;
	}
	
	
	protected final IApplicationProvider getProvider() {
		if (provider == null) {
			provider = new NullApplicationProvider();
		}
		return provider;
	}
	
	@Override
	public final void hideIfUserNotInRole(HasVisibility widget, String[] allowedRoles) {
		if (!getProvider().getSession().getSecurityContext().isUserInRole(allowedRoles)) {
			widget.setVisible(false);
		}
	}

	@Override
	public final void hideIfUserIsNotValid(HasVisibility widget) {
		if (!getProvider().getSession().getSecurityContext().isUserValid()) {
			widget.setVisible(false);
		}
	}
	
	@Override
	public final void hideIfUserHasNoRightsForNavigationEvent(HasVisibility widget, String navigationEventName) {
		INavigationEventWrapper nav = getPresenter().getNavigationEventData(navigationEventName).getNavigationEventWrapper();
		boolean requiredAuthentication = nav.isRequireAuthentication();
		String[] requiredRoles = nav.getAllowedRole();
		if (!getProvider().getSession().getSecurityContext().isAuthorized(requiredAuthentication, requiredRoles)) {
			widget.setVisible(false);
		}
	}
	
	protected abstract void onUnloadView();
	
}
