package ui_controller;
import java.util.ArrayList;

import dto.CompanyDTO;
import dto.ComputerDTO;
import exception.SqlCommandeException;
import persistence.OrderByColumn;
import persistence.OrderByMode;
import service.CompanyService;
import service.ComputerService;
public class Controller {
	

	private CompanyService companyService;
	private ComputerService computerService;
	
	private static Controller instance = null;
	
	private Controller() {
		this.companyService = CompanyService.getInstance();
		this.computerService = ComputerService.getInstance();
		
	}
	
	public final static Controller getInstance() {
		if(Controller.instance == null) {
			instance = new Controller();
		}
		return instance;
	}
	
	public void action() throws SqlCommandeException {
		boolean play = true;
		while(play) {
			int choix = Ui.demande();
			switch(choix) {
				case 1:
					pagination(1);
					break;
				case 2:
					pagination(2);
					break;
				case 3:
					int id = Ui.demandeId();
					Ui.afficher(computerService.findOne(id));
					break;
				case 4:
					String computer = Ui.demandeCreate();
					computerService.createDTO(computer);
					break;
				case 5:
					id = Ui.demandeId();
					computer = Ui.demandeCreate();
					computerService.createDTOWithId(id,computer);
					break;
				case 6:
					int idDelete = Ui.demandeDelete();
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
	
	private void pagination(int computer_or_company) throws SqlCommandeException{
		boolean next_page = false;
		int n = 0;
		while(!next_page) {
			if(computer_or_company == 1) {
				ArrayList<ComputerDTO> computers = computerService.findAll(10, n, OrderByMode.ASC, OrderByColumn.ID);
				computers.forEach(System.out::println);
			}
			else if(computer_or_company == 2) {
				ArrayList<CompanyDTO> companies = companyService.findAll(10,n);
				companies.forEach(System.out::println);
			}
			int choix = Ui.choixPage();
			switch(choix) {
				case 1:
					if(n<10)
						break;
					else {
						n-=10;
						break;
					}
				case 2:
					n+=10;
					break;
				case 3: 
					next_page = true;
					break;
			}
		}
	}
}
