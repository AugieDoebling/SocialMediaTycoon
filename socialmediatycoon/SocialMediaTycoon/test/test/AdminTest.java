package test;
import junit.framework.TestCase;
import smt.Admin;

import org.junit.Test;

public class AdminTest  extends TestCase {
	@Test
	public void testPassword()
	{
		Admin admin  = new Admin();
		admin.setAdminPassword("password");
		String expected = "password";
		assertEquals(expected, admin.getAdminPassword());
	}
	
	@Test 
	public void testFirstName()
	{
		Admin admin = new Admin();
		admin.setFirstName("Joe");
		String expected = "Joe";
		assertEquals(expected, admin.getFirstName());
	}
}
