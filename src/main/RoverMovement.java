package main;

public class RoverMovement {

	Point bounds;
	Position currentPosition;
	String currentFacing = "N";
	
	public RoverMovement(Position currentPosition, Point bounds) {
		this.bounds = bounds;
		this.currentPosition = currentPosition;
	}

	public void execute(String commands) {
		for (int index = 0; index < commands.length(); index++) {
			String command = String.valueOf(commands.charAt(index));
			if (isTurn(command)) {
				handleTurns(command);
			} else {
				handleMove();
			}
		}
		updateFacingForCurrentPosition();
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
	private void updateFacingForCurrentPosition() {
		currentPosition.facing = currentFacing;
	}

}
