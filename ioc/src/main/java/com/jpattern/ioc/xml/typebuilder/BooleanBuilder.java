package com.jpattern.ioc.xml.typebuilder;

/**
 * 
 * @author Francesco Cina'
 *
 * 19/dic/2009
 */
public class BooleanBuilder extends ATypeBuilder {

	private static final long serialVersionUID = 1L;
	
	public BooleanBuilder() {
		this(new NullTypeBuilder());
	}
	
	public BooleanBuilder(ITypeBuilder aSuccessor) {
		super(aSuccessor);
	}

	protected ResultType result(String typeName, String value) {
		ResultType type = new ResultType();
		if ( BOOLEAN.equals( typeName ) ) {
			try {
				type.setInstance( Boolean.valueOf(value) );
			}
			catch (Exception e) {
				type.setInstance( Boolean.valueOf(false) );
			}
			type.setClassOfInstance( Boolean.class );
			type.setValid(true);
			
		}

		return type;
		
	}

}
