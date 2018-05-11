/**
 * 
 */
package fr.fayss.datagenerator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author fayss
 * Singleton class that contains a Queue of datas to generate
 *
 */
public class GenerationBuffer {


	private Queue<DataConfiguration> mDataConfiguration;
	private static GenerationBuffer mGenerationBuffer ;

	private static Map <String,Object> mDatas;

	public static GenerationBuffer getInstance () {

		if (mGenerationBuffer == null) {
			mGenerationBuffer = new GenerationBuffer () ;	
		}

		return mGenerationBuffer;
	}	


	private GenerationBuffer() {
		mDataConfiguration = new LinkedList<DataConfiguration>();
		mDatas = new HashMap<String, Object>();
	}

	public void pushItem (DataConfiguration item) throws PropertyConfigurationException{

		if (item.getPropertyConfiguration(DataConfigurationTools.DATA_GENERATOR_INSTANCE) == null) {
			throw new PropertyConfigurationException ("data generator not found in the dataConfig. use the value "
					+ "of the static property DataConfigurationTools.DATA_GENERATOR_INSTANCE") ;
		} else {
			mDataConfiguration.add(item);
		}
	}
	
	public DataConfiguration popItem () {
		return mDataConfiguration.poll();
	}

	public	Object getData (String key){
		return mDatas.get(key);
	}
	
	public void setData (String key , Object value){
		mDatas.put(key, value);
	}
	
	
	public boolean hasNext () {
		return ! mDataConfiguration.isEmpty();
	}
}
