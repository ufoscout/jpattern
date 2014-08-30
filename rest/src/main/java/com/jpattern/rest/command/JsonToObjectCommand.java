package com.jpattern.rest.command;

import org.codehaus.jackson.map.ObjectMapper;

import com.jpattern.shared.command.IBaseCommand;
import com.jpattern.shared.command.IBaseCommandResult;
import com.jpattern.shared.command.NullBaseCommand;
import com.jpattern.shared.result.ErrorMessage;
import com.jpattern.shared.util.GenericWrapper;

/**
 * 
 * @author Francesco Cina'
 *
 * 11/mag/2011
 */
public class JsonToObjectCommand<T> extends ARestCommand {

	private static final long serialVersionUID = 1L;
	private static ObjectMapper MAPPER = new ObjectMapper();
	private final Class<T> aClass;
	private final GenericWrapper<T> resultWrapper;
	private final StringBuffer jsonString;

	public JsonToObjectCommand(Class<T> aClass, GenericWrapper<T> resultWrapper, StringBuffer jsonString) {
		this(aClass, resultWrapper, jsonString, new NullBaseCommand());
	}
	
	public JsonToObjectCommand(Class<T> aClass, GenericWrapper<T> resultWrapper, StringBuffer jsonString, IBaseCommand previousCommand) {
		super(previousCommand);
		this.aClass = aClass;
		this.resultWrapper = resultWrapper;
		this.jsonString = jsonString;
	}

	@Override
	protected void result(IBaseCommandResult result) {
		try {
			resultWrapper.setValue( MAPPER.readValue(jsonString.toString(), aClass) );			
		} catch (Exception e) {
			result.addErrorMessage(new ErrorMessage(getClass().getName(), e.getMessage()));
			getLogger().error("result", "", e);
		}
	}

	@Override
	protected void internalRollBack(IBaseCommandResult result) {
	}

}
