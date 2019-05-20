package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import dto.CompanyDTO;
import dto.ComputerDTO;
import exception.SqlCommandeException;
import service.CompanyService;
import service.ComputerService;
import validator.ComputerValidator;

/**
 * Servlet implementation class AddComputer
 */

@WebServlet(urlPatterns = "/addComputer")
public class AddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ComputerService computerService;
	private CompanyService companyService;
	private static Logger logger = LoggerFactory.getLogger(AddComputer.class);
	private ComputerValidator computerValidator;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComputer() {
        super();
    }
    
    @Override
    public void init() throws ServletException{
    	WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
    	companyService = applicationContext.getBean(CompanyService.class);
    	computerService = applicationContext.getBean(ComputerService.class);
    	computerValidator = applicationContext.getBean(ComputerValidator.class);

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<CompanyDTO> companyDTO_list =null;
		try {
			companyDTO_list = companyService.findAll();
		} catch (SqlCommandeException e) {
			logger.error("findAll "+e.getMessage());
		}
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
		
		
		ArrayList<CompanyDTO> companyDTO_list=null;
		try {
			companyDTO_list = companyService.findAll();
		} catch (SqlCommandeException e) {
			logger.error("findAll "+e.getMessage());
		}
		request.setAttribute("listCompany", companyDTO_list);
		ComputerDTO computerDTO = new ComputerDTO(name, dateIntroduced, dateDiscontinued, idCompanie);
		if(computerValidator.isAComputerValid(computerDTO)) {
			computerService.create(computerDTO);
			request.setAttribute("reussite", "Insertion reussite");
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/addComputer.jsp");
			rd.forward(request, response);
		}
		else {
			request.setAttribute("echec", "Echec insertion");
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/addComputer.jsp");
			rd.forward(request, response);
		}
		
				
	}

}
