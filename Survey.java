/**
 * Survey.java - survey with questions, responses, & attempts
 *
 * Maria Dominguez
 * April-June 2018
 **/

import java.util.*;
import java.io.*;

public class Survey implements Serializable {
	protected String filename;
	protected String name;
	protected int num_questions;
	protected Vector<Question> questions = new Vector<Question>();
	protected Vector<String> files = new Vector<String>();
	protected Vector<Attempt> attempts = new Vector<Attempt>();
	
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
		
		if (ans == 1) {
			TrueFalse tf = new TrueFalse();
			tf.setPrompt("True/False");
			questions.add(tf);
			tf.setRNumTF();
		} else if (ans == 2) {
			MultipleChoice mc = new MultipleChoice();
			mc.setPrompt("multiple choice");
			mc.setChoices("multiple choice");
			questions.add(mc);
			mc.setRNum();
		} else if (ans == 3) {
			ShortAnswer sa = new ShortAnswer();
			sa.setPrompt("short answer");
			questions.add(sa);
			sa.setRNum();
		} else if (ans == 4) {
			Essay e = new Essay();
			e.setPrompt("essay");
			questions.add(e);
			e.setRNum();
		} else if (ans == 5) {
			Ranking r = new Ranking();
			r.setPrompt("ranking");
			r.setChoices("ranking");
			questions.add(r);
			r.setRNum();
		} else if (ans == 6) {
			Matching m = new Matching();
			m.setPrompt("matching");
			m.setChoices("left side matching");
			m.setChoices2();
			questions.add(m);
			m.setRNum();
		}
		}
	}
	
	public void showMenu3() {
		System.out.println("\n1) Add a new T/F question");
		System.out.println("2) Add a new multiple choice question");
		System.out.println("3) Add a new short answer question");
		System.out.println("4) Add a new essay question");
		System.out.println("5) Add a new ranking question");
		System.out.println("6) Add a new matching question");
	}
	
	public void display() {
		for (int i = 0; i < questions.size(); i++) {
			System.out.println("\nQuestion " + (i+1));
			(questions.get(i)).display();
		}
	}
	
	public void modify() throws IOException, ClassNotFoundException {
		System.out.println("Number of questions: " + questions.size()); 
		int i;
		do {
			System.out.println("Which question do you wish to modify?");
			i = (new IO()).getInt();
		} while (i > questions.size() || i < 1);
		Question q = questions.get(i-1);
		
		q.modifyQ();
	}
	
	public Survey load() throws IOException, ClassNotFoundException {
			int save, i;
			
			do {
				// list files in current dir
				File cd = new File(".");
				File[] filesList = cd.listFiles();
				i = 1;
				for (File file : filesList) {
					if (file.isFile() && ((file.getName()).contains(".ser"))
						&& ((file.getName()).contains("survey"))) {
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
			
			Survey s1 = (Survey)in.readObject();
			
			in.close();
			file1.close();
			
			return s1;
	}
	
	public void save(String name) throws IOException {
			File file;
			int num = 1;
			
			do {
				filename = name + (num++) + ".ser";
			    file = new File(".", filename); 
			} while(file.exists());
			
			saveHelp(name);
	}
	
	public void saveHelp(String name) throws IOException {
		FileOutputStream file2 = new FileOutputStream(filename);
		ObjectOutputStream out = new ObjectOutputStream(file2);
		
		out.writeObject(this);
		
		System.out.println("Saved your " + name + "!");
		
		out.close();
		file2.close();
	}
	
	public int getNumQuestions() {
		return num_questions;
	}
	
	public void setNumQuestions(int num_q) {
		num_questions = num_q;
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
			} else {
				r.setRCANum2(q.getRNum());
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
	
	public String getExample(Question q) {
		String example = "";
	
		if (q instanceof TrueFalse) {
			example = "T/F example: T";
		} else if (q instanceof ShortAnswer) {
			example = "Short Answer example: here is my answer";
		} else if (q instanceof Ranking) {
			example = "Ranking example: \n1\n2\n3";
		} else if (q instanceof Matching) {
			example = "Matching example: \nA 1\nB 2\nC 3";
		} else if (q instanceof MultipleChoice) {
			example = "Multiple Choice example: A";
		} else if (q instanceof Essay) {
			example = "Essay example: here is my answer";
		}
	
		return example;
	}
	
	public void tabulate() {
		int q;
		
		for (q = 0; q < num_questions; q++) { // for each q 
			HashMap<Response, Integer> map = new HashMap<Response, Integer>();
			Question qu = questions.get(q);
			System.out.println("\nQuestion " + (q+1));
			qu.display();
			System.out.println("\nReplies:");
			
			for (int a = 0; a < attempts.size(); a++) { // for each attempt
				Response r1 = (attempts.get(a)).getResponse(q);
				
				boolean unique = true;
				
				// just print responses
				r1.displayRCAsPlain();
					
				// Ranking - order matters
				if (qu instanceof Ranking) {
					for (Response r: map.keySet()) {
						int value = map.get(r);
						if (r1.myEqualsOrd(r)) {
							map.put(r, value+1);
							unique = false;
						}
					}
					
					if (unique) {
						map.put(r1, 1);
					}
					
				} else if (!(qu instanceof Essay)) {
					for (Response r: map.keySet()) {
						int value = map.get(r);
						if (r1.myEquals(r)) {
							map.put(r, value+1);
							unique = false;
						}
					}
					
					if (unique) {
						map.put(r1, 1);
					}
				}
			}
			// exclude essay for tab printing for each q
			if (!(qu instanceof Essay)) {
				System.out.println("Tabulation:");
				for (Response r: map.keySet()) {
					//String key = r.toString();
					String value = map.get(r).toString();
					System.out.println(value + ")");
					r.displayRCAsPlain();
				}
			}
		}
	}
}