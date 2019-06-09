package fr.fayss.datagenerator.factory;


import fr.fayss.datagenerator.factory.xml.DataConfig;

import java.io.File;

public interface FromGenericFactory {


	DataConfig parseFile(File pXmlFile) throws DataConfigException;

	DataConfig parseFile(String pXmlFilePath) throws DataConfigException;

	void saveDataConfig(DataConfig pDataConfig, File pFile) throws DataConfigException;

	void saveDataConfig(DataConfig pDataConfig, String pFilePath) throws DataConfigException;
}
