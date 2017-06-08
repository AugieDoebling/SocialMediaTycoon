package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/*
 * Test class written by Andrew Rose
 */
public class TestPlayerLogin {
	@Test
	public void testPlayerLogin() {
		Player testPlayer = new Player();
		testPlayer.setPlayerLogin("username");
		String expected = "username";
		
		assertEquals(expected, testPlayer.getPlayerLogin());
	}
	
	@Test
	public void testPlayerPassword() {
		Player testPlayer = new Player();
		testPlayer.setPlayerOldPassword("password");
		testPlayer.setPlayerPassword("password1");
		String expectedOldPassword = "password";
		String expectedPassword = "password1";
		
		assertEquals(expectedOldPassword, testPlayer.getPlayerOldPassword());
		assertEquals(expectedPassword, testPlayer.getPlayerPassword());
	}
}
