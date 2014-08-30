package com.jpattern.util;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import com.jpattern.BaseTest;
import com.jpattern.util.NumberUtil;

/**
 * 
 * @author Francesco Cina
 *
 * 18/giu/2011
 */
public class NumberUtilTest extends BaseTest {

	@Test
	public void testToBigDecimal1() throws Exception {
		final int posizioniDecimali = 2;

		String importoNetto = "4,11";
		System.out.println("Stringa da analizzare = " + importoNetto  );
		BigDecimal d_appoggio = NumberUtil.toBigDecimal(importoNetto, posizioniDecimali);
		System.out.println("parsing in = " + d_appoggio  );
		int risultato = d_appoggio.multiply( new BigDecimal(100)).intValue();
		assertEquals(411, risultato);

		importoNetto = "0.90";
		System.out.println("Stringa da analizzare = " + importoNetto  );
		d_appoggio = NumberUtil.toBigDecimal(importoNetto, posizioniDecimali);
		System.out.println("parsing in = " + d_appoggio  );
		risultato = d_appoggio.multiply( new BigDecimal(100)).intValue();
		assertEquals(90, risultato);

		importoNetto = "0,89";
		System.out.println("Stringa da analizzare = " + importoNetto  );
		d_appoggio = NumberUtil.toBigDecimal(importoNetto, posizioniDecimali);
		System.out.println("parsing in = " + d_appoggio  );
		risultato = d_appoggio.multiply( new BigDecimal(100)).intValue();
		assertEquals(89, risultato);

		importoNetto = "3,50";
		System.out.println("Stringa da analizzare = " + importoNetto  );
		d_appoggio = NumberUtil.toBigDecimal(importoNetto, posizioniDecimali);
		System.out.println("parsing in = " + d_appoggio  );
		risultato = d_appoggio.multiply( new BigDecimal(100)).intValue();
		assertEquals(350, risultato);
	}

	@Test
	public void testToBigDecimal2() throws Exception {
		final int posizioniDecimali = 2;

		String importoNetto = "4,11";
		System.out.println("Stringa da analizzare = " + importoNetto  );
		BigDecimal d_appoggio = NumberUtil.toBigDecimal(importoNetto, posizioniDecimali, new BigDecimal(0));
		System.out.println("parsing in = " + d_appoggio  );
		final int risultato = d_appoggio.multiply( new BigDecimal(100)).intValue();
		assertEquals(411, risultato);


		importoNetto = "ciao!!";
		System.out.println("Stringa da analizzare = " + importoNetto  );
		d_appoggio = NumberUtil.toBigDecimal(importoNetto, posizioniDecimali, new BigDecimal(0));
		System.out.println("parsing in = " + d_appoggio  );
		assertEquals(0, d_appoggio.intValue());
	}
}
