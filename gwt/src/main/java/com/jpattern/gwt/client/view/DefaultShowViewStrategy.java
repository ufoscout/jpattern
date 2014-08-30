package com.jpattern.gwt.client.view;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * 
 * A strategy that simply shows the associated IView.
 * The view is showed only when the render() method in the presenter is called.
 * 
 * @author Francesco Cina
 *
 * 19/lug/2011
 */
public class DefaultShowViewStrategy<T extends IView> implements IShowViewStrategy<T> {

	private final T view;
	private FlowPanel viewContainer = new FlowPanel();

	public DefaultShowViewStrategy(T view) {
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
		view.render(viewContainer);		
	}

	@Override
	public void setParent(HasWidgets container) {
		container.clear();
		viewContainer = new FlowPanel();
		container.add(viewContainer);		
	}

}
