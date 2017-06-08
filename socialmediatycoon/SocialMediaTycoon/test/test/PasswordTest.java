package test;

import junit.framework.TestCase;
import org.junit.Test;
import smt.Login;

/*
 * Chandler Warne
 */
public class PasswordTest extends TestCase {
	@Test
	
	/*
	 * Test to make sure passwords are set and retreived properly
	 */
	public void testPassword() {
		Login login = new Login();
		
		String startingPass = "Password1";
		login.setAdminPassword(startingPass);
		String endingPass = login.getAdminPassword();
		
		assertEquals(startingPass, endingPass);
	}
}
