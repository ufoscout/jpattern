package com.jpattern.ioc.xml.typebuilder;

/**
 * 
 * @author Francesco Cina'
 *
 * 19/dic/2009
 */
public class StringBuilder extends ATypeBuilder {

	private static final long serialVersionUID = 1L;

	public StringBuilder() {
		this(new NullTypeBuilder());
	}
	
	public StringBuilder(ITypeBuilder aSuccessor) {
		super(aSuccessor);
	}

	protected ResultType result(String typeName, String value) {
		ResultType type = new ResultType();
		type.setInstance(value);
		type.setClassOfInstance(value.getClass());
		type.setValid(true);
		return type;
		
	}


}
