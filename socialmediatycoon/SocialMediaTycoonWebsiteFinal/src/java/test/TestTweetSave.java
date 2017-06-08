package test;

import static org.junit.Assert.*;
import org.junit.Test;
import Tweet;
/*
 * Test class written by Michael Murdock
 */
public class TestTweet {
	@Test
	public void testInfo(){
		Tweet testTweet = new Tweet();
		testTweet.setId(5);
		testTweet.setText("text works");
		testTweet.setDestination("destination works");
		testTweet.setScore(6);
		
		assertEquals(5, testTweet.getId());
		assertEquals("text works", testTweet.getText());
		assertEquals("destination works", testTweet.getDestination());
		assertEquals(6, testTweet.getScore());
	}
	
	public void testClear() {
		Tweet testTweet = new Tweet();
		testTweet.setTest("text works");
		testTweet.setDestination("destination works");
		testTweet.clear();
		
		assertNull(testTweet.getText());
		assertNull(testTweet.getDesitnation());
	}
}
