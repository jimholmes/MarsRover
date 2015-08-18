package main;

public class Point {

	 private int x;
	 private int y;
	
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
		checkInputStringLength(working);
		int[] coords = convertInputStringIntoArray(working);
		x = coords[0];
		y = coords[1];
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	
	
	private int[] convertInputStringIntoArray(String input) {
		String[] tokens = input.split(" ");
		int x = Integer.parseInt(tokens[0]);
		int y = Integer.parseInt(tokens[1]);
		int[] out = new int[2];
		out[0] = x;
		out[1] = y;

		return out;
	}
	

	private void checkInputStringLength(String input) {
		String[] tokens = input.split(" ");
		if (tokens.length < 2) {
			throw new IllegalArgumentException(
					"Too few input coordinates. Only got one: " + input);
		}
		if (tokens.length > 2) {
			throw new IllegalArgumentException("Too many input coordinates: "
					+ input);
		}
	}

}
