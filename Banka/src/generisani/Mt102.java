
package generisani;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for mt102 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="mt102">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="zaglavljeMt102" type="{http://ftn.uns.ac.rs/mt102}zaglavljeMt102"/>
 *         &lt;element name="pojedinacnoPlacanjeMt102" type="{http://ftn.uns.ac.rs/mt102}pojedinacnoPlacanjeMt102" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mt102", propOrder = {
    "zaglavljeMt102",
    "pojedinacnoPlacanjeMt102"
})
public class Mt102 {

    @XmlElement(required = true)
    protected ZaglavljeMt102 zaglavljeMt102;
    @XmlElement(required = true)
    protected List<PojedinacnoPlacanjeMt102> pojedinacnoPlacanjeMt102;

    /**
     * Gets the value of the zaglavljeMt102 property.
     * 
     * @return
     *     possible object is
     *     {@link ZaglavljeMt102 }
     *     
     */
    public ZaglavljeMt102 getZaglavljeMt102() {
        return zaglavljeMt102;
    }

    /**
     * Sets the value of the zaglavljeMt102 property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZaglavljeMt102 }
     *     
     */
    public void setZaglavljeMt102(ZaglavljeMt102 value) {
        this.zaglavljeMt102 = value;
    }

    /**
     * Gets the value of the pojedinacnoPlacanjeMt102 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pojedinacnoPlacanjeMt102 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPojedinacnoPlacanjeMt102().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PojedinacnoPlacanjeMt102 }
     * 
     * 
     */
    public List<PojedinacnoPlacanjeMt102> getPojedinacnoPlacanjeMt102() {
        if (pojedinacnoPlacanjeMt102 == null) {
            pojedinacnoPlacanjeMt102 = new ArrayList<PojedinacnoPlacanjeMt102>();
        }
        return this.pojedinacnoPlacanjeMt102;
    }

	public Mt102() {
		super();
	}

}
