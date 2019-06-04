package ui_controller;

import java.util.List;

import CompanyService;
import ComputerService;
import dto.CompanyDTO;
import dto.ComputerDTO;
import exception.SqlCommandeException;
import servlet.model.Pagination;

public class Controller {

	private CompanyService companyService;
	private ComputerService computerService;
	private Pagination pagination;

	public Controller(CompanyService companyService, ComputerService computerService, Pagination pagination) {
		this.companyService = companyService;
		this.computerService = computerService;
		this.pagination = pagination;
	}

	public void action() throws SqlCommandeException {
		boolean play = true;
		while (play) {
			int choix = Ui.demande();
			switch (choix) {
			case 1:
				pagination(1);
				break;
			case 2:
				pagination(2);
				break;
			case 3:
				long id = Ui.demandeId();
				Ui.afficher(computerService.findOne(id));
				break;
			case 4:
				String computer = Ui.demandeCreate();
				computerService.createDTO(computer);
				break;
			case 5:
				id = Ui.demandeId();
				computer = Ui.demandeCreate();
				computerService.createDTOWithId(id, computer);
				break;
			case 6:
				long idDelete = Ui.demandeDelete();
				computerService.delete(idDelete);
				break;
			case 7:
				int company = Ui.demandeIdCompany();
				companyService.delete(company);
				break;
			case 8:
				play = false;
				break;

			}
		}
	}

	private void pagination(int computer_or_company) throws SqlCommandeException {
		boolean next_page = false;
		int n = 0;
		while (!next_page) {
			if (computer_or_company == 1) {
				List<ComputerDTO> computers = computerService.findAll(this.pagination);
				computers.forEach(System.out::println);
			} else if (computer_or_company == 2) {
				List<CompanyDTO> companies = companyService.findAll(this.pagination);
				companies.forEach(System.out::println);
			}
			int choix = Ui.choixPage();
			switch (choix) {
			case 1:
				if (n < 10)
					break;
				else {
					n -= 10;
					break;
				}
			case 2:
				n += 10;
				break;
			case 3:
				next_page = true;
				break;
			}
		}
	}
}
