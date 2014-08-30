package com.jpattern.gwt.client.presenter;

import com.jpattern.gwt.client.view.IView;
import com.jpattern.gwt.client.view.NullShowViewStrategy;
import com.jpattern.gwt.client.view.NullView;

/**
 * A default implementation of a MainPresenter.
 * This should be the main presenter of the client application 
 * @author cinafr
 *
 */
public class MainPresenter extends APresenter<IView>{

	public MainPresenter(IPresenterDefinition presenterDefinition) {
		super(new NullShowViewStrategy<IView>(new NullView()), presenterDefinition);
	}

	@Override
	protected void init() {
		ready();
	}

	@Override
	protected void preRender() {
	}

	@Override
	public void onGlobalEvent(String status) {
	}

	@Override
	protected void onUnloadPresenter() {
	}

}
