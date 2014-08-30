package com.jpattern.gwt.client.history;

import com.jpattern.gwt.client.logger.ILogger;
import com.jpattern.gwt.client.logger.ALoggerService;

/**
 * 
 * @author Francesco Cina
 *
 * 02/ago/2011
 */
public class DirectHistoryEngine implements IHistoryEngine {

	private IHistoryManager historyManager;
	private final ILogger logger;
	private String lastToken = "";

	public DirectHistoryEngine(ALoggerService loggerService) {
		this.logger = loggerService.getLogger(getClass());
	}

	@Override
	public void registerEvent(String token) {
		logger.debug("registerEvent", "token: " + token);
		lastToken = token;
		historyManager.onEvent(token);
	}

	@Override
	public void init(IHistoryManager historyManager) {
		logger.debug("init", "method called");
		this.historyManager = historyManager;
	}

	@Override
	public void updateState() {
		logger.debug("updateState", "method called. lastToken: " + lastToken);
		historyManager.onEvent(lastToken);
	}

}
