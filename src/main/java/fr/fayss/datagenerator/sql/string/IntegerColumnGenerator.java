package fr.fayss.datagenerator.sql.string;

import fr.fayss.datagenerator.sql.ColumnGenerator;
import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.StringUtils;

import fr.fayss.datagenerator.types.IntegerGenerator;

/**
 * @author fayss
 *
 */
public @Getter @Setter class IntegerColumnGenerator extends IntegerGenerator implements ColumnGenerator<Integer> {
	
	private Integer mValue;
	private String mColumnName  ;

	public IntegerColumnGenerator () {
		mColumnName = DEFAULT_COLUMN_NAME;
	}
	
	public IntegerColumnGenerator (String pColumnName) {
		mColumnName = pColumnName;
	}

	@Override
	public Integer generate() {
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
