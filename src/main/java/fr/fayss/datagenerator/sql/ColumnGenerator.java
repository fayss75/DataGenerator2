/**
 * 
 */
package fr.fayss.datagenerator.sql;

import fr.fayss.datagenerator.DataFormatter;

/**
 * @author fayss
 *
 */
public interface ColumnGenerator extends DataFormatter{
	
	
	public static final String DEFAULT_COLUMN_NAME ="DFLT_COL_NAME";
	
	public String getColumnName();
	public void setColumnName(String pColumnName);

}
