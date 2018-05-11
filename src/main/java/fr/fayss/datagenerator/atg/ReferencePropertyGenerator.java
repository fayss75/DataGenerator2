/**
 * 
 */
package fr.fayss.datagenerator.atg;

import lombok.Getter;
import lombok.Setter;
import fr.fayss.datagenerator.DataConfiguration;
import fr.fayss.datagenerator.DataConfigurationTools;
import fr.fayss.datagenerator.DataFormatter;
import fr.fayss.datagenerator.DataGenerator;
import fr.fayss.datagenerator.GenerationBuffer;
import fr.fayss.datagenerator.GenerationException;
import fr.fayss.datagenerator.PropertyConfigurationException;
import fr.fayss.datagenerator.ReferenceDataGenerator;

/**
 * @author fayss
 *
 */
public  class ReferencePropertyGenerator implements ReferenceDataGenerator <String>{



	private @Getter RepositoryItemGenerator mRepositoryItemGenerator ;
	private @Getter @Setter DataGenerator<String>  mReferenceIdGenerator;
	private @Getter @Setter String mReferenceKey;
	
	public ReferencePropertyGenerator (){
	}
	
	
	
	/**
	 * Constructor
	 */
	public ReferencePropertyGenerator (RepositoryItemGenerator pRepositoryItemGenerator ,
			DataGenerator  pDataTypeGenerator,String pRefKey){
		mRepositoryItemGenerator = pRepositoryItemGenerator; 
		mReferenceIdGenerator = pDataTypeGenerator ;
		mReferenceKey = pRefKey;
	}

	/* (non-Javadoc)
	 * @see fr.fayss.datagenerator.DataGenerator#generate()
	 */
	@Override
	public String generate() {

		String repositoryId = getReferenceIdGenerator().generate();
		
		GenerationBuffer genBuffer = GenerationBuffer.getInstance();
		
		DataConfiguration dataConfigutation = new DataConfiguration() ;
		dataConfigutation.setDataGenerator(getRepositoryItemGenerator());
		dataConfigutation.setPropertyConfiguration(DataFormatter.VALUE_PROP,repositoryId);
		try {
			genBuffer.pushItem(dataConfigutation);
		} catch (PropertyConfigurationException e) {
			throw new GenerationException(e) ;
		}
		
		return repositoryId;
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
		return getReferenceIdGenerator() != null &&
				getRepositoryItemGenerator() != null &&
				getReferenceKey() != null;
	}



	public void setRepositoryItemGenerator(RepositoryItemGenerator pRepositoryItemGenerator) {
		mRepositoryItemGenerator = pRepositoryItemGenerator;
		mReferenceIdGenerator = pRepositoryItemGenerator.getIdGenerator();
	}


}
