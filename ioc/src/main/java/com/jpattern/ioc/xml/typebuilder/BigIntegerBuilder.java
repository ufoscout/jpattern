package com.jpattern.ioc.xml.typebuilder;

import java.math.BigInteger;

/**
 * 
 * @author Francesco Cina'
 *
 * 19/dic/2009
 */
public class BigIntegerBuilder extends ATypeBuilder {

	private static final long serialVersionUID = 1L;
	
	public BigIntegerBuilder() {
		this(new NullTypeBuilder());
	}
	
	public BigIntegerBuilder(ITypeBuilder aSuccessor) {
		super(aSuccessor);
	}

	protected ResultType result(String typeName, String value) {
		ResultType type = new ResultType();
		if ( BIGINTEGER.equals( typeName ) ) {
			try {
				type.setInstance( new BigInteger(value) );
			}
			catch (Exception e) {
				type.setInstance( new BigInteger("0") );
			}
			type.setClassOfInstance( BigInteger.class );
			type.setValid(true);
			
		}

		return type;
		
	}

}
