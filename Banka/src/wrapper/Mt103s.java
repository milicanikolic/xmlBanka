package wrapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import rs.ac.uns.ftn.mt103.Mt103;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Mt103s implements Serializable {

	private static final long serialVersionUID = 8657375982796453352L;

	private List<Mt103> mt103s;

	public Mt103s() {
		super();
		mt103s = new ArrayList<Mt103>();
	}

	public List<Mt103> getMt103s() {
		return mt103s;
	}

	public void setMt103s(List<Mt103> mt103s) {
		this.mt103s = mt103s;
	}

	public void dodajMt103(Mt103 mt103) {
		mt103s.add(mt103);
	}

}
