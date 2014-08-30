package com.jpattern.gwt.client.history;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;

/**
 * 
 * @author Francesco Cina
 *
 * 02/ago/2011
 */
public class GWTHistoryEngine implements IHistoryEngine, ValueChangeHandler<String> {

	private IHistoryManager historyManager;

	@Override
	public void init(IHistoryManager historyManager) {
		this.historyManager = historyManager;
		History.fireCurrentHistoryState();
		History.addValueChangeHandler(this);		
	}

	@Override
	public void registerEvent(String token) {
		History.newItem(token);		
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		historyManager.onEvent(event.getValue());
	}

	@Override
	public void updateState() {
		String initToken = History.getToken();
		historyManager.onEvent(initToken);
	}

}
