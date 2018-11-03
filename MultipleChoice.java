/**
 * MultipleChoice.java - multiple choice question for a form
 *
 * Maria Dominguez
 * April-June 2018
 **/

import java.util.*;
import java.io.*;

public class MultipleChoice extends Question implements Serializable {
	protected int num_choices;
	protected Vector<String> choices = new Vector<String>();
	
	private static final long serialVersionUID = 90L;
	
	public void display() {
		System.out.println(prompt);
		displayChoices();
	}
	
	public void displayChoices() {
		for (int i = 0; i < num_choices; i++) {
			System.out.println(String.valueOf((char)(i + 65)) + ") " + choices.get(i)); 
		}
	}
	
	public int getNumChoices() {
		return num_choices;
	}
	
	public void setNumChoices(int num_c) {
		num_choices = num_c;
	}
	
	public String getChoice(char choice) {
		//  A=0 B=1 C=3 ... etc
		char ch = Character.toUpperCase(choice);
		
		int index = Character.getNumericValue(ch) - 10;
		return choices.get(index);
	}
	
	public void setChoice(String choice) {
		choices.add(choice);
	}
	
	public void setChoices(String name) {
		System.out.println("Enter the number of choices for your " + name + " question:");
	
		setNumChoices((new IO()).getInt());
		
		System.out.println("Enter choices:");
		
		for (int i = 0; i < num_choices; i++) {
			System.out.print(String.valueOf((char)(i + 65)) + ") ");
			setChoice((new IO()).getString());
		}
	}
	
	public void createQ() {
		setPrompt("multiple choice");
		setChoices("multiple choice");
	}
	
	public void modifyQ() {
		modPrompt("multiple choice");
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
			modLeft();
		}
	}
	
	// letters
	public void modLeft() {
		int index = -1;
		do {
			System.out.println("Which one? (number)");
			String choice = (new IO()).getString();
			
			char c = choice.charAt(0); 
			char ch = Character.toUpperCase(c);
			index = Character.getNumericValue(ch) - 10;
		} while (index >= num_choices || index < 0);
			
		System.out.println("Enter new choice:");
		String newc = (new IO()).getString();
		
		choices.set(index, newc);
	}
	
	// numbers
	public void modRight(Vector<String> c) {
		int index = -1;
		do {
			System.out.println("Which one? (number)");
			index = (new IO()).getInt() - 1;
		} while (index >= num_choices || index < 0);
			
		System.out.println("Enter new choice:");
		String newc = (new IO()).getString();
		
		c.set(index, newc);
	}
}
