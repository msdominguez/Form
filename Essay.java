/**
 * Essay.java - essay question for a form
 *
 * Maria Dominguez
 * April-June 2018
 **/

import java.io.*;

public class Essay extends Question implements Serializable {
	
	private static final long serialVersionUID = 90L;
	
	public void display() {
		System.out.println(prompt);
	}
	
	public void modifyQ() {
		modPrompt("Essay");
	}
}
