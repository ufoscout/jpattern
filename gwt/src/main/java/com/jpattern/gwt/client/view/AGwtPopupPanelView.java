package com.jpattern.gwt.client.view;

import com.google.gwt.user.client.ui.HasVisibility;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PopupPanel;
import com.jpattern.gwt.client.IApplicationProvider;
import com.jpattern.gwt.client.NullApplicationProvider;
import com.jpattern.gwt.client.navigationevent.INavigationEventWrapper;
import com.jpattern.gwt.client.presenter.IPresenter;

/**
 * 
 * A View based on the gwt PopupPanel component
 * 
 * @author Francesco Cina'
 *
 * 20 Apr 2011
 */
public abstract class AGwtPopupPanelView<T extends IPresenter> extends PopupPanel implements IView {

	private T presenter;
	private IApplicationProvider provider = new NullApplicationProvider();

	/**
	 * @param autoHide
	 *            <code>true</code> if the popup should be automatically hidden
	 *            when the user clicks outside of it or the history token
	 *            changes.
	 * @param modal
	 *            <code>true</code> if keyboard or mouse events that do not
	 *            target the PopupPanel or its children should be ignored
	 */
	public AGwtPopupPanelView(boolean autoHide, boolean modal) {
		super(autoHide, modal);
	}
	   
	@Override
	public final void render(HasWidgets container) {
		show();
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
		this.provider  = provider;
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
