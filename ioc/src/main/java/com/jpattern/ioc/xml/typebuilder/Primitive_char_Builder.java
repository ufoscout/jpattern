package com.jpattern.ioc.xml.typebuilder;

/**
 * 
 * @author Francesco Cina'
 *
 * 19/dic/2009
 */
public class Primitive_char_Builder extends ATypeBuilder {

	private static final long serialVersionUID = 1L;
	
	public Primitive_char_Builder() {
		this(new NullTypeBuilder());
	}
	
	public Primitive_char_Builder(ITypeBuilder aSuccessor) {
		super(aSuccessor);
	}

	protected ResultType result(String typeName, String value) {
		ResultType type = new ResultType();
		if ( PRIMITIVE_CHAR.equals( typeName ) ) {
			try {
				type.setInstance( Character.valueOf( value.charAt(0) ) );
			}
			catch (Exception e) {
				type.setInstance( Character.valueOf('a') );
			}
			type.setClassOfInstance( char.class );
			type.setValid(true);
			
		}

		return type;
		
	}

}
