/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbdn.sample.lti11.util;

/**
 *
 * @author moneil
 */
public class SourcedIdGenException extends Exception {
	private static final long serialVersionUID = 1L;
	String mistake;
	
	//----------------------------------------------
	// Default constructor - initializes instance variable to unknown
	  public SourcedIdGenException() {
	    super();             // call superclass constructor
	    mistake = "unknown";
	  }
	  
	//-----------------------------------------------
	// Constructor receives some kind of message that is saved in an instance variable.
	  public SourcedIdGenException(String err) {
	    super(err);     // call super class constructor
	    mistake = err;  // save message
	  }
	  
	//------------------------------------------------  
	// public method, callable by exception catcher. It returns the error message.
	  public String getError() {
	    return mistake;
	  }
}
