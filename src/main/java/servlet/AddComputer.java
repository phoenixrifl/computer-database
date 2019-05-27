package servlet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import dto.ComputerDTO;
import exception.SqlCommandeException;
import service.CompanyService;
import service.ComputerService;
import validator.ComputerValidator;

@Controller
public class AddComputer extends Servlet {
	private static final long serialVersionUID = 1L;
	private CompanyService companyService;
	private ComputerService computerService;
	private ComputerValidator computerValidator;

	public AddComputer(ComputerService computerService, CompanyService companyService,
			ComputerValidator computerValidator) {
		this.computerService = computerService;
		this.companyService = companyService;
		this.computerValidator = computerValidator;
	}

	@GetMapping(value = { "/addComputer" })
	public String doGet(Model model) throws SqlCommandeException {
		model.addAttribute("listCompany", companyService.findAll());
		return "addComputer";
	}

	@PostMapping(value = { "/addComputer" })
	public RedirectView doPost(@RequestParam(value = "computerName", required = false) String computerName,
			@RequestParam(value = "introduced", required = false) String introduced,
			@RequestParam(value = "discontinued", required = false) String discontinued,
			@RequestParam(value = "companyId", required = false) String companyId, Model model) {

		ComputerDTO computerDTO = new ComputerDTO(computerName, introduced, discontinued, companyId);
		if (computerValidator.isAComputerValid(computerDTO)) {
			if (computerService.create(computerDTO))
				model.addAttribute("reussite", "Insertion reussite");
			else
				model.addAttribute("echec", "Echec insertion");
		}

		return new RedirectView("dashboard");
	}
}
