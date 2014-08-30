package com.jpattern.core.command;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.jpattern.shared.result.IErrorMessage;


/**
 * 
 * @author Francesco Cina'
 *
 * 27/feb/2011
 */
public class CommandResult extends ACommandResult {

	private static final long serialVersionUID = 1L;

    private List<IErrorMessage> errorMessages;

	private boolean waiting = false;
	
	private List<ACommand<?>> commandInExecutionList = new Vector<ACommand<?>>();

    public CommandResult() {
        errorMessages = new Vector<IErrorMessage>();
    }
	
	@Override
    public final synchronized List<IErrorMessage> getErrorMessages() {
    	if ( errorMessages == null ) {
    		errorMessages = new Vector<IErrorMessage>();
    	}
        return errorMessages;
    }

    @Override
	public final synchronized void addErrorMessage(IErrorMessage errorMessage) {
		getErrorMessages().add(errorMessage);		
	}
    
    @Override
    public synchronized boolean isValid() {
        return getErrorMessages().size() == 0;
    }

    @Override
    public synchronized String asString() {

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
	public synchronized boolean isExecutionEnd() {
		return commandInExecutionList.isEmpty();
	}

	@Override
	public synchronized void waitExecutionEnd() throws InterruptedException {
		if (!isExecutionEnd()) {
			waiting = true;
			wait();
		}
	}
	
	@Override
	synchronized void setExecutionEnd(ACommand<?> command) {
		commandInExecutionList.remove(command);
		if (isExecutionEnd() && waiting) {
			notifyAll();
		}
	}

	@Override
	synchronized void setExecutionStart(ACommand<?> command) {
		commandInExecutionList.add(command);
	}
}
