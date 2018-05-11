/**
 * 
 */
package fr.fayss.datagenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author fayss
 *
 */
public class DataGeneratorBuilder {



	/**
	 * Execute the data generator and write the result in the output file
	 * @param pDataGenerator the generator to execute
	 * @param pOutputFile the output file 
	 */
	public static void build (DataGenerator pDataGenerator, File pOutputFile) {

		BufferedWriter bw = null;
		try {
			if (!pOutputFile.exists()) {
				pOutputFile.createNewFile();
			}

			FileWriter fw = new FileWriter(pOutputFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(pDataGenerator.generate().toString());

			GenerationBuffer generationBuffer = 
					GenerationBuffer.getInstance();
			// check if any dataconfiguratione exist in the buffer,
			// if any, call the configure and then the generate method
			while (generationBuffer.hasNext()){

				DataConfiguration config = generationBuffer.popItem();

				try {
					DataConfigurationTools.configure(config);
				} catch (PropertyConfigurationException pce) {
					throw new InternalException (pce);
				}

				bw.write((config.getDataGenerator()).generate().toString());
			}
		} catch (IOException ioe) {
			throw new InternalException (ioe);
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (IOException ioe) {
				throw new InternalException (ioe);
			}
		}

	}

}
