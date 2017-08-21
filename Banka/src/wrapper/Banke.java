package wrapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import banka.Banka;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Banke implements Serializable {

	private static final long serialVersionUID = -5909062746833758181L;

	private List<Banka> banka;

	public Banke() {
		super();
		banka = new ArrayList<Banka>();
	}

	public List<Banka> getBanke() {
		return banka;
	}

	public void setBanke(List<Banka> banke) {
		this.banka = banke;
	}

	public void dodajBanku(Banka b) {
		banka.add(b);
	}

}
