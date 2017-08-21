
package rs.ac.uns.ftn.mt102;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rs.ac.uns.ftn.mt102 package. 
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

    private final static QName _Mt102_QNAME = new QName("http://ftn.uns.ac.rs/mt102", "mt102");
    private final static QName _PrimiMt102_QNAME = new QName("http://ftn.uns.ac.rs/mt102", "primiMt102");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ac.uns.ftn.mt102
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Mt102 }
     * 
     */
    public Mt102 createMt102() {
        return new Mt102();
    }

    /**
     * Create an instance of {@link PrimiMt102 }
     * 
     */
    public PrimiMt102 createPrimiMt102() {
        return new PrimiMt102();
    }

    /**
     * Create an instance of {@link ZaglavljeMt102 }
     * 
     */
    public ZaglavljeMt102 createZaglavljeMt102() {
        return new ZaglavljeMt102();
    }

    /**
     * Create an instance of {@link PojedinacnoPlacanjeMt102 }
     * 
     */
    public PojedinacnoPlacanjeMt102 createPojedinacnoPlacanjeMt102() {
        return new PojedinacnoPlacanjeMt102();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Mt102 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/mt102", name = "mt102")
    public JAXBElement<Mt102> createMt102(Mt102 value) {
        return new JAXBElement<Mt102>(_Mt102_QNAME, Mt102 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrimiMt102 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/mt102", name = "primiMt102")
    public JAXBElement<PrimiMt102> createPrimiMt102(PrimiMt102 value) {
        return new JAXBElement<PrimiMt102>(_PrimiMt102_QNAME, PrimiMt102 .class, null, value);
    }

}
