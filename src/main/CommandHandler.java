package main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	public Point setPlateauBound(String input) {
		bounds = new Point(input);
		return bounds;
	}

	public Position getStartPosition() {
		return startPosition;
	}

	private Position setStartPosition(String position) {
		String working = position.trim();
		Point point = new Point(working.substring(0, working.length() - 1));
		checkPositionIsInBounds(point);
		String facing = parseFacingInput(working);
		currentFacing = facing;
		startPosition = new Position(facing, point);
		return startPosition;
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

	public Position executeMovementCommands(String position, String commands) {
		startPosition = setStartPosition(position);
		validateMovementCommands(commands);
		runTheMoves(commands);
		updateFacingForCurrentPosition();
		return currentPosition;

	}

	private void updateFacingForCurrentPosition() {
		currentPosition.facing = currentFacing;
	}

	private void runTheMoves(String commands) {
		for (int index = 0; index < commands.length(); index++) {
			String command = String.valueOf(commands.charAt(index));
			if (isTurn(command)) {
				handleTurns(command);
			} else {
				handleMove();
			}
		}
	}

	private void handleMove() {
		int x = 	currentPosition.getCoordinates().x;
		int y = 		currentPosition.getCoordinates().y;
		switch (currentFacing) {
		case "N":
			y++;
			break;
		case "S":
			y--;
			break;
		case "E":
			x++;
			break;
		case "W":
			x--;
			break;
		}
		if (x < 0 || y < 0 || x > bounds.x || y > bounds.y) {
			throw new IllegalArgumentException("Move would cross plateau bounds.");
		}
		currentPosition.getCoordinates().x = x;
		currentPosition.getCoordinates().y = y;
	}

	private void handleTurns(String turn) {
		String newFacing = "";
		if (turn.equals("L")) {
			newFacing = turnLeft(currentFacing);
		}else{
			newFacing = turnRight(currentFacing);
		}
		currentFacing = newFacing;
	}

	private String turnLeft(String from){
		switch (from) {
		case "N":
			return "W";
		case "E":
			return "N";
		case "S":
			return "E";
		case "W":
			return "S";
		default: throw new IllegalArgumentException("Only NESW allowed: " + from);
		}
	}
	
	private String turnRight(String from){
		switch (from) {
		case "N":
			return "E";
		case "E":
			return "S";
		case "S":
			return "W";
		case "W":
			return "N";
		default: throw new IllegalArgumentException("Only NESW allowed: " + from);
		}
	}
	private boolean isTurn(String command) {
		String toCheck = String.valueOf(command);
		return "LR".contains(toCheck);

	}

	private void validateMovementCommands(String commands) {
		Pattern validCommands = Pattern.compile("[^LRM]+");
		Matcher match = validCommands.matcher(commands);
		if (match.find()) {
			throw new IllegalArgumentException(
					"Illegal movement command detected. Only 'LRM' allowed: "
							+ commands);
		}
	}

}
