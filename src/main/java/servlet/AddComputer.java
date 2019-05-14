package main.java.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.dto.CompanyDTO;
import main.java.dto.ComputerDTO;
import main.java.service.CompanyService;
import main.java.service.ComputerService;

/**
 * Servlet implementation class AddComputer
 */

@WebServlet(urlPatterns = "/addComputer")
public class AddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ComputerService computerService = ComputerService.getInstance();
	private CompanyService companyService = CompanyService.getInstance();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComputer() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<CompanyDTO> companyDTO_list = companyService.findAll();
		request.setAttribute("listCompany", companyDTO_list);
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/addComputer.jsp");
		rd.forward(request, response);	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = "", dateIntroduced = "", dateDiscontinued = "", idCompanie = "";
		if(request.getParameter("computerName")!=null)
			
			 name = request.getParameter("computerName");
			
		
		if(request.getParameter("introduced")!=null && request.getParameter("introduced")!="")
			dateIntroduced = request.getParameter("introduced");
		
		if(request.getParameter("discontinued")!=null && request.getParameter("discontinued")!="")
			dateDiscontinued = request.getParameter("discontinued");
		
		if(request.getParameter("companyId")!=null)
			idCompanie = request.getParameter("companyId");
		
		
		ArrayList<CompanyDTO> companyDTO_list = companyService.findAll();
		request.setAttribute("listCompany", companyDTO_list);
		ComputerDTO computerDTO = new ComputerDTO(name, dateIntroduced, dateDiscontinued, idCompanie);
		computerService.create(computerDTO);
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/addComputer.jsp");
		rd.forward(request, response);
				
	}

}
