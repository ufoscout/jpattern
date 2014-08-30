package com.jpattern.gwt.client.view;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 * A strategy that shows a temporary image while asynchronous operations are performed.
 * 
 * @author Francesco Cina
 *
 * 19/lug/2011
 */
public class SwapWidgetShowViewStrategy<T extends IView> implements IShowViewStrategy<T> {

	private final T view;
	private final Widget temporaryWidget;
	private FlowPanel viewContainer = new FlowPanel();

	public SwapWidgetShowViewStrategy(T view, Widget temporaryWidget) {
		this.view = view;
		this.temporaryWidget = temporaryWidget;
	}
	
	@Override
	public T getView() {
		return view;
	}

	@Override
	public void onLoadStart() {
		viewContainer.add(temporaryWidget);
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
