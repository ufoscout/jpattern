package com.jpattern.gwt.client.view;

import com.google.gwt.user.client.ui.HasWidgets;

/**
 * 
 * @author Francesco Cina
 *
 * 19/lug/2011
 */
public interface IShowViewStrategy<T extends IView> {

	/**
	 * Return the view associated with the strategy
	 * @return
	 */
	T getView();
	
	/**
	 * this is called when an asynchronous action begins
	 */
	void onLoadStart();
	
	/**
	 * This is called when a started asynchronous action ends its execution
	 */
	void onLoadCompleted();
	
	void setParent(HasWidgets container);
	
}
