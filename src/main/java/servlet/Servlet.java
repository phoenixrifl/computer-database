package servlet;

import javax.servlet.http.HttpServlet;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dto.Mappeur;
import service.CompanyService;
import service.ComputerService;
import springConfig.Config;
import validator.ComputerValidator;

public abstract class Servlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3462195182632968022L;
	protected final ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
	protected final ComputerService computerService = context.getBean(ComputerService.class);
	protected final CompanyService companyService = context.getBean(CompanyService.class);
	protected final ComputerValidator computerValidator = context.getBean(ComputerValidator.class);
	protected final Mappeur mappeur = context.getBean(Mappeur.class);
}
