package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import logic.Login;

/*
 * Chandler Warne
 */
public class TestPassword {
	@Test
	
	/*
	 * Test to make sure passwords are set and retreived properly
	 */
	public void testPassword() {
		Login login = new logic.Login();
		
		String startingPass = "Password1";
		login.setAdminPassword(startingPass);
		String endingPass = login.getAdminPassword();
		
		assertEquals(startingPass, endingPass);
	}
}
