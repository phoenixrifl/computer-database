package main.java.app;
import main.java.exception.NotOneTwoNumber;
import main.java.ui_controller.Controller;

public class Main {

	public static void main(String[] args) throws NotOneTwoNumber {		

		Controller.getInstance().action();
	}

}
