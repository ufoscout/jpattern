package com.jpattern.ioc.xml.typebuilder;

/**
 * 
 * @author Francesco Cina'
 *
 * 19/dic/2009
 */
public class NullTypeBuilder implements ITypeBuilder {

	private static final long serialVersionUID = 1L;

	public ResultType exec(String typeName, String value) {
		ResultType type = new ResultType();
		return type;
	}
	


}
