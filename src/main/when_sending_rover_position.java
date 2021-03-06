package main;


import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class when_sending_rover_position {

	CommandHandler command;
	String good_boundary = "3 3";
	String good_position = "0 0 N";
	String good_command = "M";

	@Before
	public void run_before_each() {
		command = new CommandHandler();
		command.setPlateauBound("3,3");
	}
	
	@Test
	public void valid_rover_commands_accepted() throws Exception {
		Position pos = command.executeMovementCommands("1 2 N", good_command);
		assertThat(pos.getFacing()).isEqualTo("N");
		Point coords = pos.getCoordinates();
		assertThat(coords.getX()).isEqualTo(0);
		assertThat(coords.getY()).isEqualTo(1);
	}
	

	@Test
	public void setting_rover_outside_plateau_throws() throws Exception {
		try {
			command.executeMovementCommands("4 4 N", good_command);
			fail("IllegalArgumentException not thrown.");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage()).isEqualTo(
					"Rover coordinates outside plateau bounds: 4 4");
		}
	}
}
