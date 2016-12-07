package com.liferay.calculator;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

public class CalculatorServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		response.setContentType("application/json; charset=UTF-8");

		PrintWriter out = response.getWriter();

		String viewLabel = request.getParameter("view");

		if ((viewLabel == null) || viewLabel.isEmpty()) {
			viewLabel = "repl";
		}

		DataManager dm = Factory.getDataManager(viewLabel);

		try {
			dm.update(request);

			View view = Factory.getView(viewLabel);

			out.print(view.getData());
		}
		catch (JSONException|RuntimeException e) {
			e.printStackTrace();

			out.print("Something went wrong...");
		}
		catch (SyntaxErrorException see) {
			see.printStackTrace();

			out.print("Syntax Error");
		}
		finally {
			out.flush();
		}
	}

}