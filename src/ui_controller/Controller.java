package ui_controller;
import persistence.ComputerDAO;
import persistence.CompanyDAO;
import exception.MyException;
import modele.Computer;
public class Controller {

	public void action() {
		int choix = Ui.demande();
		switch(choix) {
			case 1:
				Ui.afficher(new ComputerDAO().findAll());
				break;
			case 2:
				Ui.afficher(new CompanyDAO().findAll());
				break;
			case 3:
				int id = Ui.demandeId();
				Ui.afficher(new ComputerDAO().find(id));
				break;
			case 4:
				new ComputerDAO().create(null); //à faire
				break;
			case 5:
				new ComputerDAO().update(null); //à faire
				break;
			case 6:
				new ComputerDAO().delete(null); //à faire
				break;
			default:
				System.out.println();
		}
	}
}
