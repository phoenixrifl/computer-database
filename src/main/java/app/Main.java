package main.java.app;

import main.java.exception.SqlCommandeException;
import main.java.ui_controller.Controller;

public class Main {

	
	public static void main(String[] args) throws SqlCommandeException {		
		
		Controller.getInstance().action();
	}

	

}
