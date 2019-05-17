package app;

import exception.SqlCommandeException;
import ui_controller.Controller;

public class Main {

	
	public static void main(String[] args) throws SqlCommandeException {		
		
		Controller.getInstance().action();
	}

	

}
