package com.liferay.calculator;

import com.liferay.calculator.graph.GraphDataManager;
import com.liferay.calculator.graph.GraphView;
import com.liferay.calculator.repl.ReplDataManager;
import com.liferay.calculator.repl.ReplView;

import javax.servlet.http.HttpServletRequest;

public class Factory {

	public static DataManager getDataManager(String label) {
		for (DataManagerInstance dataManagerInstance :
				DataManagerInstance.values()) {

			if (label.equals(dataManagerInstance.getLabel())) {
				return dataManagerInstance.getDataManager();
			}
		}

		throw new IllegalArgumentException(label + " is not valid.");
	}

	public static View getView(String label) {
		for (ViewInstance viewInstance : ViewInstance.values()) {
			if (label.equals(viewInstance.getLabel())) {
				return viewInstance.getView();
			}
		}

		throw new IllegalArgumentException(label + " is not valid.");
	}

	public enum DataManagerInstance {
		REPL("repl", new ReplDataManager()),
		GRAPH("graph", new GraphDataManager());

		private DataManagerInstance(String label, DataManager dataManager) {
			this.label = label;
			this.dataManager = dataManager;
		}

		public DataManager getDataManager() {
			return dataManager;
		}

		public String getLabel() {
			return label;
		}

		private final DataManager dataManager;
		private final String label;
	}

	public enum ViewInstance {
		REPL("repl", new ReplView()),
		GRAPH("graph", new GraphView());

		private ViewInstance(String label, View view) {
			this.label = label;
			this.view = view;
		}

		public View getView() {
			return view;
		}

		public String getLabel() {
			return label;
		}

		private final String label;
		private final View view;
	}

}