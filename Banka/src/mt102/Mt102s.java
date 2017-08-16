package mt102;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Mt102s implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5024793907653061900L;
	
	private List<Mt102> mt102s;
	
	public Mt102s() {
		super();
		mt102s=new ArrayList<Mt102>();
	}

	public List<Mt102> getMt102s() {
		return mt102s;
	}

	public void setMt102s(List<Mt102> mt102s) {
		this.mt102s = mt102s;
	}
	
	public void dodajMt102(Mt102 mt102) {
		mt102s.add(mt102);
	}

}
