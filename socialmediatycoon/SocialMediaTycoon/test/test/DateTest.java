package test;

import static org.junit.Assert.*;
import org.junit.Test;
import smt.Login;
import java.util.Date;

/*
 * Chandler Warne
 */
public class DateTest {
	@Test
	
	/*
	 * Test with a date that is not within one day of eachother
	 */
	public void testDate() {
		long june8 = 1496944418;
		long june5 = 1496620800;
		Date date1 = new Date(june5);
		Date date2 = new Date(june8); 
		Login login = new Login();
		
		assertFalse(login.dateDifference(date1,  date2));
	}
}
