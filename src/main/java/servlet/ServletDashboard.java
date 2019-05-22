package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.ComputerDTO;
import exception.SqlCommandeException;
import persistence.OrderByColumn;
import persistence.OrderByMode;
import servlet.model.Pagination;

@WebServlet(urlPatterns = "/dashboard")
public class ServletDashboard extends Servlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(ServletDashboard.class);
	private Pagination pagination = new Pagination();


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String search = request.getParameter("search");

		if (request.getParameter("page") == null) {
			this.pagination.setPage(1);
		} else {
			this.pagination.setPage(Integer.parseInt(request.getParameter("page")));
		}

		if (request.getParameter("limit") == null) {
			this.pagination.setLimit(10);
		} else {
			this.pagination.setLimit(Integer.parseInt(request.getParameter("limit")));
			this.pagination.setOffset(pagination.getLimit()*(pagination.getPage()-1));
		}
		
		String orderByStr = request.getParameter("orderbycolumn");

		for(OrderByColumn ob : OrderByColumn.values()) {
			if(ob.toString().equals(orderByStr)) {
				this.pagination.setByColumn(ob);
			}
		}
		
		
		if(request.getParameter("asc")==null || request.getParameter("asc").isEmpty() ||!request.getParameter("asc").equals("DESC")) {
			this.pagination.setByMode(OrderByMode.ASC);

		}
			else {
				this.pagination.setByMode(OrderByMode.DESC);

			}

		ArrayList<ComputerDTO> computerDTO_list = null;
		try {
			if (search == null || search.isEmpty() || search.equals("dashboard")) {
				computerDTO_list = computerService.findAll(this.pagination);
				this.pagination.setNbTotalComputers(computerService.count());
			}
			else {
				computerDTO_list = computerService.find(search, this.pagination);
				this.pagination.setNbTotalComputers(computerService.countSearch(search));

			}
			
		} catch (SqlCommandeException e) {
			logger.error("findAll " + e.getMessage());

		}
		this.pagination.setNbTotalPages(this.pagination.getNbTotalComputers() / this.pagination.getLimit());
		if ((this.pagination.getNbTotalComputers() % this.pagination.getLimit()) != 0)
			this.pagination.setNbTotalPages(this.pagination.getNbTotalPages()+1);

		int begin = 0, end = 0;
		if (this.pagination.getNbTotalPages() <= 5) {
			begin = 1;
			end = this.pagination.getNbTotalPages();
		} else {
			if (this.pagination.getPage() <= 3) {
				begin = 1;
				end = 5;
			} else if (this.pagination.getPage() >= this.pagination.getNbTotalPages() - 3) {
				begin = this.pagination.getNbTotalPages() - 6;
				end = this.pagination.getNbTotalPages();
			} else {
				begin = this.pagination.getPage() - 3;
				end = this.pagination.getPage() + 3;
			}
		}

		if (this.pagination.getPage() > this.pagination.getNbTotalPages()) {
			this.pagination.setPage(this.pagination.getNbTotalPages() - 1);
		}
		request.setAttribute("search", search);
		request.setAttribute("orderbycolumn", this.pagination.getByColumn());
		request.setAttribute("asc", this.pagination.getByMode());
		request.setAttribute("computers", computerDTO_list);
		request.setAttribute("taille", this.pagination.getNbTotalComputers());
		request.setAttribute("begin", begin);
		request.setAttribute("end", end);
		request.setAttribute("page", this.pagination.getPage());
		request.setAttribute("limit", this.pagination.getLimit());
		

		try {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp");
			requestDispatcher.forward(request, response);

		} catch (Exception e) {
			logger.info(e.getMessage());
		}

	}

}
