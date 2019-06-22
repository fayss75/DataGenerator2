package fr.fayss.test.datagenerator.generator.structure;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import fr.fayss.datagenerator.DataGenerator;
import fr.fayss.datagenerator.PropertyValueException;
import fr.fayss.datagenerator.generator.structure.ComplexCollectionGenerator;

public class ComplexCollectionGeneratorTest{

	private ComplexCollectionGenerator  complexCollectionGen ;
	
	@Before
	public void setUp () {
		complexCollectionGen = new ComplexCollectionGenerator() ;
	}
	
	
	@Test
	public void testIsConfigured() {
		assertFalse(complexCollectionGen.isConfigured());
		
		Collection <DataGenerator> dataList = new ArrayList<DataGenerator>() ;
		complexCollectionGen.setDataList(dataList);
		
		assertTrue(complexCollectionGen.isConfigured());
		
	}
			
	@Test (expected = PropertyValueException.class)
	public void testGenerateException() {
		complexCollectionGen.generate();
	}

}
