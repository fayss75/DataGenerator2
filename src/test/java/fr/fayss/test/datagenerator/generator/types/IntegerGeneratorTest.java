package fr.fayss.test.datagenerator.generator.types;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.fayss.datagenerator.generator.types.IntegerGenerator;

public class IntegerGeneratorTest {

	private IntegerGenerator integerGen ;
	
	 @Before
	 public void setUp () {
		 
		 integerGen = new IntegerGenerator();
	 }
	 
	 @Test
	 public void testGenerate () {
		 
		 Object result = integerGen.generate();
			
			Assert.assertTrue(result instanceof Integer);
			
			Integer intResult = (Integer)result ;
			
			Assert.assertTrue(integerGen.getStartInclusive() <= intResult );
			
			Assert.assertTrue(integerGen.getEndInclusive()   >= intResult );
			
			
			integerGen.setStartInclusive(1);
			integerGen.setEndInclusive(1);
			
			intResult = (Integer) integerGen.generate();
			
			Assert.assertEquals(new Integer(1), intResult);
			
			integerGen.setStartInclusive(100);
			integerGen.setEndInclusive(6);
			
			boolean exceptionCatched = false;
			try   {
				intResult = (Integer) integerGen.generate();
				
			} catch (IllegalArgumentException iae){
				exceptionCatched = true;
			}
			
			if (!exceptionCatched){
				Assert.fail("an IllegalArgumentException should be thrown");
			}
	 }
	 
}
