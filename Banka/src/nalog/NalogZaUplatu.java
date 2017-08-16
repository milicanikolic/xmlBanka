package nalog;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


@WebService(
		targetNamespace = "http://ftn.uns.ac.rs/nalog"
		)
@SOAPBinding(style =Style.DOCUMENT, use = Use.LITERAL, parameterStyle = ParameterStyle.WRAPPED)
public interface NalogZaUplatu {
	
	@RequestWrapper(className="nalog.data.NalogRequest")
	@ResponseWrapper(className="nalog.data.StringResponse")
	public String obradiNalog(@WebParam(name="nalog")Nalog nalog);//, targetNamespace="http://ftn.uns.ac.rs/nalog")Nalog nalog);
	
	/*@RequestWrapper(className="nalog.data.Mt900Request")
	public void primiMt900(@WebParam(name="mt900")Mt900 mt900);
	

	public void odobriSredstva(Mt103 mt103, Mt910 mt910);
	
	@RequestWrapper(className="nalog.data.ZahtevZaIzvodRequest")
	@ResponseWrapper(className="nalog.data.ZahtevZaIzvodResponse")
	public Presek obradiZahtevZaIzvod(@WebParam(name="zahtevZaIzvod")ZahtevZaIzvod zahtevZaIzvod);*/
}
