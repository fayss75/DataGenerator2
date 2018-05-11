package fr.fayss.datagenerator.sql;

import lombok.Getter;
import lombok.Setter;
import fr.fayss.datagenerator.types.StringGenerator;

/**
 * @author fayss
 *
 */
public  @Getter @Setter class VarcharGenerator extends StringGenerator implements
		ColumnGenerator {
	
	private Object mValue;
	
	private String mColumnName  ;
	
	private String mQuote  = "\"";
	
	/**
	 * 
	 * Constructor
	 */
	public VarcharGenerator() {
		mColumnName = DEFAULT_COLUMN_NAME ;
	}
	
	public VarcharGenerator (String pColumnName) {
		mColumnName = pColumnName;
	}
	
	
	@Override
	public Object generate() {
		if (getValue() == null) {
			return getQuote() + super.generate() + getQuote() ;
		} else {
			return getValue();
		}
	}

}
