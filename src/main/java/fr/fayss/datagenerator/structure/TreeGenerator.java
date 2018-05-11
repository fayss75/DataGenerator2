package fr.fayss.datagenerator.structure;

import lombok.Getter;
import lombok.Setter;
import fr.fayss.datagenerator.DataConfiguration;
import fr.fayss.datagenerator.DataGenerator;
import fr.fayss.datagenerator.GenerationBuffer;
import fr.fayss.datagenerator.PropertyConfigurationException;
import fr.fayss.datagenerator.ReferenceDataGenerator;

public @Getter @Setter class TreeGenerator implements DataGenerator<String> {

	public static final String DATA_KEY_PREFIX = "TreeGenerator" ;
	public static final String CURRENT_DEPTH_PREFIX ="TreeGenerator.currentDepth.";
	public static final String POSITION_PREFIX ="TreeGenerator.position.";



	private String mCurrentDepthUniqueKey ;
	private String mPositionUniqueKey ;


	private ReferenceDataGenerator mDataGenerator ;
	
	/** 
	 * Define the number of direct child items 
	 * Default vale is 3 
	 */
	private Integer mNbChild = 3;

	/** 
	 * Define the depth of the tree 
	 * Default value is 3*/
	private Integer mDepth = 3 ;


	public TreeGenerator(ReferenceDataGenerator pDataGenerator, Integer pNbchild, Integer pDepth ){
		mDataGenerator = pDataGenerator; 
		mNbChild = pNbchild ;
		mDepth = pDepth ;
		
		mCurrentDepthUniqueKey = CURRENT_DEPTH_PREFIX + pDataGenerator.getReferenceKey() ;
		mPositionUniqueKey = POSITION_PREFIX + pDataGenerator.getReferenceKey() ;
	}

	public TreeGenerator( ){
		
	}
	
	public TreeGenerator(ReferenceDataGenerator pDataGenerator ){
		mDataGenerator = pDataGenerator; 
		mCurrentDepthUniqueKey = CURRENT_DEPTH_PREFIX + pDataGenerator.getReferenceKey() ;
		mPositionUniqueKey = POSITION_PREFIX + pDataGenerator.getReferenceKey() ;

	}


	@Override
	public String generate() {
		Object result =getgenerationBuffer().getData(CURRENT_DEPTH_PREFIX);



		if (result != null){

			Integer currentDepth = (Integer)getgenerationBuffer().getData(CURRENT_DEPTH_PREFIX);
			Integer currentChild =(Integer)getgenerationBuffer().getData(POSITION_PREFIX);


			if (currentChild ==  Math.pow(mNbChild, mDepth - currentDepth 	) ){
				getgenerationBuffer().setData(CURRENT_DEPTH_PREFIX, currentDepth - 1);
				getgenerationBuffer().setData(POSITION_PREFIX, 1);
			} else {
				getgenerationBuffer().setData(POSITION_PREFIX,currentChild+ 1);
			}

			if (currentDepth > 1 ){
				SimpleCollectionGenerator collectionGen = new SimpleCollectionGenerator (mDataGenerator,mNbChild) ;
				return collectionGen.generate();
			} 


		} else {
			getgenerationBuffer().setData(CURRENT_DEPTH_PREFIX, mDepth - 1);
			getgenerationBuffer().setData(POSITION_PREFIX, 1);
			SimpleCollectionGenerator collectionGen = new SimpleCollectionGenerator (mDataGenerator,mNbChild) ;
			return collectionGen.generate();
		}
		return "";

	}

	@Override
	public void configure(DataConfiguration pDataconfig)
			throws PropertyConfigurationException {
		// TODO Auto-generated method stub

	}

	private GenerationBuffer getgenerationBuffer () {
		return GenerationBuffer.getInstance();
	}

	/* (non-Javadoc)
	 * @see fr.fayss.datagenerator.DataGenerator#isConfigured()
	 */
	@Override
	public boolean isConfigured() {
		return getDataGenerator() != null &&
				getNbChild() != null && 
				getNbChild() > 0 && 
				getDepth() != null &&
				getDepth() > 0;
	}

}
