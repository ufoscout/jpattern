package com.jpattern.core.util;

import java.util.Calendar;
import java.util.Date;

import com.jpattern.core.util.DateUtil;
import com.jpattern.core.BaseTest;

/**
 * 
 * @author Francesco Cina'
 *
 * 27/feb/2011
 */
public class DateUtilTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testDate1() throws Exception {
		String stringDate = "24/11/1997";
		Date date = DateUtil.toDate(stringDate);
		System.out.println("date is: " + date);
		System.out.println("year is: " + DateUtil.getYear(date));
		System.out.println("month is: " + DateUtil.getMonth(date));
		System.out.println("day is: " + DateUtil.getDay(date));
		assertEquals( 24 , DateUtil.getDay(date) );
		assertEquals( 10 , DateUtil.getMonth(date) );
		assertEquals( 1997 , DateUtil.getYear(date) );
		
	}

	public void testDate2() throws Exception {
		String stringDate = "24/11/1997 17:31:35.234";
		String formatDate = "dd/MM/yyyy HH:mm:ss.SSS";
		Date date = DateUtil.toDate(stringDate, formatDate);
		System.out.println("date : " + date);
		System.out.println("year : " + DateUtil.getYear(date));
		System.out.println("month : " + DateUtil.getMonth(date));
		System.out.println("day : " + DateUtil.getDay(date));
		System.out.println("day of week : " + DateUtil.getDayOfWeek(date));
		System.out.println("hour : " + DateUtil.getHour(date));
		System.out.println("minutes : " + DateUtil.getMinutes(date));
		System.out.println("seconds : " + DateUtil.getSeconds(date));
		System.out.println("milliseconds : " + DateUtil.getMilliseconds(date));
		assertEquals( 24 , DateUtil.getDay(date) );
		assertEquals( 10 , DateUtil.getMonth(date) );
		assertEquals( 1997 , DateUtil.getYear(date) );
		assertEquals( 17 , DateUtil.getHour(date) );
		assertEquals( 31 , DateUtil.getMinutes(date) );
		assertEquals( 35 , DateUtil.getSeconds(date) );
		assertEquals( 234 , DateUtil.getMilliseconds(date) );
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		assertEquals(calendar.get(Calendar.DAY_OF_WEEK),DateUtil.getDayOfWeek(date)); 
		
	}
	
	public void testDateConversion() throws Exception {
		String stringDate = "24/11/1997 17:31:35.234";
		String formatDate = "dd/MM/yyyy HH:mm:ss.SSS";
		Date date = DateUtil.toDate(stringDate, formatDate);
		String convertedDate = DateUtil.toString(date, formatDate);
		assertEquals( stringDate, convertedDate);
	}
}
