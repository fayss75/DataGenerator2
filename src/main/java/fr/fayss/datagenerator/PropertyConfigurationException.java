package fr.fayss.datagenerator;

public class PropertyConfigurationException extends Exception {


	public PropertyConfigurationException  (Exception pce){
		super(pce);
	}
	
	public PropertyConfigurationException  (String pce){
		super(pce);
	}
	
}
