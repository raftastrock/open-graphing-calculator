package com.liferay.calculator.graph;

import org.json.JSONString;

public class Coordinate implements JSONString {

	public Coordinate(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	@Override
	public String toJSONString() {
		return String.format("[%f,%f]", x, y);
	}

	public String toString() {
		return String.format("(%f, %f)", x, y);
	}

	private double x;
	private double y;

}