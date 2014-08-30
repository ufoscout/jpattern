package com.jpattern.ioc.xml.typebuilder;

/**
 * 
 * @author Francesco Cina'
 *
 * 19/dic/2009
 */
public class IntegerBuilder extends ATypeBuilder {

	private static final long serialVersionUID = 1L;
	
	public IntegerBuilder() {
		this(new NullTypeBuilder());
	}
	
	public IntegerBuilder(ITypeBuilder aSuccessor) {
		super(aSuccessor);
	}

	protected ResultType result(String typeName, String value) {
		ResultType type = new ResultType();
		if ( INTEGER.equals( typeName ) ) {
			try {
				type.setInstance( Integer.valueOf(value) );
			}
			catch (Exception e) {
				type.setInstance( Integer.valueOf(0) );
			}
			type.setClassOfInstance( Integer.class );
			type.setValid(true);
			
		}

		return type;
		
	}

}
