package main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputCleanser {

	String boundary = "";
	String position = "";
	String moves = "";

	public InputCleanser(String input) {
		String[] tokens = splitInput(input);
		boundary = parseBoundary(tokens[0]);
		position = parsePosition(tokens[1]);
		moves = parseMoves(tokens[2]);
	}
	
	public Position execute() {
		CommandHandler handler = new CommandHandler();
		handler.setPlateauBound(boundary);
		return handler.executeMovementCommands(position, moves);
	}

	private String[] splitInput(String input) {
		String[] tokens = input.split(";");
		if (tokens.length != 3) {
			throw new IllegalArgumentException(
					"Input must contain three semi-colon delimited entries: "
							+ input);
		}
		return tokens;
	}

	private String parseMoves(String commands) {
		return validateMovementCommands(commands);
	}

	private String parsePosition(String position) {
		validateNumberOfPositionTokens(position);
		String coords = validateCoordinateString(position);
		String facing = validateFacingInput(position);
		return coords + " " + facing;
	}
	
	private String parseBoundary(String bounds) {
		return validateCoordinateString(bounds);
	}
	

	private void validateNumberOfPositionTokens(String position) {
		String[] tokens = position.split(" ");
		if (tokens.length != 3) {
			throw new IllegalArgumentException(
					"Position requires three items: " + position);
		}
	}

	private String validateFacingInput(String position) {
		String[] tokens = position.split(" ");
		if (!Position.ALLOWED_FACINGS.contains(tokens[2].toUpperCase())) {
			throw new IllegalArgumentException(
					"Position input must be N,S,E,W: " + tokens[2]);
		}
		return tokens[2].toUpperCase();
	}

	private String validateCoordinateString(String coords) {
		String[] tokens = coords.split(" ");
		String xString = tokens[0];
		String yString = tokens[1];
		int x = Integer.parseInt(xString);
		int y = Integer.parseInt(yString);
		if (x < 0 || y < 0) {
			throw new IllegalArgumentException(
					"Coordinates must be greater than zero: " + x + " " + y);
		}
		return xString + " " + yString;
	}
	
	private String validateMovementCommands(String commands) {
		Pattern validCommands = Pattern.compile("[^LRM]+");
		Matcher match = validCommands.matcher(commands);
		if (match.find()) {
			throw new IllegalArgumentException(
					"Illegal movement command detected. Only 'LRM' allowed: "
							+ commands);
		}
		return commands;
	}

	
}
