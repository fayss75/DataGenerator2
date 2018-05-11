/**
 * 
 */
package fr.fayss.datagenerator;

/**
 * @author fayss
 *
 */
public interface ReferenceDataGenerator extends DataGenerator{
	
	
	public DataGenerator getReferenceIdGenerator();
	
	public String getReferenceKey();

}
