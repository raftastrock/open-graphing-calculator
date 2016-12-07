package com.liferay.calculator.graph;

import com.liferay.calculator.View;

import java.util.Set;
import java.util.TreeSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GraphView implements View {

	@Override
	public JSONObject getData() throws JSONException {
		JSONObject graphData = new JSONObject();

		JSONArray functionsJSONArray = new JSONArray();

		GraphPrinter gp = new GraphPrinter();

		graphData.put("functions", gp.getFunctionData());

		Coordinate[] pixels = gp.getPixels();

		graphData.put("pixels", pixels);

		return graphData;
	}

	private String function;
	private int xmax = 10;
	private int xmin = -10;
	private int xres = 1;
	private int xscl = 1;
	private int ymax = 10;
	private int ymin = 10;
	private int yscl = 1;

}