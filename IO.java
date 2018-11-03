/**
 * IO.java - handles user input
 *
 * Maria Dominguez
 * April-June 2018
 **/

import java.io.*;
import java.util.*;

public class IO implements Serializable {
	
	private static final long serialVersionUID = 90L;
	
	public int getInt() {
		Scanner reader = new Scanner(System.in);
		int r = 0;
		try {
			r = reader.nextInt();
			return r;
		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Try again.");
			return getInt();
		}
	}
	
	public String getString() {
		Scanner reader = new Scanner(System.in);
		String r = "";
		try {
			do {
				r = reader.nextLine();
			} while (r.equals(""));
			return r;
		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Try again.");
			return getString();
		}
	}
}
