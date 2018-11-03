/**
 * Main.java - driver for form (test/survey) maker & manager
 *
 * Maria Dominguez
 * April-June 2018
 **/

import java.io.IOException;

public class Main {
	public static void showMenu() {
		System.out.println("1) Survey");
		System.out.println("2) Test");
	}
	
	public static void showMenu2(String name, boolean Test) {
		System.out.println("\n1) Create a new " + name);
		System.out.println("2) Display a " + name);
		System.out.println("3) Load a " + name);
		System.out.println("4) Save a " + name);
		System.out.println("5) Modify an existing " + name);
		System.out.println("6) Take a " + name);
		System.out.println("7) Tabulate a " + name);
		if (Test) {
			System.out.println("8) Grade a " + name);
			System.out.println("9) Quit");
		} else {
			System.out.println("8) Quit");
		}
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		int ans, ans2;
		
		// show menu 1
		do {
			showMenu();
			ans = (new IO()).getInt();
		}
		while (!(ans == 1 || ans == 2));
		
		// show menu 2 - survey
		if (ans == 1) {
			Survey s = new Survey();
			
			do {
				do {
					showMenu2("Survey", false);
					ans2 = (new IO()).getInt();
					}
				while (ans2 < 1 || ans2 > 9);
				
				switch(ans2) {
				case 1:
					s = (new Survey());
					s.create();
					break;
				case 2:
					s.display();
					break;
				case 3:
					System.out.println("Please select a file to load:");
					s = s.load();
					break;
				case 4:
					s.save("survey");
					break;
				case 5:
					System.out.println("Which survey do you want to modify?");
					s = s.load();
					s.modify();
					s.saveHelp("survey");
					break;
				case 6:
					System.out.println("Which survey do you want to take?");
					s = s.load();
					s.take();
					s.saveHelp("survey");
					break;
				case 7:
					System.out.println("Which survey do you want to tabulate?");
					s = s.load();
					s.tabulate();
					break;
				case 8:
					System.exit(0);
				}

			} while(true);
			
			// show menu 2 - test
		} else if (ans == 2) {

			Test t = new Test();
			
			do {
				do {
					showMenu2("Test", true);
					ans2 = (new IO()).getInt();
					}
				while (ans2 < 1 || ans2 > 9);
			
				System.out.println("");
				
				switch(ans2) {
				case 1:
					t = (new Test());
					t.create();
					break;
				case 2:
					t.display();
					break;
				case 3:
					System.out.println("Please select a file to load:");
					t = t.load();
					break;
				case 4:
					t.save("test");
					break;
				case 5:
					System.out.println("Which test do you want to modify?");
					t = t.load();
					t.modify();
					t.saveHelp("test");
					break;
				case 6:
					System.out.println("Which test do you want to take?");
					t = t.load();
					t.take();
					t.saveHelp("test");
					break;
				case 7:
					System.out.println("Which test do you want to tabulate?");
					t = t.load();
					t.tabulate();
					break;
				case 8:
					System.out.println("Which test do you want to grade?");
					t = t.load();
					t.grade();
					break;
				case 9:
					System.exit(0);
				}

			} while(true);
		}
	} // void main args
} // class main
