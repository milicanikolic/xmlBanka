package nalog;

import javax.jws.WebService;

import mt102.Mt102;
import mt102.ZaglavljeMt102;

@WebService(portName = "NalogZaUplatu", 
			serviceName = "NalogZaUplatuService", 
			targetNamespace = "http://ftn.uns.ac.rs/nalog", 
			endpointInterface = "nalog.NalogZaUplatu")
			//wsdlLocation = "WEB-INF/wsdl/NalogZaUplatu.wsdl") 
			//name = "NalogZaUplatu")
public class NalogZaUplatuImpl implements NalogZaUplatu{

	public void obradiNalog(Nalog nalog) {
		System.out.println("ljk");
		
	
	}

}
