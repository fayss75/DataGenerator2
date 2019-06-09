/**
 * 
 */
package fr.fayss.datagenerator;

/**
 * @author fayss
 *
 */
public interface ReferenceDataGenerator <T> extends DataGenerator <T>{
	
	
	DataGenerator getReferenceIdGenerator();
	
	String getReferenceKey();

}
