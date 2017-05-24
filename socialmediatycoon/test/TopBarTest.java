import static org.junit.Assert.*;

import org.junit.Test;

public class TopBarTest {

	TopBar bar = new TopBar();
	
	@Test
	public void test() {
		assertTrue(bar.text.equals("Main Menu"));
		assertTrue(bar.color.equals("#00796B"));
		
		assertTrue(bar.exit.isVisible());
	}

}
