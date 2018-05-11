package fr.fayss.datagenerator.sql;

import fr.fayss.datagenerator.DataFormatter;

/**
 * @author fayss
 *
 */
public interface ColumnGenerator <T> extends DataFormatter <T>{
	
	
	String DEFAULT_COLUMN_NAME ="DFLT_COL_NAME";
	
	String getColumnName();
	void setColumnName(String pColumnName);

}
