/**
 * 
 */
package fr.fayss.datagenerator.types;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import fr.fayss.datagenerator.DataConfiguration;
import fr.fayss.datagenerator.DataGenerator;
import fr.fayss.datagenerator.PropertyConfigurationException;

/**
 * @author fayss
 *
 */
public @Getter @Setter class CSVGenerator implements DataGenerator <String>{
	
	public static final int DEFAULT_ROW_NUMBER = 10;
	
	private int mNbRow = DEFAULT_ROW_NUMBER ;
	private List <DataGenerator> mColumnGenerators;

	/**
	 * 
	 */

	/* (non-Javadoc)
	 * @see fr.fayss.datagenerator.DataGenerator#generate()
	 */
	@Override
	public String generate() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see fr.fayss.datagenerator.DataGenerator#configure(fr.fayss.datagenerator.DataConfiguration)
	 */
	@Override
	public void configure(DataConfiguration pDataconfig)
			throws PropertyConfigurationException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see fr.fayss.datagenerator.DataGenerator#isConfigured()
	 */
	@Override
	public boolean isConfigured() {
		return getNbRow() > 0 && getColumnGenerators() != null;
	}

}
