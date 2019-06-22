package fr.fayss.test.datagenerator.generator.types;

import org.junit.Test;

import fr.fayss.datagenerator.generator.types.StringGenerator;

import static org.junit.Assert.*;

import org.junit.Before;

public class StringGeneratorTest {
	
	private StringGenerator stringGen ;
	
	@Before
	public void setUp () {
		 stringGen = new StringGenerator() ;
	}
	
	
	@Test
	public void testStringGenerator () {
		
		
		assertEquals(stringGen.getStringLength() , new Integer(5));
		assertEquals(stringGen.getPrefix() , "");
		assertEquals(stringGen.getSuffix() , "");
	}
	
	@Test
	public void testIsConfigured (){
		
		assertTrue(stringGen.isConfigured());
		
		stringGen.setStringLength(0);
		assertFalse(stringGen.isConfigured());
		
		stringGen.setStringLength(null);
		assertFalse(stringGen.isConfigured());
		
		stringGen.setStringLength(1);
		assertTrue(stringGen.isConfigured());	
		
		stringGen.setStringLength(-1);
		assertFalse(stringGen.isConfigured());
	}
	
	@Test
	public void testGenerate () {
	
		assertTrue(stringGen.generate() instanceof String) ;

		String result = (String) stringGen.generate();
		assertTrue(result.length() == stringGen.getStringLength());

		stringGen.setStringLength(10);
		result = (String) stringGen.generate();
		assertTrue(result.length() == 10);
	}
	
	
	@Test
	public void testGeneratePrefix () {
	
		stringGen.setStringLength(10);
		String result = (String) stringGen.generate();
		
		assertTrue(result.length() == 10);
		
		stringGen.setPrefix("PREFIX");
		result = (String) stringGen.generate();
		
		assertTrue(result.length() == 16);	
		assertTrue(result.startsWith("PREFIX"));
	}
	
	@Test
	public void testGenerateSuffix () {
	

		stringGen.setStringLength(20);
		stringGen.setSuffix("SUFFIX");
		String result = (String) stringGen.generate();
		
		assertTrue(result.length() == 26);	
		assertTrue(result.endsWith("SUFFIX"));
	}

}
