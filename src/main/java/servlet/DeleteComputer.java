package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import service.ComputerService;
import springConfig.Config;

/**
 * Servlet implementation class DeleteComputer
 */
@WebServlet(urlPatterns = "/deleteComputer")
public class DeleteComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ComputerService computerService;
	protected final ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteComputer() {
        super();
    }
    
    
    @Override
    public void init() throws ServletException{
    	WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
    	computerService = applicationContext.getBean(ComputerService.class);

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
