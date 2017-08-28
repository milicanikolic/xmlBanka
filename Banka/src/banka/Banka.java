package banka;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Banka implements Serializable {

	private static final long serialVersionUID = 1L;

	private String port;
	private String oznakaBanke;// predstavlja ona prve 3 cifre u racunu firme,
								// po kojima moze da se
	// vidi u kojoj banci firma ima otvoren racun
	private String naziv;
	private String obracunskiRacun;
	private String swiftCode;
	private HashMap<String, String> racunFirme;
	public HashMap<String, RacunUBanci> racunIznos; // broj racuna firme, iznosi
													// na racunu

	private BigDecimal iznosObracunskiRacun;

	public Banka() {
		super();
		racunFirme = new HashMap<String, String>();
		racunIznos = new HashMap<String, RacunUBanci>();

	}

	public Banka(String naziv, String oznakaBanke, String obracunskiRacun,
			String swiftCode, BigDecimal iznosObracunskiRacun) {
		super();
		racunFirme = new HashMap<String, String>();
		racunIznos = new HashMap<String, RacunUBanci>();

		this.naziv = naziv;
		this.oznakaBanke = oznakaBanke;
		this.obracunskiRacun = obracunskiRacun;
		this.swiftCode = swiftCode;
		this.iznosObracunskiRacun = iznosObracunskiRacun;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getSwiftCode() {
		return swiftCode;
	}

	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}

	public String getObracunskiRacun() {
		return obracunskiRacun;
	}

	public void setObracunskiRacun(String obracunskiRacun) {
		this.obracunskiRacun = obracunskiRacun;
	}

	public HashMap<String, RacunUBanci> getRacunIznos() {
		return racunIznos;
	}

	public void setRacunIznos(HashMap<String, RacunUBanci> racunIznos) {
		this.racunIznos = racunIznos;
	}

	public String getOznakaBanke() {
		return oznakaBanke;
	}

	public void setOznakaBanke(String oznakaBanke) {
		this.oznakaBanke = oznakaBanke;
	}

	public HashMap<String, String> getRacunFirme() {
		return racunFirme;
	}

	public void setRacunFirme(HashMap<String, String> racunFirme) {
		this.racunFirme = racunFirme;
	}

	public BigDecimal getIznosObracunskiRacun() {
		return iznosObracunskiRacun;
	}

	public void setIznosObracunskiRacun(BigDecimal iznosObracunskiRacun) {
		this.iznosObracunskiRacun = iznosObracunskiRacun;
	}

	public void dodajRacunFirme(String firma, String brRacuna) {
		racunFirme.put(firma, brRacuna);
	}

	public void dodajIznosRacuna(String brRacuna, RacunUBanci racun) {
		racunIznos.put(brRacuna, racun);
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

}