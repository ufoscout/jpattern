package com.jpattern.gwt.client.navigationevent;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.HasWidgets;
import com.jpattern.gwt.client.IApplicationProvider;
import com.jpattern.gwt.client.history.IHistoryManager;
import com.jpattern.gwt.client.logger.ILogger;
import com.jpattern.gwt.client.presenter.APresenter;
import com.jpattern.gwt.client.presenter.IPresenter;
import com.jpattern.gwt.client.presenter.NullHierarchyStrategy;
import com.jpattern.gwt.client.presenter.NullPresenter;
import com.jpattern.gwt.client.view.IView;
import com.jpattern.gwt.client.view.NullShowViewStrategy;
import com.jpattern.gwt.client.view.NullView;

/**
 * 
 * @author Francesco Cina
 *
 * 03/ago/2011
 */
public class RootNavigationEvent extends ANavigationEvent {

	private final INavigationEvent mainNavigationEvent;
	private final HasWidgets target;
	private final IApplicationProvider applicationProvider;
	
	public RootNavigationEvent(INavigationEvent mainNavigationEvent, HasWidgets target, IApplicationProvider applicationProvider) {
		super("");
		this.mainNavigationEvent = mainNavigationEvent;
		this.target = target;
		this.applicationProvider = applicationProvider;
	}
	
	/**
	 * Start the client GWT application
	 */
	public IPresenter startApplication() {
		return launch(new NullPresenter(applicationProvider), new HashMap<String, String>(), new String[0]);
	}

	@Override
	protected IPresenter exec(Map<String, String> queryStringValues) {
		ILogger logger = applicationProvider.getLoggerService().getLogger(getClass());
		logger.debug("exec", "root presenter generated");
		IPresenter rootPresenter = new RootPresenter(this);
		rootPresenter.setProvider(applicationProvider);
		IHistoryManager historyManager = applicationProvider.getHistoryService().getHistoryManager();
		historyManager.setRootPresenter(rootPresenter);
		historyManager.updateState();
		return rootPresenter;
	}
	
	class RootPresenter extends APresenter<IView> {
		
		ILogger logger;

		public RootPresenter(INavigationEvent navigationEvent) {
			super(new NullShowViewStrategy<IView>(new NullView()), getPresenterDefinition());
			setHierarchyStrategy(new NullHierarchyStrategy());
			registerNavigationEvent(mainNavigationEvent, target, new NullNavigationEventCallback());
			ready();
		}

		@Override
		public void preRender() {
		}

		@Override
		public void init() {
		}

		@Override
		public void onGlobalEvent(String status) {
		}

		@Override
		protected void onUnloadPresenter() {
		}
	}
	
}
