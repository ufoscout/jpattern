package com.jpattern.ioc.xml.typebuilder;

/**
 * 
 * @author Francesco Cina'
 *
 * 19/dic/2009
 */
public class Primitive_boolean_Builder extends ATypeBuilder {

	private static final long serialVersionUID = 1L;
	
	public Primitive_boolean_Builder() {
		this(new NullTypeBuilder());
	}
	
	public Primitive_boolean_Builder(ITypeBuilder aSuccessor) {
		super(aSuccessor);
	}

	protected ResultType result(String typeName, String value) {
		ResultType type = new ResultType();
		if ( PRIMITIVE_BOOLEAN.equals( typeName ) ) {
			try {
				type.setInstance( Boolean.valueOf(value) );
			}
			catch (Exception e) {
				type.setInstance( Boolean.valueOf(false) );
			}
			type.setClassOfInstance( boolean.class );
			type.setValid(true);
			
		}

		return type;
		
	}

}
