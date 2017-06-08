package test;
import static org.junit.Assert.*;
import Logic.WeightConversions;

import org.junit.Test;

public class TestAdmin {
	@Test
	public void testPassword()
	{
		Admin A = new Admin();
		String actual = A.setAdminPassword("password");
		String expected = "password";
		assertEquals(expected, actual);
	}
	
	@Test 
	public void testFirstName()
	{
		Admin A = new Admin();
		String actual = A.setFirstName("Joe");
		String expected = "Joe";
		assertEquals(expected, actual);
	}
}
