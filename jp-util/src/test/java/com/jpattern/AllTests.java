package com.jpattern;

import junit.framework.TestCase;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.jpattern.util.CollectionUtilTest;
import com.jpattern.util.DateUtilTest;
import com.jpattern.util.FileUtilTest;
import com.jpattern.util.QueryStringUtilTest;
import com.jpattern.util.NumberUtilTest;
import com.jpattern.util.ResourceUtilTest;
import com.jpattern.util.SizeOfUtilTest;
import com.jpattern.util.StringUtilTest;
import com.jpattern.util.UniqueIdTest;
import com.jpattern.util.GenericUtilTest;
import com.jpattern.util.ZipUtilTest;

/**
 * 
 * @author Francesco Cina'
 *
 * 08/feb/2010
 */
@RunWith(Suite.class)
//@formatter:off
@SuiteClasses({
	CollectionUtilTest.class,
	DateUtilTest.class,
	StringUtilTest.class,
	UniqueIdTest.class,
	NumberUtilTest.class,
	ResourceUtilTest.class,
	GenericUtilTest.class,
	ZipUtilTest.class,
	SizeOfUtilTest.class,
	QueryStringUtilTest.class,
	FileUtilTest.class
})
public class AllTests extends TestCase
{
	//do nothing
}
