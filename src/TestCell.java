import static org.junit.Assert.*;
import java.awt.Font;
import java.awt.event.ActionListener;

import org.junit.Test;

public class TestCell {
	private Cell cell;

	// test Font, the font we get should be "Courier", Font.PLAIN, 12
	@Test
	public void testFont() {
		cell = new Cell();
		// get cell font
		Font font = cell.getFont();
		// get the font as we expected
		Font fontExp = new Font("Courier", Font.PLAIN, 12);
		assertEquals(fontExp, font);
	}

	// The ActionListener should be set as CellButtonListener
	@Test
	public void testListener() {
		cell = new Cell();
		// get cell listener
		ActionListener listener = cell.getActionListeners()[0];
		String listenerString = listener.toString();
		assertTrue(listenerString.contains("CellButtonListener"));
	}

	// test the value the cell should return if it is dead
	@Test
	public void testToString() {
		cell = new Cell();
		cell.setAlive(false);
		assertTrue(cell.toString().equals("."));
	}

	// test the value the cell should return if it is alive
	@Test
	public void testToString2() {
		cell = new Cell();
		cell.setAlive(true);
		assertTrue(cell.toString().equals("X"));
	}
}
