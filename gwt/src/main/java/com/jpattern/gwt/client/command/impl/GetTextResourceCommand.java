package com.jpattern.gwt.client.command.impl;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.jpattern.gwt.client.IErrorMessages;
import com.jpattern.gwt.client.command.ACommand;
import com.jpattern.gwt.client.command.ICommandResult;
import com.jpattern.gwt.client.logger.ILogger;
import com.jpattern.shared.result.ErrorMessage;

/**
 * 
 * @author Francesco Cina'
 *
 * 10/mag/2011
 */
public class GetTextResourceCommand extends ACommand {

	private final StringBuffer url;
	private final StringBuffer fileContent;

	public GetTextResourceCommand(StringBuffer url, StringBuffer fileContent) {
		this.url = url;
		this.fileContent = fileContent;
	}

	@Override
	protected void exec(final ICommandResult commandResult) {
		waitAsyncCallback();
		final ILogger logger = getProvider().getLoggerService().getLogger(this.getClass());
		try {
			new RequestBuilder(RequestBuilder.GET, url.toString()).sendRequest("", new RequestCallback() {
				
				@Override
				public void onResponseReceived(Request request, Response response) {
					fileContent.append(response.getText());					
					callback(commandResult);
				}
				
				@Override
				public void onError(Request request, Throwable exception) {
					logger.error("exec", "RequestCallback error for resource " + url.toString(), exception);
					commandResult.getErrorMessages().add(new ErrorMessage(IErrorMessages.GET_FILE_ERROR, "error.getting.file", new String[]{url.toString()}));
					callback(commandResult);
				}
			});
		} catch (RequestException e) {
			logger.error("exec", "Request Resource error for resource: " + url.toString(), e);
			commandResult.getErrorMessages().add(new ErrorMessage(IErrorMessages.GET_FILE_ERROR, "error.getting.file", new String[]{url.toString()}));
			callback(commandResult);
		}
	}

}
