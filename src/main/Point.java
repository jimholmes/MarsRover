package main;

public class Point {

	 int x;
	 int y;
	
	public Point(int[]coords) {
		x = coords[0];
		y = coords[1];
	}
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(String input) {
		String working = input.replace(",", " ");
		int[] coords = parseCoordsInput(working);
		x = coords[0];
		y = coords[1];
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	private int[] parseCoordsInput(String input) {
		String[] tokens = checkInputStringLength(input);
		return convertInputStringIntoArray(tokens);
	}
	
	private int[] convertInputStringIntoArray(String[] tokens) {
		int x = Integer.parseInt(tokens[0]);
		int y = Integer.parseInt(tokens[1]);
		if (x < 0 || y < 0) {
			throw new IllegalArgumentException(
					"Coordinates must be greater than zero: " + x + ", " + y);
		}
		int[] out = new int[2];
		out[0] = x;
		out[1] = y;

		return out;
	}

	private String[] checkInputStringLength(String input) {
		String[] tokens = input.split(" ");
		if (tokens.length < 2) {
			throw new IllegalArgumentException(
					"Too few input coordinates. Only got one: " + tokens[0]);
		}
		if (tokens.length > 2) {
			String out = "";
			for (String token : tokens) {
				out += " " + token;
			}
			throw new IllegalArgumentException("Too many input coordinates:"
					+ out);
		}
		return tokens;
	}

}
