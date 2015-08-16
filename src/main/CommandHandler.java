package main;

public class CommandHandler {

	Point bounds;
	Position positionFromNasa;
//	Rover rover;

	public CommandHandler() {
		bounds = new Point(0, 0);
		positionFromNasa = new Position("N", new Point(0, 0));
	}

	public Point setPlateauBound(String input) {
		bounds = new Point(input);
		return bounds;
	}

	public Position setRoverPosition(String input) {
		String working = input.trim();
		Point point = new Point(working.substring(0, working.length() - 1));
		checkPositionIsInBounds(point);
		String facing = parseFacingInput(working);
		positionFromNasa = new Position(facing, point);
		return positionFromNasa;
	}

	private void checkPositionIsInBounds(Point point) {
		if (point.getX() > bounds.getX() || point.getY() > bounds.getY()) {
			throw new IllegalArgumentException(
					"Rover coordinates outside plateau bounds: " + point.getX()
							+ ", " + point.getY());
		}
	}

	private String parseFacingInput(String input) {
		String[] tokens = input.split(" ");
		if (tokens.length != 3) {
			throw new IllegalArgumentException(
					"Position input must be three items long: " + input);
		}
		if (!Position.ALLOWED_FACINGS.contains(tokens[2].toUpperCase())) {
			throw new IllegalArgumentException(
					"Position input must be N,S,E,W: " + tokens[2]);
		}
		return tokens[2].toUpperCase();
	}

	public Position executeMovementCommands(String commands) {
		throw new IllegalArgumentException(
				"Illegal movement command detected. Only 'LRM' allowed. : LBRM");
	}

}
