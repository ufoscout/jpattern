package com.jpattern.ioc.xml.typebuilder;

/**
 * 
 * @author Francesco Cina'
 *
 * 19/dic/2009
 */
public class Primitive_float_Builder extends ATypeBuilder {

	private static final long serialVersionUID = 1L;
	
	public Primitive_float_Builder() {
		this(new NullTypeBuilder());
	}
	
	public Primitive_float_Builder(ITypeBuilder aSuccessor) {
		super(aSuccessor);
	}

	protected ResultType result(String typeName, String value) {
		ResultType type = new ResultType();
		if ( PRIMITIVE_FLOAT.equals( typeName ) ) {
			try {
				type.setInstance( Float.valueOf(value) );
			}
			catch (Exception e) {
				type.setInstance( Float.valueOf("0") );
			}
			type.setClassOfInstance( float.class );
			type.setValid(true);
			
		}

		return type;
		
	}

}
