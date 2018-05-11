/**
 * 
 */
package fr.fayss.test.datagenerator;


import org.junit.Assert;
import org.junit.Test;

import fr.fayss.datagenerator.types.IntegerGenerator;

/**
 * @author fayss
 *
 */
public class SimpleTypesGeneratorsTests {
	
	
	@Test
	public void TestIntegerGenerator (){
		
		IntegerGenerator integerGenerator = new IntegerGenerator ();
		
		
		Object result = integerGenerator.generate();
		
		Assert.assertTrue(result instanceof Integer);
		
		Integer intResult = (Integer)result ;
		
		Assert.assertTrue(integerGenerator.getStartInclusive() <= intResult );
		
		Assert.assertTrue(integerGenerator.getEndInclusive()   >= intResult );
		
		
		integerGenerator.setStartInclusive(1);
		integerGenerator.setEndInclusive(1);
		
		intResult = (Integer) integerGenerator.generate();
		
		Assert.assertEquals(new Integer(1), intResult);
		
		integerGenerator.setStartInclusive(100);
		integerGenerator.setEndInclusive(6);
		
		boolean exceptionCatched = false;
		try   {
			intResult = (Integer) integerGenerator.generate();
			
		} catch (IllegalArgumentException iae){
			exceptionCatched = true;
		}
		
		if (!exceptionCatched){
			Assert.fail("an IllegalArgumentException should be thrown");
		}
	}
	
	
	
	

}
