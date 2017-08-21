
package rs.ac.uns.ftn.mt103;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rs.ac.uns.ftn.mt103 package. 
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

    private final static QName _PrimiMt103_QNAME = new QName("http://ftn.uns.ac.rs/mt103", "primiMt103");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ac.uns.ftn.mt103
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PrimiMt103 }
     * 
     */
    public PrimiMt103 createPrimiMt103() {
        return new PrimiMt103();
    }

    /**
     * Create an instance of {@link Mt103 }
     * 
     */
    public Mt103 createMt103() {
        return new Mt103();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrimiMt103 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/mt103", name = "primiMt103")
    public JAXBElement<PrimiMt103> createPrimiMt103(PrimiMt103 value) {
        return new JAXBElement<PrimiMt103>(_PrimiMt103_QNAME, PrimiMt103 .class, null, value);
    }

}
