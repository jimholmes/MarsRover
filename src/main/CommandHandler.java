package main;


public class CommandHandler {

	Point bounds;
	Position startPosition;
	String currentFacing;
	Position currentPosition;

	public CommandHandler() {
		bounds = new Point(0, 0);
		startPosition = new Position("N", new Point(0, 0));
		currentPosition = new Position("N", new Point(0, 0));
		currentFacing = "N";
	}

	protected Point setPlateauBound(String input) {
		bounds = new Point(input);
		return bounds;
	}
	
	protected Position executeMovementCommands(String position, String commands) {
		startPosition = setStartPosition(position);
		RoverMovement rover = new RoverMovement(currentPosition, bounds);
		rover.execute(commands);
		return currentPosition;

	}

	private Position setStartPosition(String position) {
		Point point = getPointFromPositionString(position);
		checkPositionIsInBounds(point);
		currentFacing = parseFacingInput(position.trim());
		startPosition = new Position(currentFacing, point);
		return startPosition;
	}

	private Point getPointFromPositionString(String position) {
		String working = position.trim();
		return new Point(working.substring(0, working.length() - 1));
	}

	private void checkPositionIsInBounds(Point point) {
		if (point.getX() > bounds.getX() || point.getY() > bounds.getY()) {
			throw new IllegalArgumentException(
					"Rover coordinates outside plateau bounds: " + point.getX()
							+ " " + point.getY());
		}
	}

	private String parseFacingInput(String input) {
		String[] tokens = input.split(" ");
		return tokens[2].toUpperCase();
	}

	

}
