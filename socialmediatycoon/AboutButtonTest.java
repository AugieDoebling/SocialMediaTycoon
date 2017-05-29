package socialmediatycoon; 

import static org.junit.Assert.*;

import org.junit.Test;

public class AboutButtonTest {

	AboutButton button = new AboutButton();
	
	@Test
	public void testColor() {
		assertEquals("#00796B", button.color);
		assertEquals("About", button.text);
	}

}
