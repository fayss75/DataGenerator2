package fr.fayss.datagenerator.structure;

import java.util.Collection;
import java.util.Iterator;

import lombok.Getter;
import lombok.Setter;
import fr.fayss.datagenerator.DataConfiguration;
import fr.fayss.datagenerator.DataConfigurationTools;
import fr.fayss.datagenerator.DataGenerator;
import fr.fayss.datagenerator.PropertyConfigurationException;
import fr.fayss.datagenerator.PropertyValueException;


/**
 * Generate a list of generated data. All data use different DataGenerator class
 * We insert a separator string between each data.
 * 
 * @author fayss
 *
 */
public class ComplexCollectionGenerator implements CollectionGenerator <String>{

	/**  Define the collection of data generator */
	private @Getter @Setter Collection<DataGenerator> mDataList ;
	
	/**
	 * define the separator between generated datas 
	 * default separator is "," 
	 */
	private @Getter @Setter String mSeparator = "," ;

	/**
	 * Constructor
	 * @param pDatalist the collection of data generator
	 */
	public ComplexCollectionGenerator (Collection<DataGenerator> pDatalist){
		mDataList = pDatalist ;
	}
	
	public ComplexCollectionGenerator() {
	}

	@Override
	public String generate() {
		
		
		if( mDataList == null){
			throw new PropertyValueException ("property datalist can not be null") ;
		}

		StringBuilder sb = new StringBuilder();
		
		Iterator<DataGenerator> dataIter = mDataList.iterator();
		
		
		while (dataIter.hasNext()){
			sb.append(dataIter.next().generate());
			
			if (dataIter.hasNext()){
				sb.append(getSeparator());
			}
		}

		return sb.toString();
	}

	@Override
	public void configure(DataConfiguration pDataconfig) throws PropertyConfigurationException {
		
		DataConfigurationTools.configure(this, pDataconfig);
	}


	/* (non-Javadoc)
	 * @see fr.fayss.datagenerator.DataGenerator#isConfigured()
	 */
	@Override
	public boolean isConfigured() {
		return mDataList != null;
	}

}
