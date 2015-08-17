package test;

import main.CommandHandler;
import main.Position;
import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

public class when_sending_rover_movement_commands {

	CommandHandler command;
	String good_position = "0 0 N";

	@Before
	public void run_before_each() {
		command = new CommandHandler();
		command.setPlateauBound("3,3");
	}

	@Test
	public void invalid_commands_throw() throws Exception {
		try {
			command.executeMovementCommands(good_position, "LBRM");
			fail("IllegalArgumentException not thrown!");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage())
					.isEqualTo(
							"Illegal movement command detected. Only 'LRM' allowed: LBRM");
		}
	}
	
	@Test
	public void move_straight_one_returns_correct_position() throws Exception {
		Position pos = command.executeMovementCommands(good_position, "M");
		assertThat(pos.getCoordinates().getX()).isEqualTo(0);
		assertThat(pos.getCoordinates().getY()).isEqualTo(1);
		assertThat(pos.getFacing()).isEqualTo("N");
	}
	
	@Test
	public void move_straight_two_returns_correct_position() throws Exception {
		Position pos = command.executeMovementCommands(good_position, "MM");
		assertThat(pos.getCoordinates().getX()).isEqualTo(0);
		assertThat(pos.getCoordinates().getY()).isEqualTo(2);
		assertThat(pos.getFacing()).isEqualTo("N");
	}
	
	@Test
	public void move_straight_turn_right_returns_correct_position() throws Exception {
		Position pos = command.executeMovementCommands(good_position, "MR");
		assertThat(pos.getCoordinates().getX()).isEqualTo(0);
		assertThat(pos.getCoordinates().getY()).isEqualTo(1);
		assertThat(pos.getFacing()).isEqualTo("E");
	}
	@Test
	public void turn_right_then_straight_returns_correct_position() throws Exception {
		Position pos = command.executeMovementCommands(good_position, "RM");
		assertThat(pos.getCoordinates().getX()).isEqualTo(1);
		assertThat(pos.getCoordinates().getY()).isEqualTo(0);
		assertThat(pos.getFacing()).isEqualTo("E");
	}
	
	@Test
	public void right_straight_left_straight_returns_correct_position() throws Exception {
		Position pos = command.executeMovementCommands(good_position, "RMLM");
		assertThat(pos.getCoordinates().getX()).isEqualTo(1);
		assertThat(pos.getCoordinates().getY()).isEqualTo(1);
		assertThat(pos.getFacing()).isEqualTo("N");
	}
	
	@Test
	public void cross_w_bounds_throws() throws Exception {
		try {
			Position pos = command.executeMovementCommands(good_position, "LM");
			fail("IllegalArgumentException not thrown");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage()).isEqualTo("Move would cross plateau bounds.");
		}
	}
	
	@Test
	public void cross_n_bounds_throws() throws Exception {
		try {
			Position pos = command.executeMovementCommands(good_position, "MMMM");
			fail("IllegalArgumentException not thrown");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage()).isEqualTo("Move would cross plateau bounds.");
		}
	}
	@Test
	public void cross_e_bounds_throws() throws Exception {
		try {
			Position pos = command.executeMovementCommands(good_position, "RMMMM");
			fail("IllegalArgumentException not thrown");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage()).isEqualTo("Move would cross plateau bounds.");
		}
	}
	@Test
	public void cross_s_bounds_throws() throws Exception {
		try {
			Position pos = command.executeMovementCommands(good_position, "RRM");
			fail("IllegalArgumentException not thrown");
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage()).isEqualTo("Move would cross plateau bounds.");
		}
	}
	
	@Test
	public void goto_to_corner_returns_correct_position() throws Exception {
		Position pos = command.executeMovementCommands(good_position, "MMMRMMM");
		assertThat(pos.getCoordinates().getX()).isEqualTo(3);
		assertThat(pos.getCoordinates().getY()).isEqualTo(3);
		assertThat(pos.getFacing()).isEqualTo("E");
	}
}
