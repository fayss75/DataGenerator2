package fr.fayss.datagenerator.types;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.RandomStringUtils;

import fr.fayss.datagenerator.DataConfiguration;
import fr.fayss.datagenerator.DataConfigurationTools;
import fr.fayss.datagenerator.DataGenerator;
import fr.fayss.datagenerator.PropertyConfigurationException;

/**
 * Generate a String 
 * 
 * @author fayss
 */
public @Getter @Setter class StringGenerator implements DataGenerator{

	/** 
	 * Define the length of the generated string 
	 * Default value is 5
	 */ 	
	private  Integer mStringLength = 5 ;
	
	/**
	 *  Define a static prefix that the generated string starts with
	 *  Default value is empty string
	 */
	private String mPrefix="";
	
	/**
	 *  Define a static suffix that the generated string end with
	 *  Default value is empty string
	 */
	private String mSuffix="";

	
	@Override
	public Object generate() {
		StringBuilder sb = new StringBuilder();
		return sb.append(getPrefix()).
				append(RandomStringUtils.random(getStringLength(),true,false)).
				append(getSuffix()).toString();
	}
	
	@Override
	public void configure(DataConfiguration pDataconfig) throws PropertyConfigurationException {
		DataConfigurationTools.configure(this, pDataconfig);
	}

	/* (non-Javadoc)
	 * @see fr.fayss.datagenerator.DataGenerator#isConfigured()
	 */
	@Override
	public boolean isConfigured() {
		return getStringLength() != null &&
				getStringLength() > 0;
	}

}
