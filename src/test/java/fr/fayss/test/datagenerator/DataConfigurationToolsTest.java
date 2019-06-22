package fr.fayss.test.datagenerator;


import org.junit.Assert;
import org.junit.Test;

import fr.fayss.datagenerator.DataConfigurationConstant;
import fr.fayss.datagenerator.DataConfigurationTools;
import fr.fayss.datagenerator.DataGenerator;
import fr.fayss.datagenerator.PropertyConfigurationException;
import fr.fayss.datagenerator.generator.atg.RepositoryItemGenerator;
import fr.fayss.datagenerator.generator.types.IntegerGenerator;



/**
 * this is a test class
 * 
 * @author fayss
 *
 */
public class DataConfigurationToolsTest {
	
	
	@Test
	public void testConfigure (){
		
		IntegerGenerator integerDataGenerator= new IntegerGenerator ();

		
		Assert.assertEquals(Integer.valueOf(1000), integerDataGenerator.getStartInclusive());
		Assert.assertEquals(Integer.valueOf(9000), integerDataGenerator.getEndInclusive());
		
		try {
			DataConfigurationTools.configure(integerDataGenerator, DataConfigurationConstant.START_INCLUSIVE, 5);
		} catch (PropertyConfigurationException e) {
			Assert.fail("PropertyConfigurationException not expected");
		}
	
		
		Assert.assertEquals(Integer.valueOf(5), integerDataGenerator.getStartInclusive());
		Assert.assertEquals(Integer.valueOf(9000), integerDataGenerator.getEndInclusive());
		
		
	}
	
}
