package servlet;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import dto.ComputerDTO;
import exception.SqlCommandeException;
import persistence.OrderByColumn;
import persistence.OrderByMode;
import service.ComputerService;
import servlet.model.Pagination;

@Controller
@SessionAttributes(value = ServletDashboard.PAGINATION, types = { Pagination.class })
public class ServletDashboard extends Servlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static final String PAGINATION = "pagination";
	private ComputerService computerService;

	public ServletDashboard(ComputerService computerService) {
		this.computerService = computerService;
	}

	@ModelAttribute(PAGINATION)
	public Pagination paginationInstance() {
		return new Pagination();
	}

	@GetMapping(value = { "/", "/dashboard" })
	public String getParam(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "orderbycolumn", required = false) String orderbycolumn,
			@RequestParam(value = "asc", required = false) String asc,
			@ModelAttribute(PAGINATION) Pagination pagination, Model model) throws SqlCommandeException {

		if (page == null) {
			pagination.setPage(1);
		} else {
			pagination.setPage(page);

		}
		if (search != null) {
			pagination.setSearch(search);

		}

		if (limit == null) {
			pagination.setLimit(10);

		} else {
			pagination.setLimit(limit);
			pagination.setOffset(pagination.getLimit() * (pagination.getPage() - 1));

		}

		if (orderbycolumn != null) {
			for (OrderByColumn ob : OrderByColumn.values()) {
				if (ob.toString().equals(orderbycolumn)) {
					pagination.setByColumn(ob);

				}
			}
		} else {
			pagination.setByColumn(OrderByColumn.ID);

		}

		if (asc != null) {
			for (OrderByMode ob : OrderByMode.values()) {
				if (ob.toString().equals(orderbycolumn)) {
					pagination.setByMode(ob);
				}
			}
		} else {
			pagination.setByMode(OrderByMode.ASC);
		}

		ArrayList<ComputerDTO> computerDTO_list = null;
		int nbTotalComputers = 0;
		if (search == null || search.isEmpty() || search.equals("dashboard")) {
			computerDTO_list = computerService.findAll(pagination);
			nbTotalComputers = computerService.count();
		} else {
			computerDTO_list = computerService.find(pagination);
			nbTotalComputers = computerService.countSearch(pagination);

		}
		pagination.setNbTotalComputers(nbTotalComputers);
		pagination.pageview();

		model.addAttribute("computers", computerDTO_list);

		return "dashboard";

	}

}
