package test;
import main.CommandHandler;
import main.Point;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class when_sending_coordinate_commands {

	CommandHandler command = new CommandHandler();

	@Test
	public void valid_plateau_bounds_are_processed() throws Exception {
		Point parsed = command.setPlateauBound("1,2");
		assertThat(parsed.getX()).isEqualTo(1);
		assertThat(parsed.getY()).isEqualTo(2);
	}

	@Test
	public void too_few_coords_input_throws() throws Exception {
		try {
			command.setPlateauBound("1");
			fail("IllegalArgumentException not thrown");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage()).isEqualTo(
					"Too few input coordinates. Only got one: 1");
		}
	}
	
	@Test
	public void too_many_coords_input_throws() throws Exception {
		try {
			command.setPlateauBound("1,2,3");
			fail("IllegalArgumentException not thrown");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage()).isEqualTo(
					"Too many input coordinates: 1 2 3");
		}
	}

	@Test
	public void invalid_plateau_bounds_throws() throws Exception {
		try {
			command.setPlateauBound("-1,-3");
			fail("IllegalArgumentException not thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage()).isEqualTo(
					"Coordinates must be greater than zero: -1, -3");
		}
	}
	
	@Test
	public void char_inputs_throws() throws Exception {
		try {
			command.setPlateauBound("a,Z");
			fail("NumberFormatException not thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage()).isEqualTo(
					"For input string: \"a\"");
		}
	}

}
