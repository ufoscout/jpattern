package com.jpattern.gwt.presenter;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.HasWidgets;
import com.jpattern.gwt.client.BaseTest;
import com.jpattern.gwt.client.navigationevent.ANavigationEvent;
import com.jpattern.gwt.client.presenter.APresenter;
import com.jpattern.gwt.client.presenter.IPresenter;
import com.jpattern.gwt.client.presenter.IPresenterDefinition;
import com.jpattern.gwt.client.presenter.NullPresenter;
import com.jpattern.gwt.client.session.UserData;
import com.jpattern.gwt.client.view.IShowViewStrategy;
import com.jpattern.gwt.client.view.IView;
import com.jpattern.gwt.client.view.NullView;

/**
 * 
 * @author cinafr
 *
 */
public class APresenterSecurityTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testPresenterSecurity0() throws Exception {
		
		boolean onlyValidUserSession = false;
		String[] rolesAllowed = new String[0];
		
		NavEvent event = new NavEvent(onlyValidUserSession, rolesAllowed);
		CustomPresenter presenter = (CustomPresenter) event.launch(new NullPresenter(getProvider()), new HashMap<String, String>(), new String[0]);

		assertTrue( presenter.initCalled );
		assertTrue( presenter.preRenderCalled );
		assertTrue( presenter.showViewStrategy.onLoadStartCalled );
		assertTrue( presenter.showViewStrategy.onLoadCompletedCalled );
		
	}
	
	public void testPresenterSecurity1() throws Exception {
		
		boolean onlyValidUserSession = true;
		String[] rolesAllowed = new String[0];
		
		NavEvent event = new NavEvent(onlyValidUserSession, rolesAllowed);
		CustomPresenter presenter = (CustomPresenter) event.launch(new NullPresenter(getProvider()), new HashMap<String, String>(), new String[0]);

		assertFalse( presenter.initCalled );
		assertFalse( presenter.preRenderCalled );
		assertFalse( presenter.showViewStrategy.onLoadStartCalled );
		assertFalse( presenter.showViewStrategy.onLoadCompletedCalled );
		
	}
	
	public void testPresenterSecurity2() throws Exception {
	
		getProvider().getSession().login(new UserData("", true));
		
		boolean onlyValidUserSession = true;
		String[] rolesAllowed = new String[0];
		
		NavEvent event = new NavEvent(onlyValidUserSession, rolesAllowed);
		CustomPresenter presenter = (CustomPresenter) event.launch(new NullPresenter(getProvider()), new HashMap<String, String>(), new String[0]);

		assertTrue( presenter.initCalled );
		assertTrue( presenter.preRenderCalled );
		assertTrue( presenter.showViewStrategy.onLoadStartCalled );
		assertTrue( presenter.showViewStrategy.onLoadCompletedCalled );
		
	}
	
	public void testPresenterSecurity3() throws Exception {
		
		getProvider().getSession().login(new UserData("", true));
		getProvider().getSession().getSecurityContext().getUserData().getRoles().add("role");
		
		boolean onlyValidUserSession = true;
		String[] rolesAllowed = new String[]{"role"};
		
		NavEvent event = new NavEvent(onlyValidUserSession, rolesAllowed);
		CustomPresenter presenter = (CustomPresenter) event.launch(new NullPresenter(getProvider()), new HashMap<String, String>(), new String[0]);

		assertTrue( presenter.initCalled );
		assertTrue( presenter.preRenderCalled );
		assertTrue( presenter.showViewStrategy.onLoadStartCalled );
		assertTrue( presenter.showViewStrategy.onLoadCompletedCalled );
		
	}
	
	public void testPresenterSecurity4() throws Exception {
		
		getProvider().getSession().login(new UserData("", true));
		getProvider().getSession().getSecurityContext().getUserData().getRoles().add("role");
		
		String[] rolesAllowed = new String[]{"role"};
		
		NavEvent event = new NavEvent(false, rolesAllowed);
		CustomPresenter presenter = (CustomPresenter) event.launch(new NullPresenter(getProvider()), new HashMap<String, String>(), new String[0]);

		assertTrue( presenter.initCalled );
		assertTrue( presenter.preRenderCalled );
		assertTrue( presenter.showViewStrategy.onLoadStartCalled );
		assertTrue( presenter.showViewStrategy.onLoadCompletedCalled );
		
	}
	
	public void testPresenterSecurity5() throws Exception {
		
		getProvider().getSession().login(new UserData("", false));
		getProvider().getSession().getSecurityContext().getUserData().getRoles().add("role");
		
		boolean onlyValidUserSession = true;
		String[] rolesAllowed = new String[]{"role"};
		
		NavEvent event = new NavEvent(onlyValidUserSession, rolesAllowed);
		CustomPresenter presenter = (CustomPresenter) event.launch(new NullPresenter(getProvider()), new HashMap<String, String>(), new String[0]);

		assertFalse( presenter.initCalled );
		assertFalse( presenter.preRenderCalled );
		assertFalse( presenter.showViewStrategy.onLoadStartCalled );
		assertFalse( presenter.showViewStrategy.onLoadCompletedCalled );
		
	}
	
	public void testPresenterSecurity6() throws Exception {
		
		getProvider().getSession().login(new UserData("", true));
		getProvider().getSession().getSecurityContext().getUserData().getRoles().add("role");
		
		String[] rolesAllowed = new String[]{"role-no"};
		
		NavEvent event = new NavEvent(false, rolesAllowed);
		CustomPresenter presenter = (CustomPresenter) event.launch(new NullPresenter(getProvider()), new HashMap<String, String>(), new String[0]);

		assertFalse( presenter.initCalled );
		assertFalse( presenter.preRenderCalled );
		assertFalse( presenter.showViewStrategy.onLoadStartCalled );
		assertFalse( presenter.showViewStrategy.onLoadCompletedCalled );
		
	}
	
	public void testPresenterSecurity7() throws Exception {
		
		getProvider().getSession().login(new UserData("", true));
		getProvider().getSession().getSecurityContext().getUserData().getRoles().add("role");
		
		String[] rolesAllowed = new String[0];
		
		NavEvent event = new NavEvent(false, rolesAllowed);
		CustomPresenter presenter = (CustomPresenter) event.launch(new NullPresenter(getProvider()), new HashMap<String, String>(), new String[0]);

		assertTrue( presenter.initCalled );
		assertTrue( presenter.preRenderCalled );
		assertTrue( presenter.showViewStrategy.onLoadStartCalled );
		assertTrue( presenter.showViewStrategy.onLoadCompletedCalled );
		
	}
	
	public void testPresenterSecurity8() throws Exception {
		
		getProvider().getSession().login(new UserData("", true));
		getProvider().getSession().getSecurityContext().getUserData().getRoles().add("role");
		
		String[] rolesAllowed = new String[]{"role", "role-no"};
		
		NavEvent event = new NavEvent(false, rolesAllowed);
		CustomPresenter presenter = (CustomPresenter) event.launch(new NullPresenter(getProvider()), new HashMap<String, String>(), new String[0]);

		assertTrue( presenter.initCalled );
		assertTrue( presenter.preRenderCalled );
		assertTrue( presenter.showViewStrategy.onLoadStartCalled );
		assertTrue( presenter.showViewStrategy.onLoadCompletedCalled );
		
	}
	
	class NavEvent extends ANavigationEvent {

		public NavEvent(boolean requireAuthentication, String[] rolesAllowed) {
			super("", requireAuthentication, rolesAllowed);
		}

		@Override
		protected IPresenter exec(Map<String, String> queryStringValues) {
			CustomPresenter presenter = new CustomPresenter(new CustomShowStrategy(), getPresenterDefinition());
			return presenter;
		}
	}
	
	class CustomPresenter extends APresenter<IView> {

		boolean initCalled = false;
		boolean preRenderCalled = false;
		CustomShowStrategy showViewStrategy;

		public CustomPresenter(CustomShowStrategy showViewStrategy, IPresenterDefinition navigationEvent) {
			super(showViewStrategy, navigationEvent);
			this.showViewStrategy = showViewStrategy;
		}

		@Override
		protected void init() {
			initCalled = true;
		}

		@Override
		protected void preRender() {
			preRenderCalled = true;
		}

		@Override
		public void onGlobalEvent(String status) {
		}

		@Override
		protected void onUnloadPresenter() {
		}
	}
	
	
	class CustomShowStrategy implements IShowViewStrategy<IView> {

		private IView view = new NullView();
		boolean onLoadStartCalled = false;
		boolean onLoadCompletedCalled = false;

		@Override
		public IView getView() {
			return view;
		}

		@Override
		public void onLoadStart() {
			onLoadStartCalled = true;
		}

		@Override
		public void onLoadCompleted() {
			onLoadCompletedCalled = true;			
		}

		@Override
		public void setParent(HasWidgets container) {
		}
		
	}
	
	
}
