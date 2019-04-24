package main.java.ui_controller;
import java.util.ArrayList;

import main.java.dto.CompanyDTO;
import main.java.dto.ComputerDTO;
import main.java.exception.NotOneTwoNumber;
import main.java.service.CompanyService;
import main.java.service.ComputerService;
public class Controller {

	CompanyService companyService = new CompanyService();
	ComputerService computerService = new ComputerService();
	
	public void action() throws NotOneTwoNumber {
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
					play = false;
					break;
				
			}
		}
	}
	
	private void pagination(int computer_or_company) throws NotOneTwoNumber {
		boolean next_page = false;
		int n = 1;
		while(!next_page) {
			if(computer_or_company == 1) {
				ArrayList<ComputerDTO> computers = computerService.findAll(10, n);
				computers.forEach(System.out::println);
			}
			else if(computer_or_company == 2) {
				ArrayList<CompanyDTO> companies = companyService.findAll(10,n);
				companies.forEach(System.out::println);
			}
			else throw new NotOneTwoNumber();
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