package fr.fayss.test.datagenerator;


import org.junit.Assert;
import org.junit.Test;

import fr.fayss.datagenerator.DataConfigurationConstant;
import fr.fayss.datagenerator.DataConfigurationTools;
import fr.fayss.datagenerator.DataGenerator;
import fr.fayss.datagenerator.PropertyConfigurationException;
import fr.fayss.datagenerator.atg.RepositoryItemGenerator;
import fr.fayss.datagenerator.types.IntegerGenerator;



/**
 * this is a test class
 * 
 * @author fayss
 *
 */
public class DataConfigurationToolsTest {
	
	
	@Test
	public void testConfigure (){
		
		IntegerGenerator integerDataGenerator=null;
		try {
			integerDataGenerator = IntegerGenerator.class.newInstance();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		Assert.assertEquals(new Integer(1000), integerDataGenerator.getStartInclusive());
		Assert.assertEquals(new Integer(9000), integerDataGenerator.getEndInclusive());
		
		try {
			DataConfigurationTools.configure(integerDataGenerator, DataConfigurationConstant.START_INCLUSIVE, 5);
		} catch (PropertyConfigurationException e) {
			Assert.fail("PropertyConfigurationException not exptected");
		}
	
		
		Assert.assertEquals(new Integer(5), integerDataGenerator.getStartInclusive());
		Assert.assertEquals(new Integer(9000), integerDataGenerator.getEndInclusive());
		
		
	}
	
	public void testGetInstance () {
		


		try {
			DataGenerator DataGenIns = RepositoryItemGenerator.class.newInstance() ;
			
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
