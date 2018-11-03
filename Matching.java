/**
 * Matching.java - matching question for a form
 *
 * Maria Dominguez
 * April-June 2018
 **/

import java.util.*;
import java.io.*;

public class Matching extends MultipleChoice implements Serializable {
	protected int num_choices2;
	protected Vector<String> choices2 = new Vector<String>();
	private static final long serialVersionUID = 90L;
	
	public void display() {
		System.out.println(prompt);
		displayChoices();
	}
	
	public void displayChoices() {
		for (int i = 0; i < num_choices; i++) {
			String string1 = String.valueOf((char)(i + 65)) + ")";
			String string2 = choices.get(i);
			String string3 = String.valueOf(i+1) + ")";
			String string4 = choices2.get(i);
			
			System.out.format("%1s%2s%5s%2s\n", string1, string2, string3, string4);
		}
	}
	
	public int getNumChoices2() {
		return num_choices2;
	}
	
	public void setNumChoices2(int num_c2) {
		num_choices2 = num_c2;
	}
	
	public String getChoice2(int choice2) {
		return choices2.get(choice2 - 1);
	}
	
	public void setChoice2(String choice2) {
		choices2.add(choice2);
	}
	
	public void setChoices2() {
		System.out.println("Enter choices (right side):");
		
		for (int i = 0; i < num_choices; i++) {
			System.out.print((i+1) + ") ");
			setChoice2((new IO()).getString());
		}
	}
	
	public void modifyQ() {
		modPrompt("matching");
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
			displayChoices();
			String side = "";
			do {
				System.out.println("Which side? (left/right)");
				side = (new IO()).getString();
				side = side.toLowerCase();
			} while (!(side.equals("left") || side.equals("right")));
			
			if (side.equals("left")) {
				modLeft();
			} else if (side.equals("right")) {
				modRight(choices2);
			}
		}
	}
}
