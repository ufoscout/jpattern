package com.jpattern.ioc.xml.typebuilder;

/**
 * 
 * @author Francesco Cina'
 *
 * 19/dic/2009
 */
public class Primitive_double_Builder extends ATypeBuilder {

	private static final long serialVersionUID = 1L;
	
	public Primitive_double_Builder() {
		this(new NullTypeBuilder());
	}
	
	public Primitive_double_Builder(ITypeBuilder aSuccessor) {
		super(aSuccessor);
	}

	protected ResultType result(String typeName, String value) {
		ResultType type = new ResultType();
		if ( PRIMITIVE_DOUBLE.equals( typeName ) ) {
			try {
				type.setInstance( Double.valueOf(value) );
			}
			catch (Exception e) {
				type.setInstance( Double.valueOf(0) );
			}
			type.setClassOfInstance( double.class );
			type.setValid(true);
			
		}

		return type;
		
	}

}
