package com.liferay.calculator.graph;

import com.liferay.calculator.Factory;

import com.udojava.evalex.Expression;

import java.math.BigDecimal;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FunctionPlotter {

	public FunctionPlotter(String function) {
		this.expression = new Expression(function);
		this.function = function;

		GraphDataManager gdm =
			(GraphDataManager)
				Factory.DataManagerInstance.GRAPH.getDataManager();

		this.xstep =
			(gdm.getXmax() - gdm.getXmin()) * gdm.getXres() /
				(double)SCALE_FACTOR;
	}

	public DisplayCoordinate[] getDisplayCoordinates() {
		Coordinate[] coordinates = plot();

		DisplayCoordinate[] dcs = new DisplayCoordinate[coordinates.length];

		for (int i = 0; i < coordinates.length; i++) {
			Coordinate pixel = GraphPrinter.pointToPixel(coordinates[i]);

			dcs[i] = new DisplayCoordinate(
				coordinates[i].getX(), coordinates[i].getY(), (int)pixel.getX(),
				(int)pixel.getY());
		}

		return dcs;
	}

	public String getFunction() {
		return function;
	}

	public Coordinate[] plot() {
		if (this.coordinates != null) {
			return this.coordinates;
		}

		List<Coordinate> coordinates = new ArrayList<Coordinate>();

		GraphDataManager gdm =
			(GraphDataManager)
				Factory.DataManagerInstance.GRAPH.getDataManager();

		for (double x = gdm.getXmin(); x < gdm.getXmax(); x += xstep) {
			coordinates.add(plotAt(x));
		}

		this.coordinates = coordinates.toArray(
			new Coordinate[coordinates.size()]);

		return this.coordinates;
	}

	public Coordinate plotAt(double x) {
		BigDecimal y = expression.with("X", doubleToString(x)).eval();

		return new Coordinate(x, y.doubleValue());
	}

	private String doubleToString(double d) {
		DecimalFormat df = new DecimalFormat(
			"0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));

		df.setMaximumFractionDigits(340);

		return df.format(d);
	}

	private static final int SCALE_FACTOR = 94;

	private Coordinate[] coordinates = null;
	private Expression expression;
	private String function;
	private double xstep;

}