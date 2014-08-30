package com.jpattern.ioc.xml.typebuilder;

import java.math.BigDecimal;

/**
 * 
 * @author Francesco Cina'
 *
 * 19/dic/2009
 */
public class BigDecimalBuilder extends ATypeBuilder {

	private static final long serialVersionUID = 1L;
	
	public BigDecimalBuilder() {
		this(new NullTypeBuilder());
	}
	
	public BigDecimalBuilder(ITypeBuilder aSuccessor) {
		super(aSuccessor);
	}

	protected ResultType result(String typeName, String value) {
		ResultType type = new ResultType();
		if ( BIGDECIMAL.equals( typeName ) ) {
			try {
				type.setInstance( new BigDecimal(value) );
			}
			catch (Exception e) {
				type.setInstance( new BigDecimal(0) );
			}
			type.setClassOfInstance( BigDecimal.class );
			type.setValid(true);
			
		}

		return type;
		
	}

}
