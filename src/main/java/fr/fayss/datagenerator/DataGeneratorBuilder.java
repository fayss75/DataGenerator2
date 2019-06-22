package fr.fayss.datagenerator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fayss
 *
 */
public class DataGeneratorBuilder {


	private static DataGeneratorBuilder dataGeneratorBuilder;


	public static DataGeneratorBuilder getInstance () {
		if (dataGeneratorBuilder == null){
			dataGeneratorBuilder = new DataGeneratorBuilder ();
		}
		return dataGeneratorBuilder ;
	}


	private DataGeneratorBuilder () {
	}


	public String generateAll (DataGenerator ... pDataGenerators  ) {


		StringBuilder sb = new StringBuilder();

		for (DataGenerator  dataGenerator : pDataGenerators )  {

			sb.append(dataGenerator.generate());
		}


		GenerationBuffer generationBuffer =
				GenerationBuffer.getInstance();

		while (generationBuffer.hasNext()){

			DataConfiguration config = generationBuffer.popItem();

			try {
				DataConfigurationTools.configure(config);
			} catch (PropertyConfigurationException pce) {
				throw new InternalException (pce);
			}

			sb.append((config.getDataGenerator()).generate());
		}

		return sb.toString();
	}

	/**
	 * Execute the data generator and write the result in the output file
	 * @param pDataGenerator the generator to execute
	 * @param pOutputFile the output file
	 */
	public void generateAll(DataGenerator  pDataGenerator  , File pOutputFile) {

		List <DataGenerator>  dataGenerators = new ArrayList () ;

		dataGenerators.add(pDataGenerator) ;

		generateAll(dataGenerators  ,  pOutputFile) ;
	}

	/**
	 * Execute the data generator and write the result in the output file
	 * @param pDataGenerators the generator to execute
	 * @param pOutputFile the output file
	 */
	public void generateAll(List <DataGenerator>  pDataGenerators  , File pOutputFile) {

		GenerationBuffer generationBuffer =
				GenerationBuffer.getInstance();
		Writer bw = null;
		try {
			if (!pOutputFile.exists()) {
				pOutputFile.createNewFile();
			}

			FileWriter fw = new FileWriter(pOutputFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);

			for (DataGenerator dataGenerator : pDataGenerators)
				bw.write(dataGenerator.generate().toString());


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
			generationBuffer.clear();
			try {
				if (bw != null)
					bw.close();
			} catch (IOException ioe) {
				throw new InternalException (ioe);
			}
		}

	}

}
