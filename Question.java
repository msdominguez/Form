/**
 * Question.java - properties for a question for a form
 *
 * Maria Dominguez
 * April-June 2018
 **/

import java.io.Serializable;

public abstract class Question implements Serializable {
	protected String prompt;
	protected int num_r;
	protected int q_num;
	
	private static final long serialVersionUID = 90L;
	
	abstract void display();

	public void setPrompt(String name) {
		System.out.println("Enter the prompt for your " + name + " question:");
		String pr = (new IO()).getString();
		prompt = pr;
	}
	
	public void setRNum() {
		System.out.println("Enter the number of responses a user enters:");
		int r = (new IO()).getInt();
		num_r = r;
	}
	
	public void setRNumTF() {
		num_r = 1;
	}

	// number of responses
	public int getRNum() {
		return num_r;
	}
	
	public int getQNum() {
		return q_num;
	}
	
	public void setQNum(int qnum) {
		q_num = qnum;
	}
	
	public void modPrompt(String name) {
		String ans;
		do {
			System.out.println("Do you want to modify the prompt? (yes/no)");
			ans = (new IO()).getString();
			ans = ans.toLowerCase();
			if (ans.equals("yes")) {
				setPrompt(name);
			}
		}
		while (!(ans.equals("yes") || ans.equals("no")));
	}

	abstract void modifyQ();

}
