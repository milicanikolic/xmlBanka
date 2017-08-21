
package rs.ac.uns.ftn.mt102;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for zaglavljeMt102 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="zaglavljeMt102">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idPoruke" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="swiftBankaDuznik" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="obracunskiRacBanDuznik" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="swiftBankaPoverioc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="obracunskiRacunBanPoverioc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ukupanIznos" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="sifraValute" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="datumValute" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="datum" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "zaglavljeMt102", propOrder = {
    "idPoruke",
    "swiftBankaDuznik",
    "obracunskiRacBanDuznik",
    "swiftBankaPoverioc",
    "obracunskiRacunBanPoverioc",
    "ukupanIznos",
    "sifraValute",
    "datumValute",
    "datum"
})
public class ZaglavljeMt102 {

    @XmlElement(required = true)
    protected String idPoruke;
    @XmlElement(required = true)
    protected String swiftBankaDuznik;
    @XmlElement(required = true)
    protected String obracunskiRacBanDuznik;
    @XmlElement(required = true)
    protected String swiftBankaPoverioc;
    @XmlElement(required = true)
    protected String obracunskiRacunBanPoverioc;
    @XmlElement(required = true)
    protected BigDecimal ukupanIznos;
    @XmlElement(required = true)
    protected String sifraValute;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumValute;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datum;

    /**
     * Gets the value of the idPoruke property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPoruke() {
        return idPoruke;
    }

    /**
     * Sets the value of the idPoruke property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPoruke(String value) {
        this.idPoruke = value;
    }

    /**
     * Gets the value of the swiftBankaDuznik property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSwiftBankaDuznik() {
        return swiftBankaDuznik;
    }

    /**
     * Sets the value of the swiftBankaDuznik property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSwiftBankaDuznik(String value) {
        this.swiftBankaDuznik = value;
    }

    /**
     * Gets the value of the obracunskiRacBanDuznik property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObracunskiRacBanDuznik() {
        return obracunskiRacBanDuznik;
    }

    /**
     * Sets the value of the obracunskiRacBanDuznik property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObracunskiRacBanDuznik(String value) {
        this.obracunskiRacBanDuznik = value;
    }

    /**
     * Gets the value of the swiftBankaPoverioc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSwiftBankaPoverioc() {
        return swiftBankaPoverioc;
    }

    /**
     * Sets the value of the swiftBankaPoverioc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSwiftBankaPoverioc(String value) {
        this.swiftBankaPoverioc = value;
    }

    /**
     * Gets the value of the obracunskiRacunBanPoverioc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObracunskiRacunBanPoverioc() {
        return obracunskiRacunBanPoverioc;
    }

    /**
     * Sets the value of the obracunskiRacunBanPoverioc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObracunskiRacunBanPoverioc(String value) {
        this.obracunskiRacunBanPoverioc = value;
    }

    /**
     * Gets the value of the ukupanIznos property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUkupanIznos() {
        return ukupanIznos;
    }

    /**
     * Sets the value of the ukupanIznos property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUkupanIznos(BigDecimal value) {
        this.ukupanIznos = value;
    }

    /**
     * Gets the value of the sifraValute property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSifraValute() {
        return sifraValute;
    }

    /**
     * Sets the value of the sifraValute property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSifraValute(String value) {
        this.sifraValute = value;
    }

    /**
     * Gets the value of the datumValute property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumValute() {
        return datumValute;
    }

    /**
     * Sets the value of the datumValute property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumValute(XMLGregorianCalendar value) {
        this.datumValute = value;
    }

    /**
     * Gets the value of the datum property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatum() {
        return datum;
    }

    /**
     * Sets the value of the datum property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatum(XMLGregorianCalendar value) {
        this.datum = value;
    }

}
