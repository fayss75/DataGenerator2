package fr.fayss.datagenerator.factory;

import java.io.File;

import fr.fayss.datagenerator.factory.xml.DataConfig;

/**
 * @author fayss
 *
 */
public class DataGeneratorFactory {


	public enum  SourceType {
		XML (new FromXmlFactory());

		FromGenericFactory factory;

		SourceType(FromGenericFactory pFactory) {
			factory = pFactory;
		}
		 public FromGenericFactory getFactory () {return factory;}

	}

	public static DataConfig parseFile (File pFile, SourceType type) throws DataConfigException {
		return type.getFactory().parseFile (pFile);
	}

	public static DataConfig parseFile (String pFilePath, SourceType type) throws DataConfigException {
		return type.getFactory().parseFile (pFilePath);
	}


	public static void saveDataConfig (DataConfig pDataConfig,File pFile,SourceType type) throws DataConfigException{
		type.getFactory().saveDataConfig(pDataConfig,pFile);
	}

	public static void saveDataConfig (DataConfig pDataConfig,String pFilePath,SourceType type) throws DataConfigException {
		type.getFactory().saveDataConfig(pDataConfig,pFilePath);
	}

}
