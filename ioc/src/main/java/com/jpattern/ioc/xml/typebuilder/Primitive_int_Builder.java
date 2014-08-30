package com.jpattern.ioc.xml.typebuilder;

/**
 * 
 * @author Francesco Cina'
 *
 * 19/dic/2009
 */
public class Primitive_int_Builder extends ATypeBuilder {

	private static final long serialVersionUID = 1L;
	
	public Primitive_int_Builder() {
		this(new NullTypeBuilder());
	}
	
	public Primitive_int_Builder(ITypeBuilder aSuccessor) {
		super(aSuccessor);
	}

	protected ResultType result(String typeName, String value) {
		ResultType type = new ResultType();
		if ( PRIMITIVE_INT.equals( typeName ) ) {
			try {
				type.setInstance( Integer.valueOf(value) );
			}
			catch (Exception e) {
				type.setInstance( Integer.valueOf(0) );
			}
			type.setClassOfInstance( int.class );
			type.setValid(true);
			
		}

		return type;
		
	}

}
