package socialmediatycoon; 

import static org.junit.Assert.*;

import org.junit.Test;

public class TopBarTest {

	TopBar bar = new TopBar();
	
	@Test
	public void test() {
		assertEquals("Main Menu", bar.text);
		assertEquals("#00796B", bar.color);
		
		assertTrue(bar.exit.isVisible());
	}

}
