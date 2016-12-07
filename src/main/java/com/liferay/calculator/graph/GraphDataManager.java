package com.liferay.calculator.graph;

import com.liferay.calculator.DataManager;
import com.liferay.calculator.SyntaxErrorException;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

public class GraphDataManager implements DataManager {

	public GraphDataManager() {
		this.functions = new String[10];
	}

	@Override
	public void update(HttpServletRequest request) throws SyntaxErrorException {
		// TODO
	}

	protected String[] getFunctions() {
		return Arrays.copyOf(functions, functions.length);
	}

	protected int getXmax() {
		return xmax;
	}

	protected int getXmin() {
		return xmin;
	}

	protected int getXres() {
		return xres;
	}

	protected int getXscl() {
		return xscl;
	}

	protected int getYmax() {
		return ymax;
	}

	protected int getYmin() {
		return ymin;
	}

	protected int getYscl() {
		return yscl;
	}

	protected void setFunction(int index, String function) {
		this.functions[index] = function;
	}

	protected void setXmax(int xmax) {
		this.xmax = xmax;
	}

	protected void setXmin(int xmin) {
		this.xmin = xmin;
	}

	protected void setXres(int xres) {
		this.xres = xres;
	}

	protected void setXscl(int xscl) {
		this.xscl = xscl;
	}

	protected void setYmax(int ymax) {
		this.ymax = ymax;
	}

	protected void setYmin(int ymin) {
		this.ymin = ymin;
	}

	protected void setYscl(int yscl) {
		this.yscl = yscl;
	}

	private static GraphDataManager _INSTANCE = new GraphDataManager();

	private String[] functions;
	private int xmax = 10;
	private int xmin = -10;
	private int xres = 1;
	private int xscl = 1;
	private int ymax = 10;
	private int ymin = 10;
	private int yscl = 1;

}