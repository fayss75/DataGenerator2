package fr.fayss.datagenerator.atg;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import fr.fayss.datagenerator.DataGenerator;
import fr.fayss.datagenerator.DataGeneratorBuilder;
import fr.fayss.datagenerator.PropertyConfigurationException;
import fr.fayss.datagenerator.atg.xml.DataConfig;
import fr.fayss.datagenerator.atg.xml.PropertyConfig;
import fr.fayss.datagenerator.atg.xml.PropertyValue;
import fr.fayss.datagenerator.factory.DataConfigException;
import fr.fayss.datagenerator.factory.DataGeneratorFactory;

import static fr.fayss.datagenerator.DataConfigurationTools.*;

public class AtgTemplateGenerator {


	private  Map<String, DataGenerator> dataGenMap  = new HashMap<String, DataGenerator> ();
	private String mainGeneratorId ;


	public static final String File_Path ="C:\\Users\\Picsou\\IdeaProjects\\DataGenerator\\datagenerator\\configurationsV2\\Config.xml" ;

	
	
	public static final String PROPERTY_CONFIG_TYPE_LIST = "list";
	public static final String PROPERTY_CONFIG_TYPE_DEFAULT = "default";
	
	
	private AtgTemplateGenerator () {
		
	}
	
	
	
	public static AtgTemplateGenerator getInstance () {
		return new AtgTemplateGenerator ();
	}
	
	

	
	public static void main(String[] args) {


		try {

			DataConfig dataconfig = DataGeneratorFactory.parseXmlFile(File_Path) ;

			System.out.println(dataconfig.getName());

			AtgTemplateGenerator templateGen = AtgTemplateGenerator.getInstance();

			templateGen.generateTemplatefromXml (new File (File_Path));
			Map<String, DataGenerator> dataGenMap = templateGen.getDataGenMap() ;

			System.out.println(dataGenMap);
			
			DataGenerator maindataGen = dataGenMap.get(templateGen.getMainGeneratorId());
			
			
			File file = new File("C:\\Users\\Picsou\\Documents\\Projects\\AtgTemplateGenerator.txt");
			
			DataGeneratorBuilder.build(maindataGen, file);
			

		} catch (DataConfigException e) {
			e.printStackTrace();
		}
	}

	
	
	
	public  DataGenerator getDataGeneratorById (String pId ){
		return dataGenMap.get(pId) ;
	}
	
	
	public  void registerDataGenerator (String pId , DataGenerator pDataGenerator){
		dataGenMap.put(pId, pDataGenerator) ;
	}
	
	/**
	 * iterate throw data generators and add them to dataGenMap
	 * @param dataConfig
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	public  void registerDataGenerator (DataConfig dataConfig) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		
		for (fr.fayss.datagenerator.atg.xml.DataConfig.DataGenerator dagaGenConfig : dataConfig.getDataGenerator()){
			String dataGenClassName = dagaGenConfig.getClazz() ;

			Class  <DataGenerator> dataGenClass =  (Class  <DataGenerator>) Class.forName(dataGenClassName);

			// create an instance of the data generator
			DataGenerator DataGenIns = dataGenClass.newInstance();
			if (!StringUtils.isBlank(dagaGenConfig.getId())) {
				registerDataGenerator(dagaGenConfig.getId(), DataGenIns);
			}
		}
	}
	
	
	/**
	 * Configure property config
	 * @param pPropertyConfig
	 * @throws PropertyConfigurationException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public void configurePropertyconfig (DataGenerator pDataGenerator , PropertyConfig pPropertyConfig) throws PropertyConfigurationException {
		
		
		//  inject DataGenerator ref depending of propertyConfig type
		if (pPropertyConfig.getDataGeneratorRef() != null && !pPropertyConfig.getDataGeneratorRef().isEmpty()){
			
			String propertyConfigType = pPropertyConfig.getType();
			
			// default type inject one DataGenerator
			if (propertyConfigType == null || PROPERTY_CONFIG_TYPE_DEFAULT.equals(propertyConfigType)) {
				DataGenerator subDataGen = getDataGeneratorById (pPropertyConfig.getDataGeneratorRef().get(0));
				configure(pDataGenerator, pPropertyConfig.getName(), subDataGen);
				
			// list type inject a list of DataGenerator
			} else if (PROPERTY_CONFIG_TYPE_LIST.equals(propertyConfigType)){
				
				List <DataGenerator> subDataGenerators = new ArrayList<DataGenerator>() ;
				
				for (String dataGenRefId : pPropertyConfig.getDataGeneratorRef()) {
					DataGenerator dataGenRef= getDataGeneratorById(dataGenRefId) ;
					subDataGenerators.add(dataGenRef);
				}
				configure(pDataGenerator, pPropertyConfig.getName(), subDataGenerators);
				
			} else {
				// TODO throw Exception
			}
			
		} 

		// set property values
		if (pPropertyConfig.getPropertyValue() != null && !pPropertyConfig.getPropertyValue().isEmpty()) {

			DataGenerator property= (DataGenerator) getPropertyValue(pDataGenerator ,pPropertyConfig. getName());
			for (PropertyValue propertyValue : pPropertyConfig.getPropertyValue()) {
				configure(property, propertyValue.getName(), propertyValue.getValue());
			}
		}
		
	}
	
	
	/**
	 * Create DataGenerator from XMl file
	 * @param pXmlFile
	 */
	public  void generateTemplatefromXml (File pXmlFile) {


		try {
			// parse xml file 
			DataConfig dataConfig = DataGeneratorFactory.parseXmlFile(pXmlFile);
			
			
			setMainGeneratorId(dataConfig.getMainGenerators().getName());
			

			//1 iterate threw data generators and add them to dataGenMap
			registerDataGenerator (dataConfig);

			//2 iterate threw data generators to configure them
			for (fr.fayss.datagenerator.atg.xml.DataConfig.DataGenerator dagaGenConfig : dataConfig.getDataGenerator()){

				DataGenerator DataGenIns = 	getDataGeneratorById(dagaGenConfig.getId());

				// set property values
				for (PropertyValue propertyValue : dagaGenConfig.getPropertyValue()) {
					configure(DataGenIns, propertyValue.getName(), propertyValue.getValue());
				}

				// configure sub data generator 
				for (PropertyConfig propertyConfig : dagaGenConfig.getPropertyConfig()) {
					configurePropertyconfig(DataGenIns, propertyConfig);
				}

			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PropertyConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataConfigException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public Map<String, DataGenerator> getDataGenMap() {
		return dataGenMap;
	}

	public void setMainGeneratorId(String pMainGeneratorId) {
		mainGeneratorId = pMainGeneratorId;
	}
	public String getMainGeneratorId() {
		return mainGeneratorId;
	}
}
