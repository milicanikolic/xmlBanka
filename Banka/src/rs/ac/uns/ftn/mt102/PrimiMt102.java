
package rs.ac.uns.ftn.mt102;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for primiMt102 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="primiMt102">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://ftn.uns.ac.rs/mt102}mt102" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "primiMt102", propOrder = {
    "mt102"
})
public class PrimiMt102 {

    protected Mt102 mt102;

    /**
     * Gets the value of the mt102 property.
     * 
     * @return
     *     possible object is
     *     {@link Mt102 }
     *     
     */
    public Mt102 getMt102() {
        return mt102;
    }

    /**
     * Sets the value of the mt102 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Mt102 }
     *     
     */
    public void setMt102(Mt102 value) {
        this.mt102 = value;
    }

}
