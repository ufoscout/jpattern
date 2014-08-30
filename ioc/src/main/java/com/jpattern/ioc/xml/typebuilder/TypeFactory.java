package com.jpattern.ioc.xml.typebuilder;

/**
 * 
 * @author Francesco Cina'
 *
 * 20/dic/2009
 */
public class TypeFactory {

	private ITypeBuilder typeBuilder;
	
	public ITypeBuilder typeBuilder() {
		if (typeBuilder == null) {
			typeBuilder = createTypeBuilder();
		}
		return typeBuilder;		
	}

	private ITypeBuilder createTypeBuilder() {
		
		ITypeBuilder typeBuilder = new IntegerBuilder();
		typeBuilder = new DoubleBuilder(typeBuilder);
		typeBuilder = new ByteBuilder(typeBuilder);
		typeBuilder = new ShortBuilder(typeBuilder);
		typeBuilder = new LongBuilder(typeBuilder);
		typeBuilder = new FloatBuilder(typeBuilder);
		typeBuilder = new CharacterBuilder(typeBuilder);
		typeBuilder = new BooleanBuilder(typeBuilder);
		typeBuilder = new StringBufferBuilder(typeBuilder);
		typeBuilder = new BigDecimalBuilder(typeBuilder);
		typeBuilder = new BigIntegerBuilder(typeBuilder);
		typeBuilder = new Primitive_int_Builder(typeBuilder);
		typeBuilder = new Primitive_double_Builder(typeBuilder);
		typeBuilder = new Primitive_short_Builder(typeBuilder);
		typeBuilder = new Primitive_byte_Builder(typeBuilder);
		typeBuilder = new Primitive_long_Builder(typeBuilder);
		typeBuilder = new Primitive_float_Builder(typeBuilder);
		typeBuilder = new Primitive_boolean_Builder(typeBuilder);
		typeBuilder = new Primitive_char_Builder(typeBuilder);
		
		// lo StringBuilder DEVE essere l'ultimo della catena poiche' 
		// questo restituisce SEMPRE un risultato valido e non permetterebbe
		// ai TypeBuilder successivi di attivarsi
		typeBuilder = new StringBuilder(typeBuilder);
		
		return typeBuilder;
	}
}
