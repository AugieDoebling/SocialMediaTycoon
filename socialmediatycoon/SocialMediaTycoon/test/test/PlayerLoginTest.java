package test;

import junit.framework.TestCase;
import org.junit.Test;

import smt.Player;

/*
 * Test class written by Andrew Rose
 */
public class PlayerLoginTest  extends TestCase{
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
		testPlayer.setPlayerOldPassword("pass");
		testPlayer.setPlayerPassword("pass1");
		String expectedOldPass = "pass";
		String expectedPass = "pass1";
		
		assertEquals(expectedOldPass, testPlayer.getPlayerOldPassword());
		assertEquals(expectedPass, testPlayer.getPlayerPassword());
	}
}
