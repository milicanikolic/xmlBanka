
package rs.ac.uns.ftn.cb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rs.ac.uns.ftn.cb package. 
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

    private final static QName _PrimiMt102Response_QNAME = new QName("http://ftn.uns.ac.rs/CB", "primiMt102Response");
    private final static QName _PrimiMt103Response_QNAME = new QName("http://ftn.uns.ac.rs/CB", "primiMt103Response");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ac.uns.ftn.cb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PrimiMt102Response }
     * 
     */
    public PrimiMt102Response createPrimiMt102Response() {
        return new PrimiMt102Response();
    }

    /**
     * Create an instance of {@link PrimiMt103Response }
     * 
     */
    public PrimiMt103Response createPrimiMt103Response() {
        return new PrimiMt103Response();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrimiMt102Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/CB", name = "primiMt102Response")
    public JAXBElement<PrimiMt102Response> createPrimiMt102Response(PrimiMt102Response value) {
        return new JAXBElement<PrimiMt102Response>(_PrimiMt102Response_QNAME, PrimiMt102Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrimiMt103Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/CB", name = "primiMt103Response")
    public JAXBElement<PrimiMt103Response> createPrimiMt103Response(PrimiMt103Response value) {
        return new JAXBElement<PrimiMt103Response>(_PrimiMt103Response_QNAME, PrimiMt103Response.class, null, value);
    }

}
