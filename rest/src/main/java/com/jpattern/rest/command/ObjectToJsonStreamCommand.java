package com.jpattern.rest.command;

import java.io.OutputStream;

import org.codehaus.jackson.map.ObjectMapper;

import com.jpattern.shared.command.IBaseCommand;
import com.jpattern.shared.command.IBaseCommandResult;
import com.jpattern.shared.command.NullBaseCommand;
import com.jpattern.shared.result.ErrorMessage;

/**
 * 
 * @author Francesco Cina'
 *
 * 11/mag/2011
 */
public class ObjectToJsonStreamCommand extends ARestCommand {

	private static final long serialVersionUID = 1L;
	private static ObjectMapper MAPPER = new ObjectMapper();
	private final Object object;
	private final OutputStream outputStream;

	public ObjectToJsonStreamCommand(Object object, OutputStream outputStream) {
		this(object, outputStream, new NullBaseCommand());
	}

	public ObjectToJsonStreamCommand(Object object, OutputStream outputStream, IBaseCommand previousCommand) {
		super(previousCommand);
		this.object = object;
		this.outputStream = outputStream;
	}

	@Override
	protected void result(IBaseCommandResult result) {
        try {
        	MAPPER.writeValue(outputStream, object);
		} catch (Exception e) {
			result.addErrorMessage(new ErrorMessage(getClass().getName(), e.getMessage()));
			getLogger().error("result", "", e);
		}
	}

	@Override
	protected void internalRollBack(IBaseCommandResult result) {
	}

}
