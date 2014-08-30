package com.jpattern.ioc.xml.typebuilder;

/**
 * 
 * @author Francesco Cina'
 *
 * 19/dic/2009
 */
public class Primitive_byte_Builder extends ATypeBuilder {

	private static final long serialVersionUID = 1L;
	
	public Primitive_byte_Builder() {
		this(new NullTypeBuilder());
	}
	
	public Primitive_byte_Builder(ITypeBuilder aSuccessor) {
		super(aSuccessor);
	}

	protected ResultType result(String typeName, String value) {
		ResultType type = new ResultType();
		if ( PRIMITIVE_BYTE.equals( typeName ) ) {
			try {
				type.setInstance( Byte.valueOf(value) );
			}
			catch (Exception e) {
				type.setInstance( Byte.valueOf("0") );
			}
			type.setClassOfInstance( byte.class );
			type.setValid(true);
			
		}

		return type;
		
	}

}
