package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import springConfig.Config;

@WebServlet(urlPatterns = "/dashboard")
public class ServletDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(ServletDashboard.class);
	private Pagination pagination;
	protected final ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(Config.class);


	@Override
	public void init() throws ServletException {
		WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
    	pagination = applicationContext.getBean(Pagination.class);


	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	//	pagination = new Pagination();
		try {
			RequestDispatcher requestDispatcher = pagination.doPage(request)
					.getRequestDispatcher("/WEB-INF/views/dashboard.jsp");
			requestDispatcher.forward(request, response);

		} catch (Exception e) {
			logger.info(e.getMessage());
		}

	}

}
