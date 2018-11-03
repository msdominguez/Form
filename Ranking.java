/**
 * Ranking.java - ranking question for a form
 *
 * Maria Dominguez
 * April-June 2018
 **/

import java.io.*;

public class Ranking extends MultipleChoice implements Serializable {
	
	private static final long serialVersionUID = 90L;
	
	public void display() {
		System.out.println(prompt);
		displayChoices();
	}
	
	public void displayChoices() {
		for (int i = 0; i < num_choices; i++) {
			System.out.println((i+1) + ") " + choices.get(i)); 
		}
	}
	
	public void setChoices(String name) {
		System.out.println("Enter the number of choices for your " + name + " question:");
	
		setNumChoices((new IO()).getInt());
		
		System.out.println("Enter choices:");
		
		for (int i = 0; i < num_choices; i++) {
			System.out.print((i+1) + ") ");
			setChoice((new IO()).getString());
		}
	}
	public void modifyQ() {
		modPrompt("ranking");
		modChoices();
	}
	
	public void modChoices() {
		String ans;
		do {
			System.out.println("Do you want to modify the choices? (yes/no)");
			ans = (new IO()).getString();
			ans = ans.toLowerCase();
		} while (!(ans.equals("yes") || ans.equals("no")));
		
		if (ans.equals("yes")) {
			modRight(choices);
		}
	}
}
