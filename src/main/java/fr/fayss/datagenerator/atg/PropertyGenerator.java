package fr.fayss.datagenerator.atg;


import lombok.Getter;
import lombok.Setter;
import fr.fayss.datagenerator.DataConfiguration;
import fr.fayss.datagenerator.DataConfigurationTools;
import fr.fayss.datagenerator.DataFormatter;
import fr.fayss.datagenerator.DataGenerator;
import fr.fayss.datagenerator.InternalException;
import fr.fayss.datagenerator.PropertyConfigurationException;
import fr.fayss.datagenerator.PropertyValueException;
import fr.fayss.datagenerator.types.StringGenerator;

/**
 * Generate a property of repository item
 * Example : <set-property name="PROPERTY_NAME">PROPER_VALUE</set-property>
 * @author fayss
 *
 */
public @Getter @Setter class PropertyGenerator implements DataFormatter <String>{

	/** Define the default Class to use for data generatrion if mDataTypeGenerator and value properties are not defined */
	private static final Class<? extends DataGenerator> DEFAULT_DATAGENERATOR = StringGenerator.class;

	/** Define the property data generator */
	private DataGenerator  mDataTypeGenerator ;
	
	/**
	 * Define the property name
	 * Default value is PROPERTY_NAME
	 */
	private String  mPropertyName = "PROPERTY_NAME";
	
	/**
	 * Define the value of the property. 
	 * If mValue is not null , ignore the mDataTypeGenerator property
	 */
	private String mValue = null;


	/**
	 * Constructor
	 */
	public PropertyGenerator (){
		try {
			mDataTypeGenerator =  DEFAULT_DATAGENERATOR.newInstance();
		} catch (InstantiationException | IllegalAccessException ex) {
			throw new InternalException(ex);
		}
	}
	
	/**
	 * Constructor
	 * @param pPropertyName the name of the property
	 */
	public PropertyGenerator (String pPropertyName){
		this();
		mPropertyName = pPropertyName;
		
	}
	
	/**
	 * Constructor
	 * @param pPropertyName the name of the property
	 * @param pDataGenerator the data generator of the property
	 */
	public PropertyGenerator (String pPropertyName, DataGenerator pDataGenerator){
		mDataTypeGenerator = pDataGenerator ;
		mPropertyName = pPropertyName;
	}


	@Override
	public String generate() {
		
		Object result ;
		
		if(getValue() != null) {
			result = getValue();
		} else if (getDataTypeGenerator() != null){
			result = getDataTypeGenerator().generate();
		} else {
			throw new PropertyValueException("Value and DataTypeGenerator properties can not be both null");
		}

		StringBuilder sb = new StringBuilder();
		
		sb.append("\t<set-property name=\"").append(getPropertyName()).append("\">")
		.append(result).append("</set-property>\n");
		
		return sb.toString();
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
		
		return getValue() != null || getDataTypeGenerator() != null ;
	}


}
