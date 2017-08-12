package nalog;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import mt102.Mt102;

@WebService(
		targetNamespace = "http://ftn.uns.ac.rs/nalog"
		)
@SOAPBinding(style =Style.DOCUMENT, use = Use.LITERAL, parameterStyle = ParameterStyle.WRAPPED)
public interface NalogZaUplatu {
	
	@RequestWrapper(className="nalog.data.ObradiNalog")
	@ResponseWrapper(className="nalog.data.ObradiNalogResponse")
	public void obradiNalog(@WebParam(name="nalog")Nalog nalog);//, targetNamespace="http://ftn.uns.ac.rs/nalog")Nalog nalog);

}
