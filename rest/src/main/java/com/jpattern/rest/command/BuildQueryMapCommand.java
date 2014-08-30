package com.jpattern.rest.command;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jpattern.rest.domain.URLPath;
import com.jpattern.shared.command.IBaseCommand;
import com.jpattern.shared.command.IBaseCommandResult;
import com.jpattern.shared.command.NullBaseCommand;
import com.jpattern.shared.result.ErrorMessage;

/**
 * 
 * @author Francesco Cina'
 *
 * 12/mag/2011
 */
public class BuildQueryMapCommand extends ARestCommand {

	private static final long serialVersionUID = 1L;
	private final URLPath urlPath;
	private final Map<String, List<String>> resultQueryMap;

	public BuildQueryMapCommand(URLPath urlPath, Map<String, List<String>> resultQueryMap) {
		this(urlPath, resultQueryMap, new NullBaseCommand());
	}
	
	public BuildQueryMapCommand(URLPath urlPath, Map<String, List<String>> resultQueryMap, IBaseCommand previousCommand) {
		super(previousCommand);
		this.urlPath = urlPath;
		this.resultQueryMap = resultQueryMap;
	}

	@Override
	protected void result(IBaseCommandResult result) {
		if (urlPath.getQueryString().length() > 0) {
			try {
				String query = urlPath.getQueryString();
				for (String param : query.split("&")) {
					String[] pair = param.split("=");
					if (pair.length>0) {
						String key = URLDecoder.decode(pair[0], "UTF-8");
						String value = "";
						if (pair.length>1) { 
							value = URLDecoder.decode(pair[1], "UTF-8");
						}
					
						List<String> values = resultQueryMap.get(key);
						if (values == null) {
							values = new ArrayList<String>();
							resultQueryMap.put(key, values);
						}
						values.add(value);
						resultQueryMap.put(key, values);
					}
				}
			} catch (Exception e) {
				result.addErrorMessage(new ErrorMessage(getClass().getName(), e.getMessage()));
				getLogger().error("result", "", e);
			}
		}
	}

	@Override
	protected void internalRollBack(IBaseCommandResult result) {
	}

}
