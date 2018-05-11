/**
 * 
 */
package fr.fayss.datagenerator.atg;

import lombok.Getter;
import lombok.Setter;
import fr.fayss.datagenerator.types.IntegerGenerator;

/**
 * Generate a repositoryId
 * By default it generate an integer, without prefix and suffix
 * @author fayss
 *
 */
public @Getter @Setter class ItemDescriptorIdGenerator extends IntegerGenerator {
	
	/** Define the prefix of the generated id */
	private String mPrefix="";
	
	/** Define the suffix of the generated id */
	private String mSuffix="";

	@Override
	public Object generate() {
		StringBuilder builder = new StringBuilder() ;
		return builder.append(getPrefix()).append(super.generate()).append(getSuffix()).toString();
	}


}
