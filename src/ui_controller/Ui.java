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
		return computer;
	}
	
	public static void afficher(Object obj) {
		System.out.println(obj);
		
	}
	
//	public static void afficher(ArrayList<Object> obj) {
//		for(Object o : obj) {
//			System.out.println(o);
//		}
//	}
	
}
