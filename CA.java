/**
 * CA.java - properties for a correct answer
 *
 * Maria Dominguez
 * April-June 2018
 **/

import java.io.Serializable;

public class CA extends RCA implements Serializable {
	private static final long serialVersionUID = 90L;
	
	public void display() {
		// 0 is essay, do not display
		if (rca_num == 1) {
			System.out.println("\nThe correct answer is:");
		} else if (rca_num > 1) {
			System.out.println("\nThe correct answers are:");
		}
		
		super.displayRCAs();
	}
	
	public void setRCANum() {
		System.out.println("Enter number of correct choices:");
		
		int ans = (new IO()).getInt();
		
		rca_num = ans;
	}
	
	public void setCA2(int ans, int num_c) {
		System.out.println("Enter correct choice:");
		System.out.println(super.example(ans));
		
		super.setRCA2(num_c);
	}
	
	public void setCA(int ans) {
		System.out.println("Enter correct choice:");
		System.out.println(super.example(ans));
		
		super.setRCA();
	}
	
	// which CA or 1 CA
	public int modCAHelp() {
		int index = 0;
		if (rca_num > 1) {
			do {
				System.out.println("Which one?");
				super.displayRCAs();
				index = (new IO()).getInt() - 1;
			} while (index > rca_num);
		} 
		
		System.out.println("Enter correct choice:");
		
		return index;
	}
	
	public void modCA1() {
		int index = modCAHelp();
		rca.set(index, (new IO()).getString());
	}
	
	// multiple lines for 1 ans get stored as one string
	public void modCA2(int num_c) {
		int index = modCAHelp();
		
		String a = "";
		for (int i = 0; i < num_c; i++) {
			System.out.print("(line " + (i+1) +") ");
			a += "\n" + (new IO()).getString();
		}
		rca.set(index, a);
	}
	
	
}
