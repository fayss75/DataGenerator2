package fr.fayss.datagenerator;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * Data configuration Tools
 * @author fayss
 *
 */
public class DataConfigurationTools {
	
	/**
	 * Name of the property that have to be used to put the datagenerator instance
	 * 	in the DataConfiguration
	 */
	public static final String DATA_GENERATOR_INSTANCE = "dataGeneratorInstance";
	
	/**
	 * Change the propertie's value of the data generator by using the Data config property
	 * @param pDataGen the data generator
	 * @param pDataConfig the data configuration to use
	 * @throws PropertyConfigurationException
	 */
	public static void configure ( DataGenerator pDataGen,DataConfiguration pDataConfig)
			throws PropertyConfigurationException {

		for (String property : pDataConfig.getAllConfiguredPropertyName()) {
			Object propertyConfig = pDataConfig.getPropertyConfiguration(property);
			configure( pDataGen,  property,  propertyConfig ) ;
		}
	}

	
	/**
	 * Change the propertie's value of the data generator by using the Data config property
	 * The data config must contains the data generator in this method and it must store using 
	 * the static property DataConfigurationTools.DATA_GENERATOR_INSTANCE
	 * @param pDataConfig the data configuration to use
	 * @throws PropertyConfigurationException
	 */
	public static void configure (DataConfiguration pDataConfig)
			throws PropertyConfigurationException {
		DataGenerator dataGenerator = (DataGenerator) pDataConfig.getPropertyConfiguration(DATA_GENERATOR_INSTANCE);
		
		if (dataGenerator != null){
			
			
			for (String property : pDataConfig.getAllConfiguredPropertyName()) {
				if (DATA_GENERATOR_INSTANCE.equals(property)){
					continue ;
				}
				Object propertyConfig = pDataConfig.getPropertyConfiguration(property);
				configure( dataGenerator,  property,  propertyConfig ,false) ;
			}
		} else {
			throw new PropertyConfigurationException ("data generator not found in the dataConfig. use the value of the static property DataConfigurationTools.DATA_GENERATOR_CLASS") ;
		}
	}
	
	
	/**
	 * Change the value of a specific property of the data generator
	 * @param pDataGen the data generator 
	 * @param pPropertyName the property name ,must be defined in the data generator) 
	 * @param pPropertyValue the value to set
	 * @param pTestPropertyExist true if we want to throw an exception if a property doesn't exist. false to ignore
	 * @throws PropertyConfigurationException
	 */
	public static void configure(DataGenerator pDataGen, String pPropertyName, Object pPropertyValue ,boolean pTestPropertyExist)
			throws PropertyConfigurationException {
		
		try {
			if (pTestPropertyExist){
				// add the getProperty method because setProperty doesn't throw  exceptions if the property doesn't exist ..
				BeanUtils.getProperty(pDataGen, pPropertyName);
			}
			PropertyUtils.setProperty(pDataGen, pPropertyName, pPropertyValue);

		} catch (IllegalAccessException e) {
			throw new PropertyConfigurationException(e);
		} catch (InvocationTargetException e) {
			throw new PropertyConfigurationException(e);
		} catch (NoSuchMethodException e) {
			throw new PropertyConfigurationException("Property " + pPropertyName + " not found in data generator " + pDataGen.getClass());
		}
		
	}
	
	/**
	 * Change the value of a specific property of the data generator
	 * @param pDataGen the data generator 
	 * @param pPropertyName the property name ,must be defined in the data generator) 
	 * @param pPropertyValue the value to set
	 * @throws PropertyConfigurationException
	 */
	public static void configure(DataGenerator pDataGen, String pPropertyName, Object pPropertyValue ) 
			throws PropertyConfigurationException {
		 configure( pDataGen,  pPropertyName,  pPropertyValue , false); 
	}
	
	/**
	 * get the value of a property 
	 * @param pDataGen the data generator
	 * @param pPropertyName the property name
	 * @return
	 * @throws PropertyConfigurationException 
	 */
	public static Object getPropertyValue (DataGenerator pDataGen, String pPropertyName) throws PropertyConfigurationException{
		
		try {
			return PropertyUtils.getProperty(pDataGen, pPropertyName) ;
		} catch (IllegalAccessException e) {
			throw new PropertyConfigurationException(e);
		} catch (InvocationTargetException e) {
			throw new PropertyConfigurationException(e);
		} catch (NoSuchMethodException e) {
			throw new PropertyConfigurationException(e);
		}
		
	}
	
	

}
