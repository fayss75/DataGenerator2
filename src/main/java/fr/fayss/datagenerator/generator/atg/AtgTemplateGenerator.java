package fr.fayss.datagenerator.generator.atg;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.esotericsoftware.yamlbeans.YamlWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.fayss.datagenerator.DataGeneratorBuilder;
import fr.fayss.datagenerator.factory.xml.DataConfig;
import fr.fayss.datagenerator.factory.xml.PropertyConfig;
import fr.fayss.datagenerator.factory.xml.PropertyValue;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

import fr.fayss.datagenerator.DataGenerator;
import fr.fayss.datagenerator.PropertyConfigurationException;
import fr.fayss.datagenerator.factory.DataConfigException;
import fr.fayss.datagenerator.factory.DataGeneratorFactory;

import static fr.fayss.datagenerator.DataConfigurationTools.*;

@Log4j2
public class AtgTemplateGenerator {



	private  Map<String, DataGenerator> dataGenMap  = new HashMap ();
	private String mainGeneratorId ;


	private static final String FOLDER_PATH = "C:\\Users\\Picsou\\Documents\\Projects\\";
	private static final String FILE_PATH = FOLDER_PATH +"DataGenerator2\\src\\main\\resources\\configurationTest\\Config.xml" ;

	
	
	private static final String PROPERTY_CONFIG_TYPE_LIST = "list";
	private static final String PROPERTY_CONFIG_TYPE_DEFAULT = "default";
	
	
	private static AtgTemplateGenerator getInstance () {
		return new AtgTemplateGenerator ();
	}
	
	

	
	public static void main(String[] args) {


		try {

			DataConfig dataconfig = DataGeneratorFactory.parseFile(FILE_PATH, DataGeneratorFactory.SourceType.XML) ;

			System.out.println(dataconfig.getName());

			YamlWriter writer = new YamlWriter(new FileWriter(FOLDER_PATH +"output.yml"));
			writer.write(dataconfig);
			writer.close();


			ObjectMapper mapper = new ObjectMapper();


//Object to JSON in file
			mapper.writeValue(new File(FOLDER_PATH +"file.json"), dataconfig);



			AtgTemplateGenerator templateGen = AtgTemplateGenerator.getInstance();

			templateGen.generateTemplatefromXml (new File (FILE_PATH));
			Map<String, DataGenerator> dataGenMap = templateGen.getDataGenMap() ;

			System.out.println(dataGenMap);
			
			DataGenerator maindataGen = dataGenMap.get(templateGen.getMainGeneratorId());
			
			
			File file = new File(FOLDER_PATH +"AtgTemplateGenerator.txt");
			
			DataGeneratorBuilder.getInstance().generateAll(maindataGen, file);
			

		} catch (DataConfigException | IOException e) {
			log.error(e);
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
	public  void registerDataGenerator (DataConfig dataConfig) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		
		for (DataConfig.DataGenerator dagaGenConfig : dataConfig.getDataGenerator()){
			String dataGenClassName = dagaGenConfig.getClazz() ;

			Class  <DataGenerator> dataGenClass =  (Class  <DataGenerator>) Class.forName(dataGenClassName);

			// create an instance of the data generator
			DataGenerator DataGenIns = dataGenClass.newInstance();
			if (!StringUtils.isBlank(dagaGenConfig.getId())) {
				registerDataGenerator(dagaGenConfig.getId(), DataGenIns);
			}
		}
	}


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


			// parse xml file
		DataConfig dataConfig = null;
		try {
			dataConfig = DataGeneratorFactory.parseFile(pXmlFile, DataGeneratorFactory.SourceType.XML);



		setMainGeneratorId(dataConfig.getMainGenerators().getName());
			

			//1 iterate threw data generators and add them to dataGenMap
			registerDataGenerator (dataConfig);

			//2 iterate threw data generators to configure them
			for (DataConfig.DataGenerator dagaGenConfig : dataConfig.getDataGenerator()){

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

		} catch (DataConfigException | ClassNotFoundException | InstantiationException | IllegalAccessException | PropertyConfigurationException e) {
			log.error(e);
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
