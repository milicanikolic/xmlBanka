package wrapper;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import banka.Uplata;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Uplate implements Serializable {

	private static final long serialVersionUID = -7532928321586734460L;
	private List<Uplata> uplate;

	public Uplate() {
		super();
	}

	public List<Uplata> getUplate() {
		return uplate;
	}

	public void setUplate(List<Uplata> uplate) {
		this.uplate = uplate;
	}

	public void dodajUplatu(Uplata upl) {
		uplate.add(upl);
	}

}
