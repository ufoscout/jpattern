package com.jpattern.ioc.xml.typebuilder;

/**
 * 
 * @author Francesco Cina'
 *
 * 19/dic/2009
 */
public class Primitive_short_Builder extends ATypeBuilder {

	private static final long serialVersionUID = 1L;
	
	public Primitive_short_Builder() {
		this(new NullTypeBuilder());
	}
	
	public Primitive_short_Builder(ITypeBuilder aSuccessor) {
		super(aSuccessor);
	}

	protected ResultType result(String typeName, String value) {
		ResultType type = new ResultType();
		if ( PRIMITIVE_SHORT.equals( typeName ) ) {
			try {
				type.setInstance( Short.valueOf(value) );
			}
			catch (Exception e) {
				type.setInstance( Short.valueOf("0") );
			}
			type.setClassOfInstance( short.class );
			type.setValid(true);
			
		}

		return type;
		
	}

}
