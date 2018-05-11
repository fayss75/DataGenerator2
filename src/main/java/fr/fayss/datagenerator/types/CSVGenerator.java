/**
 * 
 */
package fr.fayss.datagenerator.types;

import java.util.List;

import fr.fayss.datagenerator.structure.CollectionGenerator;
import fr.fayss.datagenerator.structure.ComplexCollectionGenerator;
import lombok.Getter;
import lombok.Setter;
import fr.fayss.datagenerator.DataConfiguration;
import fr.fayss.datagenerator.DataGenerator;
import fr.fayss.datagenerator.PropertyConfigurationException;

/**
 * @author fayss
 *
 */
public @Getter @Setter class CSVGenerator extends ComplexCollectionGenerator {
	
	private static final int DEFAULT_ROW_NUMBER = 10;
	private static final String QUOTE = "\"";


	private int mNbRow = DEFAULT_ROW_NUMBER ;
	private boolean mUseQuote = true;
	private String mQuoteValue = QUOTE;

	/* (non-Javadoc)
	 * @see fr.fayss.datagenerator.DataGenerator#generate()
	 */
	@Override
	public String generate() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0 ; i < mNbRow ;i++) {
			sb.append(super.generate());
			sb.append("\n");
		}
		return sb.toString();
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
		return super.isConfigured() &&  getNbRow() > 0 ;
	}

	@Override
	protected StringBuilder beforeElementGen(StringBuilder stringBuilder) {
		return 	isUseQuote()?stringBuilder.append(mQuoteValue) : super.beforeElementGen(stringBuilder);
	}

	@Override
	protected StringBuilder afterElementGen(StringBuilder stringBuilder) {
		return 	isUseQuote()?stringBuilder.append(mQuoteValue) : super.afterElementGen(stringBuilder);
	}
}
