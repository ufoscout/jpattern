package com.jpattern.ioc.xml.typebuilder;

/**
 * 
 * @author Francesco Cina'
 *
 * 19/dic/2009
 */
public class StringBufferBuilder extends ATypeBuilder {

	private static final long serialVersionUID = 1L;
	
	public StringBufferBuilder() {
		this(new NullTypeBuilder());
	}
	
	public StringBufferBuilder(ITypeBuilder aSuccessor) {
		super(aSuccessor);
	}

	protected ResultType result(String typeName, String value) {
		ResultType type = new ResultType();
		if ( STRINGBUFFER.equals( typeName ) ) {
			try {
				type.setInstance( new StringBuffer(value) );
			}
			catch (Exception e) {
				type.setInstance( new StringBuffer("") );
			}
			type.setClassOfInstance( StringBuffer.class );
			type.setValid(true);
			
		}

		return type;
		
	}

}
