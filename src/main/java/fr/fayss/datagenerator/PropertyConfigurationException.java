package fr.fayss.datagenerator;

public class PropertyConfigurationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PropertyConfigurationException  (Exception pce){
		super(pce);
	}
	
	public PropertyConfigurationException  (String pce){
		super(pce);
	}
	
}
