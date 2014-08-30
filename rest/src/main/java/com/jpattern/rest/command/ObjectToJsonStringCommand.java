package com.jpattern.rest.command;

import java.io.StringWriter;

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
public class ObjectToJsonStringCommand extends ARestCommand {

	private static final long serialVersionUID = 1L;
	private static ObjectMapper MAPPER = new ObjectMapper();
	private final Object object;
	private final StringBuffer outJsonString;

	public ObjectToJsonStringCommand(Object object, StringBuffer outJsonString) {
		this(object, outJsonString, new NullBaseCommand());
	}
	
	public ObjectToJsonStringCommand(Object object, StringBuffer outJsonString, IBaseCommand previousCommand) {
		super(previousCommand);
		this.object = object;
		this.outJsonString = outJsonString;
	}

	@Override
	protected void result(IBaseCommandResult result) {
        try {
            StringWriter writer = new StringWriter();
			MAPPER.writeValue(writer, object);
			outJsonString.append(writer.toString());
		} catch (Exception e) {
			result.addErrorMessage(new ErrorMessage(getClass().getName(), e.getMessage()));
			getLogger().error("result", "", e);
		}
 		
	}

	@Override
	protected void internalRollBack(IBaseCommandResult result) {
	}

}
