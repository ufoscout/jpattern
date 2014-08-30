package com.jpattern.ioc.xml.typebuilder;

/**
 * 
 * @author Francesco Cina'
 *
 * 19/dic/2009
 */
public class FloatBuilder extends ATypeBuilder {

	private static final long serialVersionUID = 1L;
	
	public FloatBuilder() {
		this(new NullTypeBuilder());
	}
	
	public FloatBuilder(ITypeBuilder aSuccessor) {
		super(aSuccessor);
	}

	protected ResultType result(String typeName, String value) {
		ResultType type = new ResultType();
		if ( FLOAT.equals( typeName ) ) {
			try {
				type.setInstance( Float.valueOf(value) );
			}
			catch (Exception e) {
				type.setInstance( Float.valueOf("0") );
			}
			type.setClassOfInstance( Float.class );
			type.setValid(true);
			
		}

		return type;
		
	}

}
