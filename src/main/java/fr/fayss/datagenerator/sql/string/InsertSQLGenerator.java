/**
 * 
 */
package fr.fayss.datagenerator.sql.string;

import java.util.List;

import fr.fayss.datagenerator.sql.ColumnGenerator;
import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;
import fr.fayss.datagenerator.DataConfiguration;
import fr.fayss.datagenerator.DataConfigurationTools;
import fr.fayss.datagenerator.DataGenerator;
import fr.fayss.datagenerator.PropertyConfigurationException;
import fr.fayss.datagenerator.PropertyValueException;

/**
 * @author fayss
 * 
 * 
 * INSERT INTO TABLE_NAME (PROP1, PROP2, PROP3) VALUES
 * 		("stringValue", 12, 1),
 * 		("stringValue2", 13, -5),
 * 		("stringValue3", 14, 489);
 *
 */
public @Getter @Setter class  InsertSQLGenerator implements DataGenerator<String> {

	private String mTableName ;
	
	private Integer mQuantity = 5 ;


	private List <ColumnGenerator> mColumnGenerators ;

	@Override
	public String generate() {

		StringBuilder sb = new StringBuilder ();

		sb.append("INSERT INTO ").append(getTableName());
		addFormatedColumnNames (sb) ;
		sb.append("VALUE\n ") ;
		generateLine (sb, getQuantity());
		sb.append(";\n") ;
		
		return sb.toString();
	}
	
	private void generateLine (StringBuilder pSb, int pQuantity) {
		
		if(pQuantity <= 0) {
			throw new PropertyValueException("quantity must more be a positive number");
		}
		
		generateLine  (pSb);
		
		if (pQuantity > 1) {
			for (int i = 1; i < pQuantity; i ++ ) {

				pSb.append(",\n");
				generateLine  (pSb);
			}
		}
	}
	
	private void generateLine (StringBuilder pSb) {

		pSb.append(" (");
		
		ColumnGenerator colGen = mColumnGenerators.get(0);
		
		pSb.append(colGen.generate());

		if (mColumnGenerators.size() > 1) {
			for (ColumnGenerator colmun : getColumnGenerators()) {

				pSb.append(",");

				pSb.append(colmun.generate());
			}
		}
		pSb.append(") ");
		
	}
	

	private void addFormatedColumnNames (StringBuilder pSb){

		pSb.append(" (");
		
		ColumnGenerator colGen = mColumnGenerators.get(0);
		
		pSb.append(colGen.getColumnName());

		if (mColumnGenerators.size() > 1) {
			for (ColumnGenerator colmun : getColumnGenerators()) {

				pSb.append(",");

				pSb.append(colmun.getColumnName());
			}
		}
		pSb.append(") ");
	}

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
		return  (!StringUtils.isBlank(getTableName()) && mQuantity > 0)  ;
		
	}

}
