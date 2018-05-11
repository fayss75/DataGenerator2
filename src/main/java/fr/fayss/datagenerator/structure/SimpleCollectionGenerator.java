package fr.fayss.datagenerator.structure;


import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;
import fr.fayss.datagenerator.DataConfiguration;
import fr.fayss.datagenerator.DataConfigurationTools;
import fr.fayss.datagenerator.DataGenerator;
import fr.fayss.datagenerator.PropertyConfigurationException;
import fr.fayss.datagenerator.PropertyValueException;

/**
 * Generate a list of generated data. All data use the same DataGenerator class
 * We insert a separator string between each data.
 * 
 * @author fayss
 *
 */
public  @Getter @Setter class SimpleCollectionGenerator  implements CollectionGenerator<String> {
	
	/** 
	 * define the quantity of generated datas
	 * default quantity is 5 
	 */
	private int mQuantity = 5 ;
	
	/** define the generator used to generate data */
	private DataGenerator mDataGenerator ;
	
	/**
	 * define the separator between generated data 
	 * default separator is "," 
	 */
	private String mSeparator = "," ;
	

	/**
	 * Constructor
	 * @param pDataGenerator the data generator
	 * @param pQuantity the quantity of generated data
	 */
	public SimpleCollectionGenerator (DataGenerator pDataGenerator,int pQuantity){
		mDataGenerator = pDataGenerator ;
		mQuantity = pQuantity ;
	}
	
	/**
	 * Constructor
	 * @param pDataGenerator the data generator
	 */
	public SimpleCollectionGenerator (DataGenerator pDataGenerator){
		mDataGenerator = pDataGenerator ;
	}
	
	
	public SimpleCollectionGenerator (){
	}


	@Override
	public String generate() {
		
		if (getQuantity() <= 0){
			throw new PropertyValueException("Quantity must be a positive value, actual value:" + getQuantity());
		}
		
		StringBuilder sb = new StringBuilder();
		
		for( int i = 1 ; i < getQuantity() ; i ++)
		{
			sb.append(getDataGenerator().generate());
				sb.append(mSeparator);
		}
		sb.append(getDataGenerator().generate());
		return sb.toString();
	}


	@Override
	public void configure(DataConfiguration pDataconfig)
			throws PropertyConfigurationException {
		DataConfigurationTools.configure(this, pDataconfig);
	}

	/* (non-Javadoc)
	 * @see fr.fayss.datagenerator.DataGenerator#isConfigured()
	 */
	@Override
	public boolean isConfigured() {
		return getQuantity() > 0 && 
				getDataGenerator() != null && 
				!StringUtils.isBlank(getSeparator()) ;
	}

}
