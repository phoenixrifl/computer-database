package ui_controller;
import service.CompanyService;
import service.ComputerService;
public class Controller {

	CompanyService companyService = new CompanyService();
	ComputerService computerService = new ComputerService();
	
	public void action() {
		boolean play = true;
		while(play) {
			int choix = Ui.demande();
			switch(choix) {
				case 1:
					Ui.afficher(computerService.findAll());
					break;
				case 2:
					Ui.afficher(companyService.findAll());
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
					computerService.update(null);
					break;
				case 6:
					int idDelete = Ui.demandeDelete();
					computerService.delete(idDelete); 
					break;
				case 7:
					play = false;
					break;
				default:
					System.out.println();
			}
		}
	}
}
