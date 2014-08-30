package com.jpattern.ioc.xml.typebuilder;

/**
 * 
 * @author Francesco Cina'
 *
 * 19/dic/2009
 */
public class CharacterBuilder extends ATypeBuilder {

	private static final long serialVersionUID = 1L;
	
	public CharacterBuilder() {
		this(new NullTypeBuilder());
	}
	
	public CharacterBuilder(ITypeBuilder aSuccessor) {
		super(aSuccessor);
	}

	protected ResultType result(String typeName, String value) {
		ResultType type = new ResultType();
		if ( CHARACTER.equals( typeName ) ) {
			try {
				type.setInstance( Character.valueOf( value.charAt(0) ) );
			}
			catch (Exception e) {
				type.setInstance( Character.valueOf('a') );
			}
			type.setClassOfInstance( Character.class );
			type.setValid(true);
			
		}

		return type;
		
	}

}
