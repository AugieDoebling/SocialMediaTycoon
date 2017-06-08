package test;

import junit.framework.TestCase;
import org.junit.Test;

import smt.Player;

/*
 * Test class written by Andrew Rose
 */
public class PlayerInfoTest  extends TestCase{
	@Test
	public void testPersonInfo() {
		Player testPlayer = new Player();
		testPlayer.setFirstName("Andrew");
		testPlayer.setLastName("Rose");
		testPlayer.setEmail("testemail@gmail.com");
		testPlayer.setPostalAddress("111 Main St.");
      String expectFirst = "Andrew";
      String expectLast = "Rose";
      String expectEmail = "testemail@gmail.com";
      String expectAddress = "111 Main St.";
		
		assertEquals(expectFirst, testPlayer.getFirstName());
		assertEquals(expectLast, testPlayer.getLastName());
		assertEquals(expectEmail, testPlayer.getEmail());
		assertEquals(expectAddress, testPlayer.getPostalAddress());
	}
	
	@Test
	public void testClear() {
		Player testPlayer = new Player();
		testPlayer.setFirstName("John");
		testPlayer.setLastName("Doe");
		testPlayer.setEmail("testemail2@gmail.com");
		testPlayer.setPostalAddress("112 Main St.");
		testPlayer.clear();
      
		assertNull(testPlayer.getFirstName());
		assertNull(testPlayer.getLastName());
		assertNull(testPlayer.getEmail());
		assertNull(testPlayer.getPostalAddress());
	}
}
