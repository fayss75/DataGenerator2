package fr.fayss.datagenerator.structure;

import fr.fayss.datagenerator.DataGenerator;

/**
 * This interface is used by any collection generator 
 * A Collection generator use a separator between generated data
 * The getter and the setter of the separator must be defined.
 * The value must be saved on a way that we can get it using the method getSeparator
 * 
 * @author fayss
 */
public interface CollectionGenerator <T> extends DataGenerator<T> {
	
	/**
	 * @return the separator value
	 */
	String getSeparator();
	
	
	/**
	 * Set the separator value
	 * @param pSeparator the value of the separator
	 */
	void setSeparator(String pSeparator) ;

}
