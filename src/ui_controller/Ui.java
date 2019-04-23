package ui_controller;

import java.util.Scanner;

public class Ui {
	
	
	private static Scanner sc;

	public static int demande() {
		sc = new Scanner(System.in);
		System.out.println("Liste des ordinateurs, tapez 1");
		System.out.println("Liste des compagnies, tapez 2");
		System.out.println("Détails d'un ordinateur, tapez 3");
		System.out.println("Créer un ordinateur, tapez 4");
		System.out.println("Mettre à jour un ordinateur, tapez 5");
		System.out.println("Supprimer un ordinateur, tapez 6");
		System.out.println("Quitter, taper 7");

		int input = sc.nextInt();
	
		return input;
		
	}
	
	public static int demandeId() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Donnez l'id de l'ordinateur");
		int input = sc.nextInt();
		return input;
	}
	
	public static String demandeCreate() {
		String computer = "";
		Scanner sc = new Scanner(System.in);
		System.out.println("Donnez un nom");
		String name = sc.nextLine();
		System.out.println("Donnez une date du format AAAA-MM-DD");
		String introduced = sc.nextLine();
		System.out.println("Donnez une date du format AAAA-MM-DD");
		String discontinued = sc.nextLine();
		System.out.println("Donnez l'id d'une compagnie");
		String company_id = sc.nextLine();
		computer+=name+","+introduced+","+discontinued+","+company_id;
		System.out.println(computer);
		return computer;
	}
	
	public static int demandeDelete() {
		int input;
		Scanner sc = new Scanner(System.in);
		System.out.println("Donnez un id d'ordinateur à supprimer");
		input = sc.nextInt();
		return input;
	}
	
	
	
	public static void afficher(Object obj) {
		System.out.println(obj);
		
	}
	

	
}