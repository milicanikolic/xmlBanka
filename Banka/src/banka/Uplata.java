package banka;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import nalog.Nalog;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Uplata implements Serializable {

	private static final long serialVersionUID = -422459348864485424L;
	private String racunPrimaoca;
	private List<Nalog> nalozi;

	public Uplata() {
		super();
		nalozi = new ArrayList<Nalog>();
	}

	public Uplata(String racunPrimaoca, List<Nalog> nalozi) {
		super();
		this.racunPrimaoca = racunPrimaoca;
		this.nalozi = nalozi;
	}

	public String getRacunPrimaoca() {
		return racunPrimaoca;
	}

	public void setRacunPrimaoca(String racunPrimaoca) {
		this.racunPrimaoca = racunPrimaoca;
	}

	public List<Nalog> getNalozi() {
		return nalozi;
	}

	public void setNalozi(List<Nalog> nalozi) {
		this.nalozi = nalozi;
	}

	public void dodajNalog(Nalog n) {
		nalozi.add(n);
	}

}
