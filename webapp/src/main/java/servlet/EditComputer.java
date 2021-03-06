package servlet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import dto.CompanyDTO;
import dto.ComputerDTO;
import exception.SqlCommandeException;
import service.CompanyService;
import service.ComputerService;
import validator.ComputerValidator;

@Controller
public class EditComputer extends AbstractController {
	private CompanyService companyService;
	private ComputerService computerService;
	private ComputerValidator computerValidator;

	public EditComputer(ComputerService computerService, CompanyService companyService,
			ComputerValidator computerValidator) {
		this.companyService = companyService;
		this.computerService = computerService;
		this.computerValidator = computerValidator;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = { "editComputer" })
	public String doGet(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "computerName", required = false) String computerName,
			@RequestParam(value = "introduced", required = false) String introduced,
			@RequestParam(value = "discontinued", required = false) String discontinued,
			@RequestParam(value = "companyId", required = false) String companyId, Model model)
			throws SqlCommandeException {

		List<CompanyDTO> companyDTO_list = new ArrayList<CompanyDTO>();
		this.companyService.findAll().stream()
		.map(x -> this.mappeur.ModelToDTOCompany(x))
		.forEach(companyDTO_list::add);
		
		

		model.addAttribute("id", id);
		model.addAttribute("computerName", computerName);
		model.addAttribute("introduced", introduced);
		model.addAttribute("discontinued", discontinued);
		model.addAttribute("companyId", companyId);
		model.addAttribute("listCompany", companyDTO_list);

		return "editComputer";
	}

	@PostMapping(value = { "editComputer" })
	public RedirectView doPost(@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "computerName", required = false) String computerName,
			@RequestParam(value = "introduced", required = false) String introduced,
			@RequestParam(value = "discontinued", required = false) String discontinued,
			@RequestParam(value = "companyId", required = false) Long companyId, Model model) {
		ComputerDTO computerDTO = new ComputerDTO(id, computerName, introduced, discontinued, companyId);
		if (computerValidator.isAComputerValid(computerDTO)) {
			this.computerService.update(this.mappeur.DTOToModel(computerDTO));
			model.addAttribute("reussite", "Update reussi");
		} else {
			model.addAttribute("echec", "Echec Update");

		}
		return new RedirectView("dashboard");

	}

}
