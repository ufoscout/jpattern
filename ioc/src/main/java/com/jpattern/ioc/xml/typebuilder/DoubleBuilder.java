package com.jpattern.ioc.xml.typebuilder;

/**
 * 
 * @author Francesco Cina'
 *
 * 19/dic/2009
 */
public class DoubleBuilder extends ATypeBuilder {

	private static final long serialVersionUID = 1L;
	
	public DoubleBuilder() {
		this(new NullTypeBuilder());
	}
	
	public DoubleBuilder(ITypeBuilder aSuccessor) {
		super(aSuccessor);
	}

	protected ResultType result(String typeName, String value) {
		ResultType type = new ResultType();
		if ( DOUBLE.equals( typeName ) ) {
			try {
				type.setInstance( Double.valueOf(value) );
			}
			catch (Exception e) {
				type.setInstance( Double.valueOf(0) );
			}
			type.setClassOfInstance( Double.class );
			type.setValid(true);
			
		}

		return type;
		
	}

}
