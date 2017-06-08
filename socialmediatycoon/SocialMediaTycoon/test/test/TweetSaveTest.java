package test;

import junit.framework.TestCase;
import org.junit.Test;

import smt.Tweet;
/*
 * Test class written by Michael Murdock
 */
public class TweetSaveTest  extends TestCase{
	private static final String TEXTWORKS = "text works";
	private static final String DESTINATIONWORKS = "destination works";

	@Test
	public void testInfo(){
		Tweet testTweet = new Tweet();
		testTweet.setId(5);
		testTweet.setText(TEXTWORKS);
		testTweet.setDestination(DESTINATIONWORKS);
		testTweet.setScore(6);
		
		assertEquals(5, testTweet.getId().intValue());
		assertEquals(TEXTWORKS, testTweet.getText());
		assertEquals(DESTINATIONWORKS, testTweet.getDestination());
		assertEquals(6, testTweet.getScore());
	}
	
	public void testClear() {
		Tweet testTweet = new Tweet();
		testTweet.setText(TEXTWORKS);
		testTweet.setDestination(DESTINATIONWORKS);
		testTweet.clear();
		
		assertNull(testTweet.getText());
		assertNull(testTweet.getDestination());
	}
}
