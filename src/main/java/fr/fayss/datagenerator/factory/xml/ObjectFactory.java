
package fr.fayss.datagenerator.factory.xml;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.fayss.datagenerator.atg.xml package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.fayss.datagenerator.atg.xml
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DataConfig }
     * 
     */
    public DataConfig createDataConfig() {
        return new DataConfig();
    }

    /**
     * Create an instance of {@link DataConfig.MainGenerators }
     * 
     */
    public DataConfig.MainGenerators createDataConfigMainGenerators() {
        return new DataConfig.MainGenerators();
    }

    /**
     * Create an instance of {@link DataConfig.DataGenerator }
     * 
     */
    public DataConfig.DataGenerator createDataConfigDataGenerator() {
        return new DataConfig.DataGenerator();
    }

    /**
     * Create an instance of {@link PropertyValue }
     * 
     */
    public PropertyValue createPropertyValue() {
        return new PropertyValue();
    }

    /**
     * Create an instance of {@link PropertyConfig }
     * 
     */
    public PropertyConfig createPropertyConfig() {
        return new PropertyConfig();
    }

}
