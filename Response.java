/**
 * Response.java - stores user input as a single response
 *
 * Maria Dominguez
 * April-June 2018
 **/

import java.io.Serializable;

public class Response extends RCA implements Serializable {
	private static final long serialVersionUID = 90L;
	
	// display one user's response (1 Resp vector of resps)
	public void display() {
		if (rca_num == 1 ) {
			System.out.println("Response:");
		} else if (rca_num > 1) {
			System.out.println("Responses:");
		}
		
		super.displayRCAs();
	}
	
	public void setRCANum2(int rnum) {
		rca_num = rnum;
	}
}
