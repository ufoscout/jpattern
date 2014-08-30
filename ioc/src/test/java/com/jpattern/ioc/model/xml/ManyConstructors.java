package com.jpattern.ioc.model.xml;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

/**
 * 
 * @author Francesco Cina'
 *
 * 19/dic/2009
 */

public class ManyConstructors {

	private int qualeCostruttore = 0;
	private String dettagli = "";
	
	public ManyConstructors(){
		dettagli = "costruttore senza parametri";
	}
	
	public ManyConstructors(String arg0){
		qualeCostruttore = 1;
		dettagli = "costruttore con 1 parametro String";
	}
	
	public ManyConstructors(int arg0){
		qualeCostruttore = 2;
		dettagli = "costruttore con 1 parametro int = " + arg0;
	}
	
	public ManyConstructors(Integer arg0){
		qualeCostruttore = 3;
		dettagli = "costruttore con 1 parametro Integer = " + arg0;
	}

	public ManyConstructors(double arg0){
		qualeCostruttore = 4;
		dettagli = "costruttore con 1 parametro double = " + arg0;
	}
	
	public ManyConstructors(int arg0, String arg1, Double arg2){
		qualeCostruttore = 5;
		dettagli = "costruttore con 3 parametri int,String,Double";
	}
	
	public ManyConstructors(String arg1, Map<String,?> arg2){
		qualeCostruttore = 6;
		dettagli = "costruttore con 2 parametri String,Map";
	}
	
	public ManyConstructors(Double arg0){
		qualeCostruttore = 7;
		dettagli = "costruttore con 1 parametro Double = " + arg0;
	}
	
	public ManyConstructors(Byte arg0){
		qualeCostruttore = 8;
		dettagli = "costruttore con 1 parametro Byte = " + arg0;
	}
	
	public ManyConstructors(Short arg0){
		qualeCostruttore = 9;
		dettagli = "costruttore con 1 parametro Short = " + arg0;
	}
	
	public ManyConstructors(Long arg0){
		qualeCostruttore = 10;
		dettagli = "costruttore con 1 parametro Long = " + arg0;
	}
	
	public ManyConstructors(Float arg0){
		qualeCostruttore = 11;
		dettagli = "costruttore con 1 parametro Float = " + arg0;
	}
	
	public ManyConstructors(Boolean arg0){
		qualeCostruttore = 12;
		dettagli = "costruttore con 1 parametro Boolean = " + arg0;
	}
	
	public ManyConstructors(Character arg0){
		qualeCostruttore = 13;
		dettagli = "costruttore con 1 parametro Character = " + arg0;
	}
	
	public ManyConstructors(BigInteger arg0){
		qualeCostruttore = 14;
		dettagli = "costruttore con 1 parametro BigInteger = " + arg0;
	}
	
	public ManyConstructors(BigDecimal arg0){
		qualeCostruttore = 15;
		dettagli = "costruttore con 1 parametro BigDecimal = " + arg0;
	}
	
	public ManyConstructors(StringBuffer arg0){
		qualeCostruttore = 16;
		dettagli = "costruttore con 1 parametro StringBuffer = " + arg0;
	}
	
	public ManyConstructors(StringBuffer stringBuffer, BigInteger bigInteger, Character character, Boolean booleanValue, String stringa){
		qualeCostruttore = 17;
		dettagli = "costruttore con 5 parametri StringBuffer=" + stringBuffer + ", BigInteger=" + bigInteger + ", Character=" + character + ", Boolean=" + booleanValue + ", String=" + stringa;
	}
	
	public ManyConstructors(StringBuffer stringBuffer, int integer, Character character, boolean booleanValue, String stringa){
		qualeCostruttore = 24;
		dettagli = "costruttore con 5 parametri StringBuffer=" + stringBuffer + ", int=" + integer + ", Character=" + character + ", boolean=" + booleanValue + ", String=" + stringa;
	}
	
	public ManyConstructors(StringBuffer stringBuffer, int integer, Character character, boolean booleanValue, String stringa, Object object){
		qualeCostruttore = 25;
		dettagli = "costruttore con 6 parametri StringBuffer=" + stringBuffer + ", int=" + integer + ", Character=" + character + ", boolean=" + booleanValue + ", String=" + stringa + ", Object=" + object.toString();
	}
	
	public ManyConstructors(short arg0){
		qualeCostruttore = 18;
		dettagli = "costruttore con 1 parametro short = " + arg0;
	}
	
	public ManyConstructors(byte arg0){
		qualeCostruttore = 19;
		dettagli = "costruttore con 1 parametro byte = " + arg0;
	}
	
	public ManyConstructors(long arg0){
		qualeCostruttore = 20;
		dettagli = "costruttore con 1 parametro long = " + arg0;
	}
	
	public ManyConstructors(float arg0){
		qualeCostruttore = 21;
		dettagli = "costruttore con 1 parametro float = " + arg0;
	}
	
	public ManyConstructors(boolean arg0){
		qualeCostruttore = 22;
		dettagli = "costruttore con 1 parametro boolean = " + arg0;
	}
	
	public ManyConstructors(char arg0){
		qualeCostruttore = 23;
		dettagli = "costruttore con 1 parametro char = " + arg0;
	}
	
	
	
	
	
	
	
	
	public int getQualeCostruttore() {
		return qualeCostruttore;
	}

	public String getDettagli() {
		return dettagli;
	}
}
