package com.jpattern.shared.util;

import java.math.BigDecimal;

import com.jpattern.shared.BaseTest;

/**
 * 
 * @author Francesco Cina
 *
 * 18/giu/2011
 */
public class NumberUtilTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testToBigDecimal1() throws Exception {
		int posizioniDecimali = 2;
		
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
	
	public void testToBigDecimal2() throws Exception {
		int posizioniDecimali = 2;
		
		String importoNetto = "4,11";
		System.out.println("Stringa da analizzare = " + importoNetto  );
		BigDecimal d_appoggio = NumberUtil.toBigDecimal(importoNetto, posizioniDecimali, new BigDecimal(0));
		System.out.println("parsing in = " + d_appoggio  );
		int risultato = d_appoggio.multiply( new BigDecimal(100)).intValue();		
		assertEquals(411, risultato);
		
		
		importoNetto = "ciao!!";
		System.out.println("Stringa da analizzare = " + importoNetto  );
		d_appoggio = NumberUtil.toBigDecimal(importoNetto, posizioniDecimali, new BigDecimal(0));
		System.out.println("parsing in = " + d_appoggio  );
		assertEquals(0, d_appoggio.intValue());
	}
}
