package main;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class when_receiving_input {

	InputCleanser cleaner;
	String validBounds = "3 3";
	String validPosition = "0 0 N";
	String validMove = "M";
	String sep = ";";

	@Test
	public void too_few_input_tokens_throws() throws Exception {
		try {
			cleaner = new InputCleanser("foo;bar");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage()).startsWith(
					"Input must contain three semi-colon delimited entries:");
		}
	}

	@Test
	public void char_in_bounds_throws() throws Exception {
		try {
			cleaner = new InputCleanser("a Z" + sep + validPosition + sep + validMove);
			fail("NumberFormatException not thrown.");
		} catch (NumberFormatException e) {
			assertThat(e.getMessage()).startsWith(
					"For input string");
		}
	}

	@Test
	public void invalid_plateau_bounds_throws() throws Exception {
		try {
			cleaner = new InputCleanser("-1 -3" + sep + validPosition + sep + validMove);
			fail("IllegalArgumentException not thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage())
					.startsWith(
							"Coordinates must be greater than zero:");
		}
	}
	
	@Test
	public void invalid_chars_in_position_coords_throws() throws Exception {
		try {
			cleaner = new InputCleanser(validBounds + sep + "z a N" + sep + validMove);
			fail("NumberFormatException not thrown.");
		} catch (NumberFormatException e) {
			assertThat(e.getMessage()).startsWith(
					"For input string");
		}
	}
	
	@Test
	public void invalid_facing_in_position_throws() throws Exception {
		try {
			cleaner = new InputCleanser(validBounds + sep + "0 0 T" + sep + validMove);
			fail("IllegalArgumentException not thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage()).startsWith(
					"Position input must be N,S,E,W: ");
		}
	}
	
	@Test
	public void too_few_position_tokens_throws() throws Exception {
		try {
			cleaner = new InputCleanser(validBounds + sep + "0 0" + sep + validMove);
			fail("IllegalArgumentException not thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage()).startsWith(
					"Position requires three items: ");
		}
	}
}
