/**
 * RCA.java - properties for a response or correct answer
 *
 * Maria Dominguez
 * April-June 2018
 **/

import java.io.Serializable;
import java.util.Vector;

public abstract class RCA implements Serializable {
	private static final long serialVersionUID = 90L;
	protected int rca_num;
	protected Vector<String> rca = new Vector<String>();

	abstract void display();
	
	public void displayRCAs() {
		for (int i = 0; i < rca_num; i++) {
			System.out.println("(" + (i+1) + ") " + rca.get(i));
		}
	}
	
	public void displayRCAsPlain() {
		for (int i = 0; i < rca_num; i++) {
			System.out.println(rca.get(i));
		}
		System.out.println("");
	}
	
	public void respPrompt() {
		if (rca_num == 1) {
			System.out.println("Enter " + rca_num + " response:");
		} else if (rca_num > 1) {
			System.out.println("Enter " + rca_num + " responses:");
		}
	}
	
	public void setRCANumTF() {
		rca_num = 1;
	}
	
	public int getRCANum() {
		return rca_num;
	}

	// typing in answers formatting info for user
		public String example(int i) {
			String example = "";
			
			switch(i) {
			case 1:
				example = "T/F example: T";
				break;
			case 2:
				example = "Multiple Choice example: A";
				break;
			case 3:
				example = "Short Answer example: here is my answer";
				break;
			case 4:
				example = "Essay example: here is my answer";
				break;
			case 5:
				example = "Ranking example: \n1\n2\n3";
				break;
			case 6:
				example = "Matching example: \nA 1\nB 2\nC 3";
				break;
			}
			
			return example;
		}
		
		// tf, mc, sa, essay
		public void setRCA() {
			for (int i = 0; i < rca_num; i++) {
				System.out.print("(" + (i+1) +") ");
				rca.add((new IO()).getString());
			}
		}
		
		// matching & ranking
		public void setRCA2(int num) {
			for (int i = 0; i < rca_num; i++) {
				String a = "";
				System.out.print("(response " + (i+1) +") ");
				for (int k = 0; k < num; k++) {
					System.out.print("(line " + (k+1) +") ");
					a += (new IO()).getString();
						if (k != (num-1)) {
							a += " ";
						}
					}
				rca.add(a);
			}
		}
		
		public String getRCA(int i) {
			return rca.get(i);
		}
		
		// 2 RCAs are equal if they contain the same things
		public boolean myEquals(RCA rca_ob) {
			for (int i = 0; i < getRCANum(); i++) {
				if (!(rca_ob.rca).contains(getRCA(i))) {
					return false;
				}
			}
			return true;
		}
		
		// 2 RCAs are equal only if they have the same things in order
		public boolean myEqualsOrd(RCA rca_ob) {
			for (int i = 0; i < getRCANum(); i++) {
				if (!((rca_ob.rca).get(i)).equals(getRCA(i))) {
					return false;
				}
			}
			return true;
		}
}
