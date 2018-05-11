package fr.fayss.datagenerator.types;

import de.svenjacobs.loremipsum.LoremIpsum;
import fr.fayss.datagenerator.DataConfiguration;
import fr.fayss.datagenerator.DataConfigurationTools;
import fr.fayss.datagenerator.DataGenerator;
import fr.fayss.datagenerator.GenerationException;
import fr.fayss.datagenerator.PropertyConfigurationException;
import lombok.Getter;
import lombok.Setter;

/**
 * Generate a Lorem Ipsum words or paragraphs
 *
 * @author fayss
 *
 */
public @Getter @Setter class LoremIpsumGenerator implements DataGenerator <String>{


	/**
	 * Define the LoremIpsum api
	 */
	private static LoremIpsum LOREMIPSUM = new LoremIpsum();

	/**
	 * Define the quantity of words or paragraphs to generate
	 * quantity must be positive
	 * Default value is 50
	 */
	private Integer mQuantity = 50;

	/**
	 * Define if it generates paragraphs instead of words
	 * Default value is false  , it generates words
	 */
	private Boolean mUseParagraph = false;


	/**
	 *
	 */
	public LoremIpsumGenerator() {

	}



	@Override
	public String generate() throws GenerationException {

		if (getUseParagraph()){

			return LOREMIPSUM.getParagraphs(getQuantity());
		} else {

			return LOREMIPSUM.getWords(getQuantity());
		}
	}

	/* (non-Javadoc)
	 * @see fr.fayss.datagenerator.DataGenerator#configure(fr.fayss.datagenerator.DataConfiguration)
	 */
	@Override
	public void configure(DataConfiguration pDataconfig)
			throws PropertyConfigurationException {
		DataConfigurationTools.configure(this, pDataconfig);
	}

	/* (non-Javadoc)
	 * @see fr.fayss.datagenerator.DataGenerator#isConfigured()
	 */
	@Override
	public boolean isConfigured() {
		return (getQuantity() != null &&
				getQuantity() > 0);
	}

}
