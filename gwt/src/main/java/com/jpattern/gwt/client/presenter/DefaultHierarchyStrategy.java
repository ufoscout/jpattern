package com.jpattern.gwt.client.presenter;

import java.util.List;

/**
 * 
 * @author cinafr
 *
 */
public class DefaultHierarchyStrategy implements IHierarchyStrategy {

	@Override
	public void hierarchy(List<String> hierarchyResult, String name ) {
		hierarchyResult.add(name);
	}

}
