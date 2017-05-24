import static org.junit.Assert.*;

import org.junit.Test;

public class AboutButtonTest {

	AboutButton button = new AboutButton();
	
	@Test
	public void testColor() {
		assertTrue(button.color.equals("#00796B"));
		assertTrue(button.text.equals("About"));
	}

}
