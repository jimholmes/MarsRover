package main;

public class Position {
	static final String ALLOWED_FACINGS = "NSEW";
	String facing;
	Point coords;
	
	public Position(String facing, Point coords) {
		this.facing = facing;
		this.coords = coords;
	}
	
	public String getFacing() {
		return facing;
	}
	
	public Point getCoordinates() {
		return coords;
	}

}
