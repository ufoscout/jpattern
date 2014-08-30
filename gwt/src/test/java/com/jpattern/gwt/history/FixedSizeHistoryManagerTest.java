package com.jpattern.gwt.history;

import java.util.HashMap;
import java.util.List;

import com.jpattern.gwt.client.AApplication;
import com.jpattern.gwt.client.BaseTest;
import com.jpattern.gwt.client.history.HistoryManager;
import com.jpattern.gwt.client.history.HistoryService;
import com.jpattern.gwt.client.history.IEventToken;
import com.jpattern.gwt.client.history.IHistoryManagerObserver;
import com.jpattern.gwt.client.history.DirectHistoryEngine;
import com.jpattern.gwt.client.history.NullHistoryManagerObserver;
import com.jpattern.gwt.client.logger.SysoutLoggerService;
import com.jpattern.gwt.client.presenter.NullPresenter;

/**
 * 
 * @author Francesco Cina'
 *
 * 06/ago/2011
 */
public class FixedSizeHistoryManagerTest extends BaseTest {

	private AApplication applicationProvider;
	private HistoryManagerObserver historyManagerObserver = new HistoryManagerObserver();
	
	@Override
	protected void setUp() throws Exception {
		applicationProvider = new AApplication() {
			public void stopSystem() {}
			public void startSystem() {	
				setLoggerService(new SysoutLoggerService());
				setHistoryService(new HistoryService(new DirectHistoryEngine(getLoggerService()), historyManagerObserver,getLoggerService()));
			}
		};
		applicationProvider.startSystem();
	}

	@Override
	protected void tearDown() throws Exception {
	}

	public void testHistoryTokenQueryString1() {
		HistoryManager historyManager = new HistoryManager(new DirectHistoryEngine(new SysoutLoggerService()), new NullHistoryManagerObserver(), new SysoutLoggerService());
		
		String eventName = "historyToken1_historyToken2_?value1=1;value2=2";
		
		IEventToken eventToken = historyManager.generateEventToken(eventName);
		assertEquals("historyToken1_historyToken2_" , eventToken.getToken());
		assertEquals("?value1=1;value2=2" , eventToken.getQueryString());
	}
	
	public void testHistoryTokenQueryString2() {
		HistoryManager historyManager = new HistoryManager(new DirectHistoryEngine(new SysoutLoggerService()), new NullHistoryManagerObserver(), new SysoutLoggerService());
		
		String eventName = "historyToken1_historyToken2_";
		
		IEventToken eventToken = historyManager.generateEventToken(eventName);
		assertEquals("historyToken1_historyToken2_" , eventToken.getToken());
		assertEquals("" , eventToken.getQueryString());
	}
	
	public void testHistoryTokenQueryString3() {
		HistoryManager historyManager = new HistoryManager(new DirectHistoryEngine(new SysoutLoggerService()), new NullHistoryManagerObserver(), new SysoutLoggerService());
		
		String eventName = "?value1=1;value2=2";
		
		IEventToken eventToken = historyManager.generateEventToken(eventName);
		assertEquals("" , eventToken.getToken());
		assertEquals("?value1=1;value2=2" , eventToken.getQueryString());
	}
	
	public void testHistoryTokenQueryString4() {
		HistoryManager historyManager = new HistoryManager(new DirectHistoryEngine(new SysoutLoggerService()), new NullHistoryManagerObserver(), new SysoutLoggerService());
		
		String eventName = "?";
		
		IEventToken eventToken = historyManager.generateEventToken(eventName);
		assertEquals("" , eventToken.getToken());
		assertEquals("?" , eventToken.getQueryString());
	}
	
	public void testHistoryManager() throws Exception {
		
		MockNavigationEvent event1 = new MockNavigationEvent("P1");
		MockNavigationEvent event1_2 = new MockNavigationEvent("P2");
		MockNavigationEvent event1_2_3 = new MockNavigationEvent("P3");
		MockNavigationEvent event1_2_4 = new MockNavigationEvent("P4");
		
		event1.launch(new NullPresenter(applicationProvider), new HashMap<String, String>(), new String[0]);
		event1_2.launch(event1.getPresenter(), new HashMap<String, String>(), new String[0]);
		event1_2_3.notifyNavigationEvent(event1_2.getPresenter(), new HashMap<String, String>(), new String[0], true);
		event1_2_4.notifyNavigationEvent(event1_2.getPresenter(), new HashMap<String, String>(), new String[0], true);
		event1_2_4.launch(event1_2.getPresenter(), new HashMap<String, String>(), new String[0]);
		
//		assertEquals("P2" , event1.getPresenter().viewEvent);
//		assertEquals("P4" , event1_2.getPresenter().viewEvent);
//		assertEquals("" , event1_2_4.getPresenter().viewEvent);
//		assertEquals("" , event1_2_3.getPresenter().viewEvent);

		
		assertEquals(2, historyManagerObserver.tokenListSize );
		
		
	}
	
	class HistoryManagerObserver implements IHistoryManagerObserver {
		
		int tokenListSize = 0;
		

		@Override
		public void onRegisterHistory(String currentToken, List<String> presenterHierarchy) {
			System.out.println("currentToken: " + currentToken);
			System.out.println("history Token List: ");
			for (String presenter : presenterHierarchy) {
				System.out.println("-- name: " + presenter );
			}
			if (!currentToken.isEmpty()) {
				tokenListSize++;
			}
		}


		@Override
		public void onEvent(String eventName) {
		}
		
	}

}
