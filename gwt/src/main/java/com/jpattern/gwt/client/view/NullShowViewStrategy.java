package com.jpattern.gwt.client.view;

import com.google.gwt.user.client.ui.HasWidgets;


/**
 * A strategy that never shows the view
 * 
 * @author Francesco Cina
 *
 * 19/lug/2011
 */
public class NullShowViewStrategy<T extends IView> implements IShowViewStrategy<T> {

	private final T view;

	public NullShowViewStrategy(T view) {
		this.view = view;
	}
	
	@Override
	public T getView() {
		return view;
	}

	@Override
	public void onLoadStart() {
	}

	@Override
	public void onLoadCompleted() {
	}

	@Override
	public void setParent(HasWidgets container) {
	}

}
