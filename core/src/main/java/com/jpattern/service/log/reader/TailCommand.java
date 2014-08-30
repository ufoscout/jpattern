package com.jpattern.service.log.reader;

import com.jpattern.core.IProvider;
import com.jpattern.core.command.ACommand;
import com.jpattern.core.command.ACommandResult;
import com.jpattern.shared.util.GenericWrapper;


/**
 * 
 * @author Francesco Cina'
 *
 * 6 May 2011
 * 
 * 
 */
public class TailCommand extends ACommand<IProvider> {

    private final GenericWrapper<IQueueMessage> resultMessage;
    private final IFilter _messageFilter;
    private final long _lastReadedMessage;

    public TailCommand(GenericWrapper<IQueueMessage> resultMessage, long lastReadedMessage, IFilter aMessageFilter) {
		this.resultMessage = resultMessage;
        _messageFilter = aMessageFilter;
        _lastReadedMessage = lastReadedMessage;
    }

    @Override
    protected void rollback(ACommandResult result) {
    }

    @Override
    protected void execute(ACommandResult result) {
        IMessageReader messageReader = getProvider().getLoggerReaderService().messageReader(_messageFilter);
        resultMessage.setValue(messageReader.read(_lastReadedMessage));
    }

}
