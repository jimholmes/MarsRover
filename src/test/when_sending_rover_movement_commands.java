package test;

import main.CommandHandler;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

public class when_sending_rover_movement_commands {

	CommandHandler command;

	@Before
	public void run_before_each() {
		command = new CommandHandler();
		command.setPlateauBound("3,3");
	}

	@Test
	public void invalid_commands_throw() throws Exception {
		try {
			command.executeMovementCommands("LBRM");
			fail("IllegalArgumentException not thrown!");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage())
					.isEqualTo(
							"Illegal movement command detected. Only 'LRM' allowed. : LBRM");
		}
	}
}
