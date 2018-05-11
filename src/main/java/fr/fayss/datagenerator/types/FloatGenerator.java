package fr.fayss.datagenerator.types;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.RandomUtils;

import fr.fayss.datagenerator.DataConfiguration;
import fr.fayss.datagenerator.DataConfigurationTools;
import fr.fayss.datagenerator.DataGenerator;
import fr.fayss.datagenerator.PropertyConfigurationException;

/**
 * Generates a Float number
 * 
 * @author fayss
 */
public @Getter @Setter class FloatGenerator implements DataGenerator <Float>{
	
	/**
	 * Define the minimum value that can be generated
	 * Default value is 1000f
	 */
	private Float mStartInclusive = 1000f;
	
	/**
	 * Define the maximum value that can be generated
	 * Default value is 9000f
	 */
	private Float mEndInclusive = 9000f;
	
	/**
	 * Define the number of decimals
	 * Default value is 2
	 */
	private Integer mScale = 2 ;

	@Override
	public Float generate() {
		
		Float result =  RandomUtils.nextFloat(getStartInclusive(), getEndInclusive());
		return new BigDecimal(result).setScale(getScale(), RoundingMode.HALF_UP).floatValue();
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
				getStartInclusive() < getEndInclusive() &&
				getScale() != null &&
				getScale() > 0;
	}
	

}
