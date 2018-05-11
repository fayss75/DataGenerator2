package fr.fayss.datagenerator.sql;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.StringUtils;

import fr.fayss.datagenerator.types.IntegerGenerator;

/**
 * @author fayss
 *
 */
public @Getter @Setter class IntegerColumnGenerator extends IntegerGenerator implements ColumnGenerator {
	
	private Object mValue;
	private String mColumnName  ;

	public IntegerColumnGenerator () {
		mColumnName = DEFAULT_COLUMN_NAME;
	}
	
	public IntegerColumnGenerator (String pColumnName) {
		mColumnName = pColumnName;
	}

	@Override
	public Object generate() {
		if (mValue  == null) {
			return super.generate();
		} else {
			return mValue;
		}
	}
	
	@Override
	public boolean isConfigured() {
		if (mValue == null || StringUtils.isBlank(mColumnName)){
			return false ;
		} else {
			return super.isConfigured() ;
		}
	}

}
