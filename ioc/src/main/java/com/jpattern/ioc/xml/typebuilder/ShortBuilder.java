package com.jpattern.ioc.xml.typebuilder;

/**
 * 
 * @author Francesco Cina'
 *
 * 19/dic/2009
 */
public class ShortBuilder extends ATypeBuilder {

	private static final long serialVersionUID = 1L;
	
	public ShortBuilder() {
		this(new NullTypeBuilder());
	}
	
	public ShortBuilder(ITypeBuilder aSuccessor) {
		super(aSuccessor);
	}

	protected ResultType result(String typeName, String value) {
		ResultType type = new ResultType();
		if ( SHORT.equals( typeName ) ) {
			try {
				type.setInstance( Short.valueOf(value) );
			}
			catch (Exception e) {
				type.setInstance( Short.valueOf("0") );
			}
			type.setClassOfInstance( Short.class );
			type.setValid(true);
			
		}

		return type;
		
	}

}
