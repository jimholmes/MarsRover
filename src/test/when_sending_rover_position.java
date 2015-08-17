package test;


import main.CommandHandler;
import main.Point;
import main.Position;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class when_sending_rover_position {

	CommandHandler command;
	String good_position = "0 0 N";
	String good_command = "M";

	@Before
	public void run_before_each() {
		command = new CommandHandler();
		command.setPlateauBound("3,3");
	}
	
	@Test
	public void valid_rover_commands_accepted() throws Exception {
		command.executeMovementCommands("1 2 N", good_command);
		Position pos = command.getStartPosition();
		assertThat(pos.getFacing()).isEqualTo("N");
		Point coords = pos.getCoordinates();
		assertThat(coords.getX()).isEqualTo(1);
		assertThat(coords.getY()).isEqualTo(2);
	}
	
	@Test
	public void invalid_facing_throws() throws Exception {
		try {
			command.executeMovementCommands("1 2 F", good_command);
			fail("IllegalArgumentException not thrown");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage()).isEqualTo(
					"Position input must be N,S,E,W: F");
		}
	}

	@Test
	public void negative_position_throws() throws Exception {
		try {
			command.executeMovementCommands("-1 -3 N", good_command);
			fail("IllegalArgumentException not thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage()).isEqualTo(
					"Coordinates must be greater than zero: -1, -3");
		}
	}

	@Test
	public void setting_rover_outside_plateau_throws() throws Exception {
		try {
			command.executeMovementCommands("4 4 N", good_command);
			fail("IllegalArgumentException not thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage()).isEqualTo(
					"Rover coordinates outside plateau bounds: 4, 4");
		}
	}
}
