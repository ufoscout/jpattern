package com.jpattern.util;


import java.math.BigDecimal;

/**
 * 
 * @author Francesco Cina'
 *
 * 20/lug/2010
 */
public abstract class NumberUtil {

	/**
	 * Effettua il parsing di una String in un tipo BigDecimal. L'utilizzo del BigDecimal evita i
	 * tipici errori di arrotondamento dovuti all'utilizzo del tipo double.
	 * Il separatore decimale della stringa in ingresso puo' essere indipendentemente "." oppure ","
	 * @param value la stringa da analizzare
	 * @param decimalPositions le posizioni decimali desiderate
	 * @return la stringa convertita in BigDecimal
	 */
	public static BigDecimal toBigDecimal(String value, int decimalPositions) throws RuntimeException {
		// il separatore deve essere il '.', sostituisco una eventuale ','
		BigDecimal numero = new BigDecimal(value.replace(',', '.') );
		numero = numero.setScale(decimalPositions, BigDecimal.ROUND_HALF_UP);
		return numero;
	}

	/**
	 * Effettua il parsing di una String in un tipo BigDecimal. L'utilizzo del BigDecimal evita i
	 * tipici errori di arrotondamento dovuti all'utilizzo del tipo double.
	 * Il separatore decimale della stringa in ingresso puo' essere indipendentemente "." oppure ",".
	 * Se il parsing non riesce viene restituito il defaultValue;
	 * @param value la stringa da analizzare
	 * @param decimalPositions le posizioni decimali desiderate
	 * @return la stringa convertita in BigDecimal o il defaultValue
	 */
	public static BigDecimal toBigDecimal(String value, int decimalPositions, BigDecimal defaultValue) {
		try {
			return toBigDecimal(value, decimalPositions);
		} catch (final RuntimeException e) {
			return defaultValue;
		}
	}

}
