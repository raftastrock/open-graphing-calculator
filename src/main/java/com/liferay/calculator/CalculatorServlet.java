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
		catch (JSONException je) {
			je.printStackTrace();

			out.print(je.toString());
		}
		catch (SyntaxErrorException see) {
			see.printStackTrace();

			out.print(see.getMessage());
		}
	}

}