
package generisani;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for mt103 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="mt103">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idPoruke" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="swiftBanDuznik" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="obracunskiRacBankeDuznik" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="obracunskiRacunBankePoverioca" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="swiftBanPoverioc" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="duznik" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="svrhaPlacanja" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="primalac" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="datumNaloga" type="{http://www.w3.org/2001/XMLSchema}date" form="qualified"/>
 *         &lt;element name="datumValute" type="{http://www.w3.org/2001/XMLSchema}date" form="qualified"/>
 *         &lt;element name="racunDuznik" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="modelZaduzenja" type="{http://www.w3.org/2001/XMLSchema}integer" form="qualified"/>
 *         &lt;element name="pozivNaBrojZaduzenja" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="racunPoverioca" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="modelOdobrenja" type="{http://www.w3.org/2001/XMLSchema}integer" form="qualified"/>
 *         &lt;element name="pozivNaBrOdobrenja" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
 *         &lt;element name="iznos" type="{http://www.w3.org/2001/XMLSchema}decimal" form="qualified"/>
 *         &lt;element name="sifraValute" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/>
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
@XmlType(name = "mt103", namespace = "http://ftn.uns.ac.rs/mt103", propOrder = {
    "idPoruke",
    "swiftBanDuznik",
    "obracunskiRacBankeDuznik",
    "obracunskiRacunBankePoverioca",
    "swiftBanPoverioc",
    "duznik",
    "svrhaPlacanja",
    "primalac",
    "datumNaloga",
    "datumValute",
    "racunDuznik",
    "modelZaduzenja",
    "pozivNaBrojZaduzenja",
    "racunPoverioca",
    "modelOdobrenja",
    "pozivNaBrOdobrenja",
    "iznos",
    "sifraValute"
})
public class Mt103 {

    @XmlElement(required = true)
    protected String idPoruke;
    @XmlElement(required = true)
    protected String swiftBanDuznik;
    @XmlElement(required = true)
    protected String obracunskiRacBankeDuznik;
    @XmlElement(required = true)
    protected String obracunskiRacunBankePoverioca;
    @XmlElement(required = true)
    protected String swiftBanPoverioc;
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
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumValute;
    @XmlElement(required = true)
    protected String racunDuznik;
    @XmlElement(required = true)
    protected BigInteger modelZaduzenja;
    @XmlElement(required = true)
    protected String pozivNaBrojZaduzenja;
    @XmlElement(required = true)
    protected String racunPoverioca;
    @XmlElement(required = true)
    protected BigInteger modelOdobrenja;
    @XmlElement(required = true)
    protected String pozivNaBrOdobrenja;
    @XmlElement(required = true)
    protected BigDecimal iznos;
    @XmlElement(required = true)
    protected String sifraValute;

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
     * Gets the value of the swiftBanDuznik property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSwiftBanDuznik() {
        return swiftBanDuznik;
    }

    /**
     * Sets the value of the swiftBanDuznik property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSwiftBanDuznik(String value) {
        this.swiftBanDuznik = value;
    }

    /**
     * Gets the value of the obracunskiRacBankeDuznik property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObracunskiRacBankeDuznik() {
        return obracunskiRacBankeDuznik;
    }

    /**
     * Sets the value of the obracunskiRacBankeDuznik property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObracunskiRacBankeDuznik(String value) {
        this.obracunskiRacBankeDuznik = value;
    }

    /**
     * Gets the value of the obracunskiRacunBankePoverioca property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObracunskiRacunBankePoverioca() {
        return obracunskiRacunBankePoverioca;
    }

    /**
     * Sets the value of the obracunskiRacunBankePoverioca property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObracunskiRacunBankePoverioca(String value) {
        this.obracunskiRacunBankePoverioca = value;
    }

    /**
     * Gets the value of the swiftBanPoverioc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSwiftBanPoverioc() {
        return swiftBanPoverioc;
    }

    /**
     * Sets the value of the swiftBanPoverioc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSwiftBanPoverioc(String value) {
        this.swiftBanPoverioc = value;
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
     * Gets the value of the pozivNaBrojZaduzenja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPozivNaBrojZaduzenja() {
        return pozivNaBrojZaduzenja;
    }

    /**
     * Sets the value of the pozivNaBrojZaduzenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPozivNaBrojZaduzenja(String value) {
        this.pozivNaBrojZaduzenja = value;
    }

    /**
     * Gets the value of the racunPoverioca property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRacunPoverioca() {
        return racunPoverioca;
    }

    /**
     * Sets the value of the racunPoverioca property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRacunPoverioca(String value) {
        this.racunPoverioca = value;
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

	public Mt103() {
		super();
	}

}
