package fr.fayss.datagenerator.generator.types;

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
 * Generates a Double number
 * 
 * @author fayss
 */
public @Getter @Setter class DoubleGenerator implements DataGenerator <Double>{

	/**
	 * Define the minimum value that can be generated
	 * Default value is 1000d
	 */
	private Double mStartInclusive = 1000d;
	
	/**
	 * Define the max value that can be generated
	 * Default value is 9000d
	 */
	private Double mEndInclusive = 9000d;
	
	/**
	 * Define the number of decimals
	 * Default value is 2
	 */
	private Integer mScale = 2 ;
	
	@Override
	public Double generate() {
		
		Double result = RandomUtils.nextDouble(getStartInclusive(), getEndInclusive());
		return new BigDecimal(result).setScale(getScale(), RoundingMode.HALF_UP).doubleValue();
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
