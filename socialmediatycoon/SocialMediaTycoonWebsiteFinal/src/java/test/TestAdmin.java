package test;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestAdmin {
	@Test
	public void testPassword()
	{
		Admin admin  = new Admin();
		String actual = admin.setAdminPassword("password");
		String expected = "password";
		assertEquals(expected, actual);
	}
	
	@Test 
	public void testFirstName()
	{
		Admin admin = new Admin();
		String actual = admin.setFirstName("Joe");
		String expected = "Joe";
		assertEquals(expected, actual);
	}
}
