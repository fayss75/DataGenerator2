/**
 * 
 */
package fr.fayss.datagenerator;

/**
 * @author fayss
 *
 */
public interface ReferenceDataGenerator <T> extends DataGenerator <T>{
	
	
	public DataGenerator getReferenceIdGenerator();
	
	public String getReferenceKey();

}
