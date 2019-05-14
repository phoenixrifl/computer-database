package main.java.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.service.ComputerService;

/**
 * Servlet implementation class DeleteComputer
 */
@WebServlet(urlPatterns = "/deleteComputer")
public class DeleteComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ComputerService computerService = ComputerService.getInstance();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteComputer() {
        super();
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String selection= request.getParameter("selection");
		String []spliteurSelection = selection.split(",");
		
		for(int i=0; i< spliteurSelection.length; i++) {
			computerService.delete(Integer.parseInt(spliteurSelection[i]));
		}
		
		response.sendRedirect("dashboard");
	}

}
