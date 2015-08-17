package test;

public class InputCleanser {

	public InputCleanser(String string) {
		throw new NumberFormatException("Boundary coords must be integers greater than zero: " + string);
	}

}
