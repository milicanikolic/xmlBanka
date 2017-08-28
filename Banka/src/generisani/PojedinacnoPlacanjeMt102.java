
package generisani;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for pojedinacnoPlacanjeMt102 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="pojedinacnoPlacanjeMt102">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idNaloga" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="duznik" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="svrhaPlacanja" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="primalac" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="datumNaloga" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="racunDuznik" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="modelZaduzenja" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="pozivNaBrZaduzenja" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="racunPoverioc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="modelOdobrenja" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="pozivNaBrOdobrenja" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="iznos" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="sifraValute" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pojedinacnoPlacanjeMt102", propOrder = {
    "idNaloga",
    "duznik",
    "svrhaPlacanja",
    "primalac",
    "datumNaloga",
    "racunDuznik",
    "modelZaduzenja",
    "pozivNaBrZaduzenja",
    "racunPoverioc",
    "modelOdobrenja",
    "pozivNaBrOdobrenja",
    "iznos",
    "sifraValute"
})
public class PojedinacnoPlacanjeMt102 {

    @XmlElement(required = true)
    protected String idNaloga;
    @XmlElement(required = true)
    protected String duznik;
    @XmlElement(required = true)
    protected String svrhaPlacanja;
    @XmlElement(required = true)
    protected String primalac;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumNaloga;
    @XmlElement(required = true)
    protected String racunDuznik;
    @XmlElement(required = true)
    protected BigInteger modelZaduzenja;
    @XmlElement(required = true)
    protected String pozivNaBrZaduzenja;
    @XmlElement(required = true)
    protected String racunPoverioc;
    @XmlElement(required = true)
    protected BigInteger modelOdobrenja;
    @XmlElement(required = true)
    protected String pozivNaBrOdobrenja;
    @XmlElement(required = true)
    protected BigDecimal iznos;
    @XmlElement(required = true)
    protected String sifraValute;

    /**
     * Gets the value of the idNaloga property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdNaloga() {
        return idNaloga;
    }

    /**
     * Sets the value of the idNaloga property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdNaloga(String value) {
        this.idNaloga = value;
    }

    /**
     * Gets the value of the duznik property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDuznik() {
        return duznik;
    }

    /**
     * Sets the value of the duznik property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDuznik(String value) {
        this.duznik = value;
    }

    /**
     * Gets the value of the svrhaPlacanja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSvrhaPlacanja() {
        return svrhaPlacanja;
    }

    /**
     * Sets the value of the svrhaPlacanja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSvrhaPlacanja(String value) {
        this.svrhaPlacanja = value;
    }

    /**
     * Gets the value of the primalac property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimalac() {
        return primalac;
    }

    /**
     * Sets the value of the primalac property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimalac(String value) {
        this.primalac = value;
    }

    /**
     * Gets the value of the datumNaloga property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumNaloga() {
        return datumNaloga;
    }

    /**
     * Sets the value of the datumNaloga property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumNaloga(XMLGregorianCalendar value) {
        this.datumNaloga = value;
    }

    /**
     * Gets the value of the racunDuznik property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRacunDuznik() {
        return racunDuznik;
    }

    /**
     * Sets the value of the racunDuznik property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRacunDuznik(String value) {
        this.racunDuznik = value;
    }

    /**
     * Gets the value of the modelZaduzenja property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getModelZaduzenja() {
        return modelZaduzenja;
    }

    /**
     * Sets the value of the modelZaduzenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setModelZaduzenja(BigInteger value) {
        this.modelZaduzenja = value;
    }

    /**
     * Gets the value of the pozivNaBrZaduzenja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPozivNaBrZaduzenja() {
        return pozivNaBrZaduzenja;
    }

    /**
     * Sets the value of the pozivNaBrZaduzenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPozivNaBrZaduzenja(String value) {
        this.pozivNaBrZaduzenja = value;
    }

    /**
     * Gets the value of the racunPoverioc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRacunPoverioc() {
        return racunPoverioc;
    }

    /**
     * Sets the value of the racunPoverioc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRacunPoverioc(String value) {
        this.racunPoverioc = value;
    }

    /**
     * Gets the value of the modelOdobrenja property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getModelOdobrenja() {
        return modelOdobrenja;
    }

    /**
     * Sets the value of the modelOdobrenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setModelOdobrenja(BigInteger value) {
        this.modelOdobrenja = value;
    }

    /**
     * Gets the value of the pozivNaBrOdobrenja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPozivNaBrOdobrenja() {
        return pozivNaBrOdobrenja;
    }

    /**
     * Sets the value of the pozivNaBrOdobrenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPozivNaBrOdobrenja(String value) {
        this.pozivNaBrOdobrenja = value;
    }

    /**
     * Gets the value of the iznos property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIznos() {
        return iznos;
    }

    /**
     * Sets the value of the iznos property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIznos(BigDecimal value) {
        this.iznos = value;
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

	public PojedinacnoPlacanjeMt102(String idNaloga, String duznik,
			String svrhaPlacanja, String primalac,
			XMLGregorianCalendar datumNaloga, String racunDuznik,
			BigInteger modelZaduzenja, String pozivNaBrZaduzenja,
			String racunPoverioc, BigInteger modelOdobrenja,
			String pozivNaBrOdobrenja, BigDecimal iznos, String sifraValute) {
		super();
		this.idNaloga = idNaloga;
		this.duznik = duznik;
		this.svrhaPlacanja = svrhaPlacanja;
		this.primalac = primalac;
		this.datumNaloga = datumNaloga;
		this.racunDuznik = racunDuznik;
		this.modelZaduzenja = modelZaduzenja;
		this.pozivNaBrZaduzenja = pozivNaBrZaduzenja;
		this.racunPoverioc = racunPoverioc;
		this.modelOdobrenja = modelOdobrenja;
		this.pozivNaBrOdobrenja = pozivNaBrOdobrenja;
		this.iznos = iznos;
		this.sifraValute = sifraValute;
	}

	public PojedinacnoPlacanjeMt102() {
		super();
	}

    
}
