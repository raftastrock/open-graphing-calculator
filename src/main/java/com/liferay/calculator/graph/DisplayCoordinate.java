package com.liferay.calculator.graph;

public class DisplayCoordinate extends Coordinate {

	public DisplayCoordinate(double x, double y, int displayX, int displayY) {
		super(x, y);

		this.displayX = displayX;
		this.displayY = displayY;
	}

	public double getDisplayX() {
		return displayX;
	}

	public double getDisplayY() {
		return displayY;
	}

	@Override
	public String toJSONString() {
		return String.format(
			"{\"point\":[%f,%f],\"pixel\":[%d,%d]}",
			getX(), getY(), getDisplayX(), getDisplayY());
	}

	private double displayX;
	private double displayY;

}