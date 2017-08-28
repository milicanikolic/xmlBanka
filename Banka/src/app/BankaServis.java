package app;


import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import mt103imt910.MT103I910;
import mt900.Mt900;
import nalog.Nalog;
import presek.Presek;
import zahtevZaIzvod.ZahtevZaIzvod;

@WebService(targetNamespace = "http://ftn.uns.ac.rs/banka")
@XmlSeeAlso({mt103imt910.ObjectFactory.class, mt910.ObjectFactory.class})
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL, parameterStyle = ParameterStyle.WRAPPED)
public interface BankaServis {

	@RequestWrapper(className = "nalog.data.NalogRequest", targetNamespace = "http://ftn.uns.ac.rs/nalog")
	// @ResponseWrapper(className="nalog.data.StringResponse")
	public String obradiNalog(
			@WebParam(name = "nalog", targetNamespace = "http://ftn.uns.ac.rs/nalog") Nalog nalog);// ,
																									// targetNamespace="http://ftn.uns.ac.rs/nalog")Nalog
																									// nalog);

	@RequestWrapper(className = "nalog.data.Mt900Request")
	public void primiMt900(@WebParam(name = "mt900", targetNamespace = "http://ftn.uns.ac.rs/mt900") Mt900 mt900);
	
	//@RequestWrapper(localName="MT103I910" ,className = "mt103imt910.MT103I910",targetNamespace="http://ftn.uns.ac.rs/mt103i910")
	@RequestWrapper(className="nalog.data.Mt103I910Requet")
	public void odobriSredstva(@WebParam(name="MT103i910",targetNamespace="http://ftn.uns.ac.rs/mt103i910") MT103I910 MT103I910);
	//public void odobriSredstva(@WebParam(name="mt103", targetNamespace="http://ftn.uns.ac.rs/mt103i910") Mt103 mt103, @WebParam(name="mt910", targetNamespace="http://ftn.uns.ac.rs/mt103i910") Mt910 mt910);

	@RequestWrapper(className = "nalog.data.ZahtevZaIzvodRequest")
	@ResponseWrapper(className = "nalog.data.ZahtevZaIzvodResponse")
	public Presek obradiZahtevZaIzvod(
			@WebParam(name = "zahtevZaIzvod", targetNamespace = "http://ftn.uns.ac.rs/zahtev") ZahtevZaIzvod zahtevZaIzvod);

}
