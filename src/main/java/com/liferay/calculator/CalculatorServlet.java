package com.liferay.calculator;

import com.udojava.evalex.Expression;

import java.io.IOException;
import java.io.PrintWriter;

import java.math.BigDecimal;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalculatorServlet extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		PrintWriter out = response.getWriter();

		out.println("Hello, world.");

		// sample usage
		/*
		BigDecimal result = null;
		result = new Expression("(3.4 + -4.1)/2").eval();
		System.out.println(result.toString());
		*/
	}

}