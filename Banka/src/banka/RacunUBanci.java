package banka;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RacunUBanci implements Serializable {

	private static final long serialVersionUID = -2975145461272216475L;
	private BigDecimal raspolozivoStanje;
	private BigDecimal rezervisanoStanje;

	public RacunUBanci() {
		raspolozivoStanje = new BigDecimal(0);
		rezervisanoStanje = new BigDecimal(0);

	}

	public RacunUBanci(BigDecimal raspolozivoStanje,
			BigDecimal rezervisanoStanje) {
		super();
		this.raspolozivoStanje = raspolozivoStanje;
		this.rezervisanoStanje = rezervisanoStanje;
	}

	public BigDecimal getRaspolozivoStanje() {
		return raspolozivoStanje;
	}

	public void setRaspolozivoStanje(BigDecimal raspolozivoStanje) {
		this.raspolozivoStanje = raspolozivoStanje;
	}

	public BigDecimal getUkupnoStanje() {
		return raspolozivoStanje.add(rezervisanoStanje);
	}

	public BigDecimal getRezervisanoStanje() {
		return rezervisanoStanje;
	}

	public void setRezervisanoStanje(BigDecimal rezervisanoStanje) {
		this.rezervisanoStanje = rezervisanoStanje;
	}

}
