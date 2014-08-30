package com.jpattern.gwt.event;

import com.jpattern.gwt.client.presenter.APresenter;
import com.jpattern.gwt.client.presenter.PresenterDefinition;
import com.jpattern.gwt.client.view.IView;
import com.jpattern.gwt.client.view.NullShowViewStrategy;

/**
 * 
 * @author Francesco Cina'
 *
 */
public class MockPresenter extends APresenter<IView> {
	
	private boolean preDisplayExecuted = false;
	public boolean isPreDisplayExecuted() {
		return preDisplayExecuted;
	}

	public boolean isPostDisplayExecuted() {
		return postDisplayExecuted;
	}

	private boolean postDisplayExecuted = false;

	public MockPresenter(IView view) {
		super(new NullShowViewStrategy<IView>(view), new PresenterDefinition());
	}

	@Override
	public void preRender() {
		preDisplayExecuted = true;
	}

	@Override
	public void init() {
		postDisplayExecuted = true;
	}
	
	@Override
	public void onGlobalEvent(String status) {
	}

	@Override
	protected void onUnloadPresenter() {
	}

}
