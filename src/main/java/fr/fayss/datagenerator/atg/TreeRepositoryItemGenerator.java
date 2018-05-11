package fr.fayss.datagenerator.atg;

import java.util.ArrayList;
import java.util.List;

import fr.fayss.datagenerator.InternalException;
import fr.fayss.datagenerator.PropertyValueException;
import lombok.Getter;
import lombok.Setter;

/**
 * Generate hierarchical Tree of repositoryItems from the same type.
 * A repository item contains a list of child repository items, and its child could contain also child repository Items

 * @author fayss
 *
 */
public @Getter @Setter class TreeRepositoryItemGenerator extends RepositoryItemGenerator {

	private static final Class<? extends PropertyGenerator> DEFAULT_CHILD_PROP_GENERATOR = PropertyGenerator.class;
	
	/** Define the  property generator for the property that contains the ids of child items*/
	private PropertyGenerator mChildProperyGenerator ;

	/** 
	 * Define the number of direct child items 
	 * Default value is 3 
	 */
	private Integer mNbChild = 3;
	
	/** 
	 * Define the depth of the tree 
	 * Default value is 3
	 */
	private Integer mDepth = 3 ;
	
	
	@Override
	public boolean isConfigured() {

		if (mNbChild == null || mNbChild < 0 || mDepth == null || mDepth < 0 || mChildProperyGenerator == null ){
			return false;  
		} else {
			return super.isConfigured();
		}
	}


	/**
	 * Constructor
	 * 
	 * @param pItemName is the item descriptor name
	 * @param pChildPropertyName is the name of the child property
	 * @param propertyList contains other property
	 * @param pIdGenerator an @ItemDescriptorIdGenerator for the id 
	 */
	public TreeRepositoryItemGenerator(String pItemName,String pChildPropertyName,
			List<PropertyGenerator> propertyList,
			ItemDescriptorIdGenerator pIdGenerator) {

		super(pItemName, propertyList, pIdGenerator);

		try {
			mChildProperyGenerator = DEFAULT_CHILD_PROP_GENERATOR.newInstance();
			mChildProperyGenerator.setPropertyName(pChildPropertyName);

		} catch (InstantiationException | IllegalAccessException ex) {
			throw new InternalException (ex);
		}
	}

	/**
	 * Constructor
	 * 
	 * @param pItemName is the item descriptor name
	 * @param pChildPropertyName is the name of the child property
	 * @param propertyList contains other property
	 */
	public TreeRepositoryItemGenerator(String pItemName,String pChildPropertyName,
			List<PropertyGenerator> propertyList) {

		super(pItemName, propertyList);

		try {
			mChildProperyGenerator = DEFAULT_CHILD_PROP_GENERATOR.newInstance();
			mChildProperyGenerator.setPropertyName(pChildPropertyName);

		} catch (InstantiationException | IllegalAccessException ex) {
			throw new InternalException (ex);
		}
	}


	/**
	 * Format a list of ids using the separator "," and return it as a String
	 * @param ids list of ids to format
	 * @return a formated string of ids
	 */
	private String formatIds (List <String> ids) {
		StringBuilder sb = new StringBuilder();
		if (ids.size() > 0) ;
		sb.append(ids.get(0));

		for (int i = 1; i < ids.size(); i++) {
			sb.append(",");
			sb.append(ids.get(i));
		}

		return sb.toString();

	}

	@Override
	public String generate() {

		StringBuilder sb = new StringBuilder();

		if (getDepth() == 0){
			return super.generate();
		} else {

			List <String> ids = generateIds(getNbChild());
			getChildProperyGenerator().setValue(formatIds(ids));

			super.generateStartAddItemTag(sb);
			super.generateProperty(sb);
			sb.append(getChildProperyGenerator().generate());
			super.generateEndAddItemTag(sb);


			// I try first to use the same class for sub item
			// if it doesn't work properly i have to change the code to instanciate new class
			
			//			HierarchicalRepposiotryItemGenerator subItem = new HierarchicalRepposiotryItemGenerator(
			//					getItemName(),getChildProperyGenerator().getPropertyName(),
			//					getPropertyList(),getIdGenerator());
			//			
			//			subItem.setDepth(getDepth() - 1);
			this.setDepth(getDepth() - 1);


			for (String childId : ids) {

				//subItem.setValue(childId);
				this.setValue(childId);

				//sb.append(subItem.generate());
				sb.append(this.generate());
			}

		}

		return sb.toString();
	}


	/**
	 * Generates a list of ids. 
	 * CAUTION : this method do not garranty that all ids are different.
	 * Depending of the {@link ItemDescriptorIdGenerator} implemehtation, the same id could be generated several times
	 * @param  pQuantity of id generated, must be a positive number
	 * @return A list a ids
	 * @throws PropertyValueException if pQuantity is not greater than zero 
	 */
	private List <String> generateIds (int pQuantity) {
		if (pQuantity <= 0){
			throw new PropertyValueException("Quantity must be a positive value, actual value:" + pQuantity);
		}

		List <String> ids = new ArrayList <String>(pQuantity);

		for (int i = 0 ; i < pQuantity ; i++ ){
			ids.add((String)getIdGenerator().generate());
		}

		return ids;
	}

}
