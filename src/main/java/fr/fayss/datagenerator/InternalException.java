/**
 * 
 */
package fr.fayss.datagenerator;

/**
 * This exception should never be thrown, if this is the case its means that there is a bug somewhere in the framework
 *
 * @author fayss
 */
public class InternalException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7331320328393866746L;

	public InternalException (Exception e){
		super(e);
	}

}
