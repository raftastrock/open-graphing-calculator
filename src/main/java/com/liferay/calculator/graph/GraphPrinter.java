package com.liferay.calculator.graph;

import com.liferay.calculator.Factory;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GraphPrinter {

	public GraphPrinter() {
		GraphDataManager gdm =
			(GraphDataManager)
				Factory.DataManagerInstance.GRAPH.getDataManager();

		String[] functions = gdm.getFunctions();

		FunctionPlotter[] functionPlotters =
			new FunctionPlotter[functions.length];

		for (int i = 0; i < functions.length; i++) {
			functionPlotters[i] =
				(functions[i] == null) ? null : new FunctionPlotter(
					functions[i]);
		}

		this.functionPlotters = functionPlotters;
	}

	public JSONArray getFunctionData() throws JSONException {
		JSONArray functionDataJSONArray = new JSONArray();

		for (int i = 0; i < functionPlotters.length; i++) {
			JSONObject functionDataJSONObject = new JSONObject();

			functionDataJSONObject.put("y", "y" + (i + 1));

			FunctionPlotter functionPlotter = functionPlotters[i];

			if (functionPlotter == null) {
				continue;
			}

			functionDataJSONObject.put(
				"function", functionPlotter.getFunction());

			functionDataJSONObject.put(
				"trace", functionPlotter.getDisplayCoordinates());

			functionDataJSONArray.put(functionDataJSONObject);
		}

		return functionDataJSONArray;
	}

	public Coordinate[] getPixels() {
		Set<Coordinate> printPixels = new TreeSet<Coordinate>(
			new Comparator<Coordinate>() {

				@Override
				public int compare(Coordinate c1, Coordinate c2) {
					if (c1.getX() < c2.getX()) {
						return -1;
					}
					else if (c1.getX() > c2.getX()) {
						return 1;
					}
					else if (c1.getY() < c2.getY()) {
						return -1;
					}
					else if (c1.getY() > c2.getY()) {
						return 1;
					}
					else {
						return 0;
					}
				}

			});

		for (FunctionPlotter functionPlotter : functionPlotters) {
			if (functionPlotter == null) {
				continue;
			}

			for (Coordinate coordinate : functionPlotter.plot()) {
				printPixels.add(pointToPixel(coordinate));
			}
		}

		GraphDataManager gdm =
			(GraphDataManager)
				Factory.DataManagerInstance.GRAPH.getDataManager();

		Coordinate originPixel = pointToPixel(new Coordinate(0, 0));

		if (originPixel.getX() >= 0) {
			for (int y = 0; y < VERT_PIXELS; y++) {
				printPixels.add(new Coordinate(originPixel.getX(), y));
			}

			int scaleX = (int)originPixel.getX() + 1;

			for (int y = 0; y < VERT_PIXELS; y += gdm.getYscl()) {
				printPixels.add(new Coordinate(scaleX, y));
			}
		}

		if (originPixel.getY() >= 0) {
			for (int x = 0; x < VERT_PIXELS; x++) {
				printPixels.add(new Coordinate(x, originPixel.getY()));
			}

			int scaleY = (int)originPixel.getY() + 1;

			for (int x = 0; x < VERT_PIXELS; x += gdm.getXscl()) {
				printPixels.add(new Coordinate(x, scaleY));
			}
		}

		return printPixels.toArray(new Coordinate[printPixels.size()]);
	}

	protected static Coordinate pointToPixel(Coordinate point) {
		GraphDataManager gdm =
			(GraphDataManager)
				Factory.DataManagerInstance.GRAPH.getDataManager();

		double pixelX =
			(double)HORIZ_PIXELS * (point.getX() - gdm.getXmin()) /
				(gdm.getXmax() - gdm.getXmin());

		double pixelY =
			(double)VERT_PIXELS * (point.getY() - gdm.getYmin()) /
				(gdm.getYmax() - gdm.getYmin());

		return new Coordinate(Math.round(pixelX), Math.round(pixelY));
	}

	protected Coordinate[] pointsToPixels(Coordinate[] points) {
		Coordinate[] pixels = new Coordinate[points.length];

		for (int i = 0; i < points.length; i++) {
			pixels[i] = pointToPixel(points[i]);
		}

		return pixels;
	}

	private static final int HORIZ_PIXELS = 96;
	private static final int VERT_PIXELS = 64;

	private FunctionPlotter[] functionPlotters;

}