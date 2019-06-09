package fr.fayss.datagenerator.factory;

import fr.fayss.datagenerator.factory.xml.DataConfig;
import lombok.NoArgsConstructor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

@NoArgsConstructor
public class FromXmlFactory implements FromGenericFactory {

	private static final String XML_BINDING_PACKAGE = "fr.fayss.datagenerator.factory.xml" ;


	public DataConfig parseFile(File pXmlFile) throws DataConfigException {

		try {
			JAXBContext jc = JAXBContext.newInstance(XML_BINDING_PACKAGE);
			Unmarshaller unmarshaller = jc.createUnmarshaller();

			return (DataConfig) unmarshaller.unmarshal(pXmlFile);
		} catch (JAXBException e) {
			throw new DataConfigException(e);
		}



	}

	public DataConfig  parseFile(String pXmlFilePath) throws DataConfigException{
		return parseFile (new File( pXmlFilePath));
	}

	public void saveDataConfig(DataConfig pDataConfig, File pFile) throws DataConfigException{
		OutputStream os = null ;
		try {
			JAXBContext jc = JAXBContext.newInstance(XML_BINDING_PACKAGE);
			Marshaller marshaller = jc.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			os = new FileOutputStream( pFile );

			marshaller.marshal( pDataConfig, os );
		} catch (JAXBException | FileNotFoundException e) {
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

	public void saveDataConfig(DataConfig pDataConfig, String pFilePath) throws DataConfigException {
		File file = new File(pFilePath) ;
		saveDataConfig(pDataConfig, file);
	}
}
