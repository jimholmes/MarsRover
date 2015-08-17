package test;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class when_receiving_input {
	
	InputCleanser cleaner;
	String validPosition="0 0 N";
	String validMove = "M";

	@Test
	public void char_in_bounds_throws() throws Exception {
		try {
			cleaner = new InputCleanser("a,Z" + validPosition + validMove);
			fail("NumberFormatException not thrown.");
		} catch (NumberFormatException e) {
			assertThat(e.getMessage()).startsWith(
					"Boundary coords must be integers greater than zero: ");
		}
	}
	
	@Test
	public void invalid_plateau_bounds_throws() throws Exception {
		try {
			cleaner = new InputCleanser("-1 -3" + validPosition + validMove);
			fail("IllegalArgumentException not thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage()).startsWith(
					"Boundary coords must be integers greater than zero: -1 -3");
		}
	}
}
