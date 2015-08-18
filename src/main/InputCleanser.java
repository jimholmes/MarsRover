package main;

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

	private String[] splitInput(String input) {
		String[] tokens = input.split(";");
		if (tokens.length != 3) {
			throw new IllegalArgumentException(
					"Input must contain three semi-colon delimited entries: "
							+ input);
		}
		return tokens;
	}

	private String parseMoves(String input) {
		// TODO Auto-generated method stub
		return null;
	}

	private String parsePosition(String input) {
		validateNumberOfPositionTokens(input);
		String coords = validateCoordinateString(input);
		String facing = validateFacingInput(input);
		return coords + " " + facing;
	}

	private void validateNumberOfPositionTokens(String input) {
		String[] tokens = input.split(" ");
		if (tokens.length != 3) {
			throw new IllegalArgumentException(
					"Position requires three items: " + input);
		}
	}

	private String parseBoundary(String input) {
		return validateCoordinateString(input);
	}
	
	private String validateFacingInput(String input) {
		String[] tokens = input.split(" ");
		if (!Position.ALLOWED_FACINGS.contains(tokens[2].toUpperCase())) {
			throw new IllegalArgumentException(
					"Position input must be N,S,E,W: " + tokens[2]);
		}
		return tokens[2].toUpperCase();
	}

	private String validateCoordinateString(String input) {
		String[] tokens = input.split(" ");
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

}
