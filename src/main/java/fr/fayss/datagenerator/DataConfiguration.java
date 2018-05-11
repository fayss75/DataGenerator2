package fr.fayss.datagenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * Configuration container to use when want to change the configuration on a DataGenerator, instead of using
 * getter and setters 
 * 
 * @author fayss
 *
 */
public  class DataConfiguration {

	/**
	 * Define the container of the configuration
	 * key is the property name
	 * value is the value to set
	 */
	private Map<String, Object> mPropertyConfigMap;

	/** Constructor */
	public DataConfiguration () {
		mPropertyConfigMap = new HashMap<String, Object>() ;
	}

	/**
	 * @return the value of a property
	 */
	public Object getPropertyConfiguration (String pPropertyName){
		return mPropertyConfigMap.get(pPropertyName);
	}

	/**
	 * set value to configure for a property
	 * @param pPropertyName the property name
	 * @param pValue the value to set
	 */
	public void setPropertyConfiguration (String pPropertyName, Object pValue){
		mPropertyConfigMap.put(pPropertyName, pValue);
	}

	/**
	 * @return a set property names defined in the data configuration object
	 */
	public Set <String> getAllConfiguredPropertyName (){
		return mPropertyConfigMap.keySet();
	}


}
