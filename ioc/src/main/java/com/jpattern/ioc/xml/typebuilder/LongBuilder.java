package com.jpattern.ioc.xml.typebuilder;

/**
 * 
 * @author Francesco Cina'
 *
 * 19/dic/2009
 */
public class LongBuilder extends ATypeBuilder {

	private static final long serialVersionUID = 1L;
	
	public LongBuilder() {
		this(new NullTypeBuilder());
	}
	
	public LongBuilder(ITypeBuilder aSuccessor) {
		super(aSuccessor);
	}

	protected ResultType result(String typeName, String value) {
		ResultType type = new ResultType();
		if ( LONG.equals( typeName ) ) {
			try {
				type.setInstance( Long.valueOf(value) );
			}
			catch (Exception e) {
				type.setInstance( Long.valueOf("0") );
			}
			type.setClassOfInstance( Long.class );
			type.setValid(true);
			
		}

		return type;
		
	}

}
