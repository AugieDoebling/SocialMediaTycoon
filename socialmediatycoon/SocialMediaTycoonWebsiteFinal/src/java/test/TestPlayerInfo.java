package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/*
 * Test class written by Andrew Rose
 */
public class TestPlayerInfo {
	@Test
	public void testPersonInfo() {
		Player testPlayer = new Player();
		testPlayer.setFirstName("Andrew");
		testPlayer.setLastName("Rose");
		testPlayer.setEmail("testemail@gmail.com");
		testPlayer.setPostalAddress("111 Main St.");
		
		assertEquals("Andrew", testPlayer.getFirstName());
		assertEquals("Rose", testPlayer.getLastName());
		assertEquals("testemail@gmail.com", testPlayer.getEmail());
		assertEquals("111 Main St.", testPlayer.getPostalAddress());
	}
	
	@Test
	public void testClear() {
		Player testPlayer = new Player();
		testPlayer.setFirstName("Andrew");
		testPlayer.setLastName("Rose");
		testPlayer.setEmail("testemail@gmail.com");
		testPlayer.setPostalAddress("111 Main St.");
		
		assertNull(testPlayer.getFirstName());
		assertNull(testPlayer.getLastName());
		assertNull(testPlayer.getEmail());
		assertNull(testPlayer.getPostalAddress());
	}
}
