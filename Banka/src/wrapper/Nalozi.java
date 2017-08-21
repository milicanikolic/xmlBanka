package wrapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import nalog.Nalog;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Nalozi  implements Serializable{


	private static final long serialVersionUID = 7272614184087783252L;
	
	private List<Nalog> nalozi;
	
	
	

	public Nalozi() {
		super();
		nalozi=new ArrayList<Nalog>();
	}

	public List<Nalog> getNalozi() {
		return nalozi;
	}

	public void setNalozi(List<Nalog> nalozi) {
		this.nalozi = nalozi;
	}
	
	

}
