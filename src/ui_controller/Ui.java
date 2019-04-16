package ui_controller;

import java.util.Scanner;

public class Ui {
	
	
	public int demande() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Liste des ordinateurs, tapez 1");
		System.out.println("Liste des compagnies, tapez 2");
		System.out.println("Détails des ordinateurs, tapez 3");
		System.out.println("Créer un ordinateur, tapez 4");
		System.out.println("Mettre à jour un ordinateur, tapez 5");
		System.out.println("Supprimer un ordinateur, tapez 6");

		int input = sc.nextInt();
		sc.close();
		return input;
		
	}
	
	public void action() {
		int choix = demande();
		switch(choix) {
			case 1:
				System.out.println("choix 1");
				break;
			case 2:
				System.out.println("choix 2");
				break;
			case 3:
				System.out.println("choix 3");
				break;
			case 4:
				System.out.println("choix 4");
				break;
			case 5:
				System.out.println("choix 5");
				break;
			case 6:
				System.out.println("choix 6");
				break;
			default:
				System.out.println("choix incorrect");
					
		}
	}
}
