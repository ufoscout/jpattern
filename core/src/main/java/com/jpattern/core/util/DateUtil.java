package com.jpattern.core.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


/**
 * 
 * @author Francesco Cina'
 *
 * 27/feb/2011
 * 
 * Utility class for very simple date formatting.
 */

public abstract class DateUtil {

    /**
     * Trasforma una stringa secondo il formato dd/mm/yyyy in data. In caso di errore di conversione viene lanciata un'eccezione
     * 
     * @param d String
     * @throws Exception
     * @author Romolo Salvi
     */
    public static Date toDate(String d) throws Exception {
    	return toDate(d, "dd/MM/yyyy" );
    }

    /**
     * Trasforma un string in data in base al formato passato
     * 
     * @param d String
     * @param format String
     * @throws Exception
     * @return Date
     * @author Romolo Salvi
     */
    public static Date toDate(String d, String format) throws Exception {
    	return toDate(d, format, Locale.getDefault());
    }

    /**
     * <p>
     * Trasforma una stringa in data; utilizzando il formato corretto e il locale se il formato risulta locale-sensitive.
     * </p>
     * <p>
     * <b>Example:</b> String data = "Fri Dec 01 20:24:35 CET 2006"; String format = "EEE MMM dd HH:mm:ss 'CET' yyyy"; Locale locale = Locale.US;
     * </p>
     * 
     * @param d String
     * @param format String
     * @param locale Locale
     * @throws Exception
     * @return Date
     * @author Romolo Salvi
     */
    public static Date toDate(String d, String format, Locale locale) throws Exception {

        if (d == null || d.trim().length() == 0)
            return null;

        SimpleDateFormat sdf = new SimpleDateFormat(format, locale);
        sdf.setTimeZone(TimeZone.getTimeZone("ECT"));

        Date data = null;

        try {
            sdf.setLenient(false);
            data = sdf.parse(d);
        }
        catch (ParseException pEx) {

            sdf.setLenient(true);
            try {
                data = sdf.parse(d);
            }
            catch (ParseException ppEx) {
                throw new Exception(ppEx);
            }

            String tempStr = sdf.format(data);
            if (!d.equals(tempStr))
                throw new Exception(pEx);
        }

        if (data == null)
            throw new Exception("Formato della data non valido");

        return data;
    }
    
    /**
     * Trasforma una data in stringa secondo il formato dd/mm/yyyy
     * 
     * @param d Date
     * @author Romolo Salvi
     */
    public static String toString(Date d) {
        return toString(d,"dd/MM/yyyy");
    }

    /**
     * Trasforma una data in stringa in base al formato passato
     * 
     * @param d Date
     * @param format String
     * @return String
     * @author Romolo Salvi
     */
    public static String toString(Date d, String format) {
        if (d == null)
            return "";

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getTimeZone("ECT"));

        return sdf.format(d);
    }


    public static Calendar toCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    /**
     * 
     * @param date
     * @return the year of the input date
     */
    public static int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * 
     * @param date
     * @return the month of the input date (0 if January, 1 if February...)
     */
    public static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH);
    }

    /**
     * 
     * @param date
     * @return the day of the month of the input date
     */
    public static int getDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 
     * @param date
     * @return 0 if sunday, 1 if monday ...
     */
    public static int getDayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 
     * @param date
     * @return the hour of the input date
     */
    public static int getHour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 
     * @param date
     * @return the minutes of the input date
     */
    public static int getMinutes(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MINUTE);
    }

    /**
     * 
     * @param date
     * @return the seconds of the input date
     */
    public static int getSeconds(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.SECOND);
    }
    
    /**
     * 
     * @param date
     * @return the milliseconds of the input date
     */
    public static int getMilliseconds(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MILLISECOND);
    }

}
