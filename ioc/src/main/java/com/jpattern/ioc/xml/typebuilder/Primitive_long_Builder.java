package com.jpattern.ioc.xml.typebuilder;

/**
 * 
 * @author Francesco Cina'
 *
 * 19/dic/2009
 */
public class Primitive_long_Builder extends ATypeBuilder {

	private static final long serialVersionUID = 1L;
	
	public Primitive_long_Builder() {
		this(new NullTypeBuilder());
	}
	
	public Primitive_long_Builder(ITypeBuilder aSuccessor) {
		super(aSuccessor);
	}

	protected ResultType result(String typeName, String value) {
		ResultType type = new ResultType();
		if ( PRIMITIVE_LONG.equals( typeName ) ) {
			try {
				type.setInstance( Long.valueOf(value) );
			}
			catch (Exception e) {
				type.setInstance( Long.valueOf("0") );
			}
			type.setClassOfInstance( long.class );
			type.setValid(true);
			
		}

		return type;
		
	}

}
