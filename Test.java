/**
 * Test.java - test inherits survey & adds correct answers
 *
 * Maria Dominguez
 * April-June 2018
 **/

import java.util.*;
import java.io.*;

public class Test extends Survey implements Serializable {
	protected int pts_possible;
	protected int pts_actual;
	protected Vector<CA> ca_v = new Vector<CA>();
	protected Vector<String> files = new Vector<String>();
	
	private static final long serialVersionUID = 90L;
	
	public void create() {
	int ans;
	
	System.out.println("How many questions do you want to create?");
	setNumQuestions((new IO()).getInt());
	
	for (int i=0; i < num_questions; i++) {
		do {
			showMenu3();
			ans = (new IO()).getInt();
		}
		while (ans < 1 || ans > 6);
		
		CA ca = new CA();
		
		if (ans == 1) {
			TrueFalse tf = new TrueFalse();
			tf.setPrompt("True/False");
			questions.add(tf);
			ca.setRCANumTF(); // only one correct answer
		} else if (ans == 2) {
			MultipleChoice mc = new MultipleChoice();
			mc.setPrompt("multiple choice");
			mc.setChoices("multiple choice");
			ca.setRCANum();
			questions.add(mc);
		} else if (ans == 3) {
			ShortAnswer sa = new ShortAnswer();
			sa.setPrompt("short answer");
			ca.setRCANum();
			questions.add(sa);
		} else if (ans == 4) {
			Essay e = new Essay();
			e.setPrompt("essay");
			e.setRNum();
			questions.add(e);
		} else if (ans == 5) {
			Ranking r = new Ranking();
			r.setPrompt("ranking");
			r.setChoices("ranking");
			ca.setRCANum();
			ca.setCA2(ans, r.getNumChoices()); // multiple lines
			questions.add(r);
		} else if (ans == 6) {
			Matching m = new Matching();
			m.setPrompt("matching");
			m.setChoices("left side matching");
			m.setChoices2();
			ca.setRCANum();
			ca.setCA2(ans, m.getNumChoices()); // multiple lines
			questions.add(m);
		}
		
		// set correct answers & add to vector
		// exclude Essay (not graded), Matching & ranking multiple lines
		if (ans < 4) {
			ca.setCA(ans);
		}
		
		ca_v.add(ca);
	}
	}
	
	// display questions and correct answers
	public void display() {
		for (int i = 0; i < questions.size(); i++) {
			System.out.println("\nQuestion " + (i+1));
			(questions.get(i)).display();
			(ca_v.get(i)).display();
		}
	}
	
	public void modify() throws IOException, ClassNotFoundException {
		System.out.println("Number of questions: " + questions.size()); 
		int i;
		do {
			System.out.println("Which question do you wish to modify?");
			i = (new IO()).getInt();
		} while (i > questions.size() || i < 0);
		Question q = questions.get(i-1);
		
		q.modifyQ();
		
		if (!(q instanceof Essay)) {
			System.out.println("Do you want to modify the correct answer? (yes/no)");
			String ans = (new IO()).getString();
			ans = ans.toLowerCase();
			
			if (ans.equals("yes")) {
				if (q instanceof Matching) {
					Matching m = (Matching)q;
					(ca_v.get(i-1)).modCA2(m.getNumChoices());
				} else if (q instanceof Ranking) {
					Ranking r = (Ranking)q;
					(ca_v.get(i-1)).modCA2(r.getNumChoices());
				} else {
					(ca_v.get(i-1)).modCA1();
				}
			}
		}
	}
	

	public Test load() throws IOException, ClassNotFoundException {
			int save, i;
			
			do {
				// list files in current dir
				File cd = new File(".");
				File[] filesList = cd.listFiles();
				i = 1;
				for (File file : filesList) {
					if (file.isFile() && ((file.getName()).contains(".ser"))
							&& ((file.getName()).contains("test"))) {
						System.out.println(i + ") " + file.getName());
						files.add(file.getName());
						i++;
					}
				}
			
				save = (new IO()).getInt();
			} while (save < 1 || save >= i);
			
			filename = files.get(save-1);
				
			FileInputStream file1 = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(file1);
				
			Test t1 = (Test)in.readObject();
				
			in.close();
			file1.close();
				
			return t1;
	}
	
	public void take() {
		Attempt a = new Attempt();
		
		for (int i = 0; i < questions.size(); i++) {
			System.out.println("\nQuestion " + (i+1));
			Question q = questions.get(i);
			q.display();
			
			Response r = new Response();
			
			if (q instanceof TrueFalse) {
				r.setRCANum2(1);
			} else if (q instanceof Essay) {
				r.setRCANum2(q.getRNum());
			}else {
				r.setRCANum2((ca_v.get(i)).getRCANum());
			}
	
			r.respPrompt();
			
			System.out.println(getExample(q));
			
			// enter responses
			if (q instanceof Matching) {
				Matching m = (Matching)q;
				r.setRCA2(m.getNumChoices());
			} else if (q instanceof Ranking) {
				Ranking ra = (Ranking)q;
				r.setRCA2(ra.getNumChoices());
			} else {
				r.setRCA();
			}
			// add response to my attempt
			a.setResponse(r);
		}
		
		attempts.add(a);
		System.out.println(attempts.size());
	}
	
	public void grade() {
		// if no attempts 
		if (attempts.size() == 0) {
			System.out.println("No attempts to grade.");
		} else {
		
		int index;
		do {
			System.out.println("Number of attempts: " + attempts.size());
			System.out.println("Which attempt do you want to grade?");
		
			index = (new IO()).getInt();
		} while (index < 0 || index > attempts.size());
		
		Attempt a = attempts.get(index-1);
		
		pts_possible = 0;
		pts_actual = 0;
		for (int i = 0; i < num_questions; i++) {
			Question qu = questions.get(i);
			System.out.println("\nQuestion " + (i+1));
			qu.display();
			
			Response r = a.getResponse(i);
			System.out.println("");
			r.display();
			
			if (!(questions.get(i) instanceof Essay)) {
				CA ca = (ca_v.get(i));
				ca.display();
			
				if (r.myEqualsOrd(ca) && (questions.get(i) instanceof Ranking)) {
					pts_actual++;
					System.out.println("Correct!");
				} else if (r.myEquals(ca)) {
					pts_actual++;
					System.out.println("Correct!");
				} else {
					System.out.println("Incorrect!");
				}
			
				pts_possible++;
			}
		}
		
		System.out.println("\nGrade: " + pts_actual + "/" + pts_possible);
	}
	}
}
