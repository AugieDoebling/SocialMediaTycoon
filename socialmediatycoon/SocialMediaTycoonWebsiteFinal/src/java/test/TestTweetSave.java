package test;

import static org.junit.Assert.*;
import org.junit.Test;
/*
 * Test class written by Michael Murdock
 */
public class TestTweet {
	private static final String TEXTWORKS = "text works";
	private static final String DESTINATIONWORKS = "destination works";

	@Test
	public void testInfo(){
		Tweet testTweet = new Tweet();
		testTweet.setId(5);
		testTweet.setText(TEXTWORKS);
		testTweet.setDestination(DESTINATIONWORKS);
		testTweet.setScore(6);
		
		assertEquals(5, testTweet.getId());
		assertEquals(TEXTWORKS, testTweet.getText());
		assertEquals(DESTINATIONWORKS, testTweet.getDestination());
		assertEquals(6, testTweet.getScore());
	}
	
	public void testClear() {
		Tweet testTweet = new Tweet();
		testTweet.setTest(TEXTWORKS);
		testTweet.setDestination(DESTINATIONWORKS);
		testTweet.clear();
		
		assertNull(testTweet.getText());
		assertNull(testTweet.getDesitnation());
	}
}
