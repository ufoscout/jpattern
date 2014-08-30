package com.jpattern.spike;

import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.jpattern.gwt.client.BaseTest;

/**
 * 
 * @author Francesco Cina'
 *
 * 12 Apr 2011
 */
public class EscapeTest extends BaseTest {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testEscapeHtml() throws Exception {
		String text = "<script>do bad actions & terrible things</script>";
		String safeText = SafeHtmlUtils.htmlEscape(text);
		String expectedText = "&lt;script&gt;do bad actions &amp; terrible things&lt;/script&gt;";
		System.out.println("Original text : " + text);
		System.out.println("Escaped text  : " + safeText);
		System.out.println("Expected text : " + expectedText);
		assertFalse( safeText.contains("<") );
		assertFalse( safeText.contains(">") );
		assertEquals( expectedText , safeText );
	}

}
