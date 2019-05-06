package main.java.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.dto.ComputerDTO;
import main.java.service.ComputerService;

/**
 * Servlet implementation class Servlet
 */

@WebServlet(urlPatterns = "/dashboard")
public class ServletDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ComputerService computerService = ComputerService.getInstance();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDashboard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page, pagination;
		int limits, pageCourante, offset;
		if(request.getParameter("page")==null) {
			page = "1";
		}else {
			page = request.getParameter("page");
		}
		
		
		if(request.getParameter("limit")==null) {
			pagination = "10";
		}else {
			pagination = request.getParameter("limit");
		}
	
		
		limits = Integer.valueOf(pagination);
		pageCourante = Integer.parseInt(page);
		if(pageCourante <= 1) {
			pageCourante = 1;
			page = "1";
		}
		if(limits < 1) {
			limits = 1;
			pagination = "1";
		}
		offset = limits * (pageCourante-1);
		
		ArrayList<ComputerDTO> computerDTO_list = computerService.findAll(limits, offset);
		int nbTotal = computerService.count();
		int nbTotalPage = nbTotal / limits;
		if(nbTotal % limits != 0)
			nbTotalPage++;
		
		int begin = 0, end =0;
		if(pageCourante <= 3) {
			begin = 1;
			end = 5;
		}
		else if(pageCourante >= nbTotalPage-3) {
			begin = nbTotalPage-6;
			end = nbTotalPage;
		}
		else {
			begin = pageCourante-3;
			end = pageCourante-3;
		}
		if(pageCourante >= nbTotalPage) {
			pageCourante = nbTotalPage-1;
			page = String.valueOf(pageCourante);
		}
		
		request.setAttribute("computers", computerDTO_list);
		request.setAttribute("taille", nbTotal);
		request.setAttribute("begin", begin);
		request.setAttribute("end", end);
		request.setAttribute("page", page);
		request.setAttribute("limit", limits);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
