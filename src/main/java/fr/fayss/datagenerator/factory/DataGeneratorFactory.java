package fr.fayss.datagenerator.factory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import fr.fayss.datagenerator.atg.xml.DataConfig;

/**
 * @author fayss
 *
 */
public class DataGeneratorFactory {

	private static final String XML_BINDING_PACKAGE = "fr.fayss.datagenerator.atg.xml" ;



	public static DataConfig  parseXmlFile (File pXmlFile) throws DataConfigException{

		try {
			JAXBContext jc = JAXBContext.newInstance(XML_BINDING_PACKAGE);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			
			return (DataConfig) unmarshaller.unmarshal(pXmlFile);

		} catch (JAXBException e) {
			throw new DataConfigException (e);
		}
	}

	public static DataConfig  parseXmlFile (String pXmlFilePath) throws DataConfigException{
		return parseXmlFile (new File( pXmlFilePath));
	}

	public static void saveDataConfig (DataConfig pDataConfig,File pFile) throws DataConfigException{
		OutputStream os = null ;
		try {
			JAXBContext jc = JAXBContext.newInstance(XML_BINDING_PACKAGE);
			Marshaller marshaller = jc.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			os = new FileOutputStream( pFile );

			marshaller.marshal( pDataConfig, os );
		} catch (JAXBException e) {
			throw new DataConfigException (e);
		} catch (FileNotFoundException e) {
			throw new DataConfigException (e);
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					throw new DataConfigException (e);
				}
			}
		}
	}

	public static void saveDataConfig (DataConfig pDataConfig,String pFilePath) throws DataConfigException {

		File file = new File(pFilePath) ;
		saveDataConfig(pDataConfig, file);
	}

}
