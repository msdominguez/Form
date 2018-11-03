/**
 * Attempt.java - saves a filled out form
 *
 * Maria Dominguez
 * April-June 2018
 **/

import java.io.Serializable;
import java.util.Vector;

public class Attempt implements Serializable {
	private static final long serialVersionUID = 90L;
	protected Vector<Response> r_v = new Vector<Response>();
	
	public void setResponse(Response r) {
		r_v.add(r);
	}
	
	public Response getResponse(int index) {
		return r_v.get(index);
	}
	
	public int getNumResponses() {
		return r_v.size();
	}

}
