package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/deleteComputer")
public class DeleteComputer extends Servlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String selection= request.getParameter("selection");
		String []spliteurSelection = selection.split(",");
		
		for(int i=0; i< spliteurSelection.length; i++) {
			computerService.delete(Integer.parseInt(spliteurSelection[i]));
		}
		
		response.sendRedirect("dashboard");
	}

}
