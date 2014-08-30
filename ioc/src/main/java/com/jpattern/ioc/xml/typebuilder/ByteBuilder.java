package com.jpattern.ioc.xml.typebuilder;

/**
 * 
 * @author Francesco Cina'
 *
 * 19/dic/2009
 */
public class ByteBuilder extends ATypeBuilder {

	private static final long serialVersionUID = 1L;
	
	public ByteBuilder() {
		this(new NullTypeBuilder());
	}
	
	public ByteBuilder(ITypeBuilder aSuccessor) {
		super(aSuccessor);
	}

	protected ResultType result(String typeName, String value) {
		ResultType type = new ResultType();
		if ( BYTE.equals( typeName ) ) {
			try {
				type.setInstance( Byte.valueOf(value) );
			}
			catch (Exception e) {
				type.setInstance( Byte.valueOf("0") );
			}
			type.setClassOfInstance( Byte.class );
			type.setValid(true);
			
		}

		return type;
		
	}

}
