package com.jpattern.shared.util;

/**
 * 
 * @author Francesco Cina'
 *
 * 20/lug/2010
 */
public class StringUtil {

	/**
	 * Restituisce la stringa tagliata alla lunghezza length richiesta. Se la stringa e' di lunghezza minore di length viene
	 * restituita la stringa inalterata.
	 * @param text la striga da tagliare
	 * @param length la lunghezza massima della stringa
	 * @return
	 */
	public static String cut(String text, int length) {
		if ( (length>=0) && (text.length()>length)) {
			return text.substring(0, length);
		}
		return text;
	}

	/**
	 * Restituisce un array di tutte le sottostringhe risultanti dallo split di una stringa source
	 * in base al pattern passato
	 * @param source La stringa da splittare
	 * @param pattern il pattern regex in base al quale splittare la stringa source
	 * @return
	 */
	public static String[] tokenize(String source, String pattern) {
		return source.split(pattern); //The "-1" is needed to keep the empty fields
	}

	/**
	 * Restituisce un array di tutte le sottostringhe risultanti dallo split di una stringa source
	 * in base al pattern passato
	 * @param source La stringa da splittare
	 * @param pattern il pattern regex in base al quale splittare la stringa source
	 * @return
	 */
	public static String[] tokenizeKeepEmptyField(String source, String pattern) {
		return source.split(pattern, -1); //The "-1" is needed to keep the empty fields
	}

}
