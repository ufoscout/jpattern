package com.jpattern.gwt.client.view;

import java.util.List;

import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.jpattern.gwt.client.ui.UlListPanel;
import com.jpattern.shared.result.IErrorMessage;

/**
 * Define an IErrorArea which shows the errors added in a UIList
 * @author cinafr
 *
 */
public class DefaultUlNotificationArea implements INotificationArea {

	private final Widget onLoadWidget;
	private final UlListPanel listPanel;
	private final Panel errorPanel;

	/**
	 * 
	 * @param errorPanel the area where errors are notified
	 * @param onLoadWidget a widget to show while and operation is running
	 */
	public DefaultUlNotificationArea(Panel errorPanel, Widget onLoadWidget) {
		this.onLoadWidget = onLoadWidget;
		errorPanel.setVisible(false);
		this.errorPanel = errorPanel;
		this.listPanel = new UlListPanel();
		errorPanel.add(listPanel);
		errorPanel.add(onLoadWidget);
		onLoadWidget.setVisible(false);
	}
	
	@Override
	public void addErrorMessage(IErrorMessage errorMessage) {
		errorPanel.setVisible(true);
		listPanel.add(new InlineLabel(errorMessage.getName() + " " + errorMessage.getMessage()));
	}

	@Override
	public void addErrorMessages(List<IErrorMessage> errorMessages) {
		for (IErrorMessage errorMessage : errorMessages) {
			addErrorMessage(errorMessage);
		}
	}

	@Override
	public void clear() {
		listPanel.clear();
		onLoadWidget.setVisible(false);
		errorPanel.setVisible(false);
	}

	@Override
	public void operationStart() {
		errorPanel.setVisible(true);
		onLoadWidget.setVisible(true);
	}

	@Override
	public void operationFinished() {
		errorPanel.setVisible(false);
		onLoadWidget.setVisible(false);
	}

}
