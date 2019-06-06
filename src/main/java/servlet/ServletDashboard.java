package servlet;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;

import dto.ComputerDTO;
import exception.SqlCommandeException;
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

			pagination.setOrderby(orderbycolumn);

		}

		List<ComputerDTO> computerDTO_list = null;
		long nbTotalComputers = 0;
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

	@PostMapping(value = { "/", "/dashboard" })
	public RedirectView doPost(@RequestParam(value = "selection", required = false) String selection, Model model) {
		String[] spliteur = selection.split(",");
		for (int i = 0; i < spliteur.length; i++) {
			computerService.delete(Long.parseLong(spliteur[i]));
		}
		return new RedirectView("dashboard");

	}
}
