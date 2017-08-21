//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.08.13 at 06:05:39 PM CEST 
//

package presek;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for presek complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="presek">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://ftn.uns.ac.rs/presek}zaglavljePreseka"/>
 *         &lt;element ref="{http://ftn.uns.ac.rs/presek}stavkaPreseka" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "presek", propOrder = { "zaglavljePreseka", "stavkaPreseka" })
public class Presek {

	@XmlElement(required = true)
	protected ZaglavljePreseka zaglavljePreseka;
	@XmlElement(required = true)
	protected List<StavkaPreseka> stavkaPreseka;

	/**
	 * Gets the value of the zaglavljePreseka property.
	 * 
	 * @return possible object is {@link ZaglavljePreseka }
	 * 
	 */
	public ZaglavljePreseka getZaglavljePreseka() {
		return zaglavljePreseka;
	}

	/**
	 * Sets the value of the zaglavljePreseka property.
	 * 
	 * @param value
	 *            allowed object is {@link ZaglavljePreseka }
	 * 
	 */
	public void setZaglavljePreseka(ZaglavljePreseka value) {
		this.zaglavljePreseka = value;
	}

	/**
	 * Gets the value of the stavkaPreseka property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the stavkaPreseka property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getStavkaPreseka().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link StavkaPreseka }
	 * 
	 * 
	 */
	public List<StavkaPreseka> getStavkaPreseka() {
		if (stavkaPreseka == null) {
			stavkaPreseka = new ArrayList<StavkaPreseka>();
		}
		return this.stavkaPreseka;
	}

}
