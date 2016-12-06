package com.liferay.calculator;

import com.liferay.calculator.ExpressionRecord;

import java.util.LinkedList;

public class ExpressionRecords {

	public ExpressionRecords() {
		_expressionRecords = new LinkedList<ExpressionRecord>(); 
	}

	public void add(String expression, String value) {
		ExpressionRecord expressionRecord = new ExpressionRecord(expression, value);

		_expressionRecords.add(expressionRecord);

		if (_expressionRecords.size() > 20) {
			_expressionRecords.removeFirst();
		}
	}

	public ExpressionRecord getLatest() {
		return _expressionRecords.peekLast();
	}

	private LinkedList<ExpressionRecord> _expressionRecords;

	private final int MAX_SIZE = 20;

}