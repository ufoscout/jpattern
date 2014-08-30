package com.jpattern.gwt.client.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.jpattern.shared.result.IErrorMessage;

/**
 * 
 * @author Francesco Cina'
 *
 */
public class CommandResult implements ICommandResult {

	private static final long serialVersionUID = 1L;
	private List<IErrorMessage> errorMessages = new ArrayList<IErrorMessage>();
	private int responseCode;
	
	@Override
	public final List<IErrorMessage> getErrorMessages() {
		return errorMessages ;
	}

	public final boolean isValid() {
		return getErrorMessages().size()==0;
	}

	public String asString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[ \n");
        buffer.append("{ isValid = " + isValid() + " } \n");
      //  buffer.append("{ returnObject = " + returnedObject() + " } \n");
        buffer.append("{ errormessages = \n");
        for (Iterator<IErrorMessage> iter = errorMessages.iterator(); iter.hasNext();) {
            IErrorMessage msg = iter.next();
            buffer.append("( " + msg.getName() + " : " + msg.getMessage());
            int len = msg.getParameters().size();
            for (int i = 0; i < len; i++) {
                buffer.append(" " + msg.getParameters().get(i) + "  ");
            }
            buffer.append(" )");
        }
        buffer.append(" }\n");
        buffer.append(" ] ");

        return buffer.toString();
	}

	@Override
	public int getResponseCode() {
		return responseCode;
	}

	@Override
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

}
