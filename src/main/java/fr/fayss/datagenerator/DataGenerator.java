package fr.fayss.datagenerator;



/**
 * Interface of all data generators
 * 
 * @author fayss
 *
 */
public interface DataGenerator <T>{

	/** Returns the generated data 
	 * this method should call the method isGeneratorConfigured () before 
	 * generating any data.
	 * @throws GenerationException  if there is any error during the generation
	 * */
	public T generate() throws GenerationException;
	
	/**
	 * Configure properties of the data generator
	 * Not sure to keep this method....
	 * @param pDataconfig
	 * @throws PropertyConfigurationException
	 */
	public void configure(DataConfiguration pDataconfig) throws PropertyConfigurationException ;
	
	/**
	 * Verify if the dataGenerator has the minimum configuration to work.
	 * if we used the Contructors of the class , if shoud be ok , but if we use
	 * the class.newInstance () method, we have to verify that all the configuration is done.  
	 * It means that if we run the generate method we will not have exceptions related
	 * to not defined mandatory property for example.
	 * 
	 * Test if the data generator is well configured at least to work without 
	 * Exception throw at its level
	 * @return true if its well configured, false otherwise
	 */
	public boolean isConfigured();
}
