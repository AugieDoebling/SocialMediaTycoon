package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import logic.Login;
import java.util.Date;

/*
 * Chandler Warne
 */
public class TestDate {
	@Test
	
	/*
	 * Test with a date that is not within one day of eachother
	 */
	public void testDate() {
		long june8 = 1496944418;
		long june5 = 1496620800;
		Date date1 = new Date(june5);
		Date date2 = new Date(june8); 
		boolean expected = false;
		boolean actual = Login.dateDifference(date1,  date2);
		
		assertEquals(expected, actual);
	}
}
