package fr.fayss.test.datagenerator;

import fr.fayss.datagenerator.*;
import fr.fayss.datagenerator.generator.atg.PropertyGenerator;
import fr.fayss.datagenerator.generator.atg.ReferencePropertyGenerator;
import fr.fayss.datagenerator.generator.atg.RepositoryItemGenerator;
import fr.fayss.datagenerator.generator.structure.CollectionGenerator;
import fr.fayss.datagenerator.generator.structure.SimpleCollectionGenerator;
import fr.fayss.datagenerator.generator.structure.TreeGenerator;
import fr.fayss.datagenerator.generator.types.DoubleGenerator;
import fr.fayss.datagenerator.generator.types.IntegerGenerator;
import fr.fayss.datagenerator.generator.types.LoremIpsumGenerator;
import fr.fayss.datagenerator.generator.types.StringGenerator;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DataGeneratorBuilderTest {


	@Test
	@Ignore ("local test just used for development")
	public void generateAllTest () {

		File file = new File("C:\\Users\\FAYCAL\\Documents\\project\\filename2.txt");

		DataGenerator productGen = createCatalog ();
		DataGeneratorBuilder.getInstance().generateAll(productGen, file);

	}

	private DataGenerator createCatalog() {

		RepositoryItemGenerator skuGen =  new RepositoryItemGenerator("sku", skupropertyBuilder()) ;
		skuGen.getIdGenerator().setPrefix("sku");

		RepositoryItemGenerator prdGen =  new RepositoryItemGenerator("product", productpropertyBuilder()) ;
		prdGen.getIdGenerator().setPrefix("prd");
		ReferenceDataGenerator refDataGenerator = new ReferencePropertyGenerator(skuGen,skuGen.getIdGenerator(),"childSkus");
		CollectionGenerator treeGen = new SimpleCollectionGenerator(refDataGenerator);
		prdGen.getPropertyList().add(new PropertyGenerator("childSkus", treeGen));


		RepositoryItemGenerator catGen =  new RepositoryItemGenerator("category", catpropertyBuilder()) ;
		catGen.getIdGenerator().setPrefix("cat");
		ReferenceDataGenerator refCatDataGenerator = new ReferencePropertyGenerator(catGen,catGen.getIdGenerator(),"childCategories");
		TreeGenerator treeCatGen = new TreeGenerator(refCatDataGenerator);
		catGen.getPropertyList().add(new PropertyGenerator("childCategories", treeCatGen));

		ReferenceDataGenerator refPrdDataGenerator = new ReferencePropertyGenerator(prdGen,prdGen.getIdGenerator(),"childProducts");
		CollectionGenerator treePrdGen = new SimpleCollectionGenerator(refPrdDataGenerator);
		catGen.getPropertyList().add(new PropertyGenerator("childProducts", treePrdGen));

		return catGen;
	}

	private List<PropertyGenerator> catpropertyBuilder() {

		ArrayList<PropertyGenerator> dgList = new ArrayList<PropertyGenerator>();
		LoremIpsumGenerator loremIpsumGenerator = new LoremIpsumGenerator();

		dgList.add(new PropertyGenerator("catString1", loremIpsumGenerator));
		dgList.add(new PropertyGenerator("catString2", loremIpsumGenerator));
		dgList.add(new PropertyGenerator("catdefault"));
		dgList.add(new PropertyGenerator("catdoubleProperty", new DoubleGenerator()));
		dgList.add(new PropertyGenerator("catintProperty", new IntegerGenerator()));

		return dgList ;
	}

	private List<PropertyGenerator> productpropertyBuilder() {

		ArrayList<PropertyGenerator> dgList = new ArrayList<PropertyGenerator>();
		StringGenerator stringGenerator = new StringGenerator();

		dgList.add(new PropertyGenerator("prdString1", stringGenerator));
		dgList.add(new PropertyGenerator("prdString2", stringGenerator));
		dgList.add(new PropertyGenerator("prddefault"));
		dgList.add(new PropertyGenerator("prddoubleProperty", new DoubleGenerator()));
		dgList.add(new PropertyGenerator("prdintProperty", new IntegerGenerator()));

		return dgList ;
	}

	private List<PropertyGenerator> skupropertyBuilder() {

		ArrayList<PropertyGenerator> dgList = new ArrayList<PropertyGenerator>();
		StringGenerator stringGenerator = new StringGenerator();
		dgList.add(new PropertyGenerator("skuString1", stringGenerator));
		dgList.add(new PropertyGenerator("skuString2", stringGenerator));
		dgList.add(new PropertyGenerator("skudefault"));
		dgList.add(new PropertyGenerator("skudoubleProperty", new DoubleGenerator()));
		dgList.add(new PropertyGenerator("skuintProperty", new IntegerGenerator()));

		return dgList ;
	}
}
