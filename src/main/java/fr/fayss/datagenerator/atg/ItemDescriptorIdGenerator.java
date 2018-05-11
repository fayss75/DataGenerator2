/**
 * 
 */
package fr.fayss.datagenerator.atg;

import fr.fayss.datagenerator.DataConfiguration;
import fr.fayss.datagenerator.DataConfigurationTools;
import fr.fayss.datagenerator.DataGenerator;
import fr.fayss.datagenerator.PropertyConfigurationException;
import lombok.Getter;
import lombok.Setter;
import fr.fayss.datagenerator.types.IntegerGenerator;
import org.apache.commons.lang3.RandomUtils;

/**
 * Generate a repositoryId
 * By default it generate an integer, without prefix and suffix
 * @author fayss
 *
 */
public @Getter @Setter class ItemDescriptorIdGenerator implements DataGenerator<String> {
	
	/** Define the prefix of the generated id */
	private String mPrefix="";
	
	/** Define the suffix of the generated id */
	private String mSuffix="";


	/**
	 * Define the minimum value that can be generated
	 * Default value is 1000
	 */
	private Integer mStartInclusive = 1000;

	/**
	 * Define the maximu value that can be generated
	 * Default value is 9000
	 */
	private Integer mEndInclusive = 9000;


	public Integer generateNum() {
		return RandomUtils.nextInt(getStartInclusive(), getEndInclusive());
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
		return getStartInclusive() != null &&
				getEndInclusive() != null &&
				getStartInclusive() < getEndInclusive();
	}

	@Override
	public String generate() {
		StringBuilder builder = new StringBuilder() ;


		return builder.append(getPrefix())
				.append(generateNum())
				.append(getSuffix())
				.toString();
	}


}
