package fr.fayss.datagenerator.types;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.RandomUtils;

import fr.fayss.datagenerator.DataConfiguration;
import fr.fayss.datagenerator.DataConfigurationTools;
import fr.fayss.datagenerator.DataGenerator;
import fr.fayss.datagenerator.PropertyConfigurationException;

/**
 * Generates an Integer number
 * 
 * @author fayss
 */
public @Getter @Setter class IntegerGenerator implements DataGenerator {

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

	@Override
	public Object generate() {
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


}
