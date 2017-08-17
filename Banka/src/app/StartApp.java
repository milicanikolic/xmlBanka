package app;

import java.io.File;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import banka.Banka;
import banka.Banke;
import banka.RacunUBanci;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.DatabaseClientFactory.Authentication;

@Startup
@Singleton
public class StartApp {

	private static DatabaseClient client;
	private static String trenutniPort;

	public StartApp() {

	}

	@PostConstruct
	public void init() {

		System.out.println("POKRENUO BANKU");
//		uzmiPort();
		otvoriKonekciju();
	
		Banka b1=new Banka("Continental", "111", "111487596325478512", "CONARS22", new BigDecimal("800000"));
		b1.dodajRacunFirme("f", "111123456789987621");
		RacunUBanci rb1=new RacunUBanci(new BigDecimal("320000"), new BigDecimal("0"));
		b1.dodajIznosRacuna("111123456789987621", rb1);
		
		Banka b2=new Banka("Vojvodjanska", "222", "222487596874856976", "VBUBRS22", new BigDecimal("550000"));
		b2.dodajRacunFirme("firmaB", "22298765432112307");
		
		RacunUBanci rb2=new RacunUBanci(new BigDecimal("120000"), new BigDecimal("0"));
		b2.dodajIznosRacuna("22298765432112307", rb2);
		//b2.dodajIznosRacuna("22298765432112307", rb2);
		
		Banke b=new Banke();
		b.dodajBanku(b1);
		b.dodajBanku(b2);
		
		 Marshaller marshallerFirma;
		try {
			JAXBContext contextFirma = JAXBContext.newInstance(Banke.class);
			
			marshallerFirma = contextFirma.createMarshaller();
			marshallerFirma.marshal(b, new File("C:/Users/NINAM/Desktop/content/banka.xml"));
			
		} catch (JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

/*	public void uzmiPort() {
		BufferedReader br1 = null;
		BufferedReader br2 = null;
		String portPart = "";
		String offset = "";

		try {

			String sCurrentLine;

			br1 = new BufferedReader(new FileReader(fileName));

			while ((sCurrentLine = br1.readLine()) != null) {

				if (sCurrentLine.contains("jboss.http.port")) {

					String[] foundPort = sCurrentLine.split(":");
					portPart = (foundPort[1].split("}"))[0];

					break;

				}

			}

			br2 = new BufferedReader(new FileReader(fileName));

			while ((sCurrentLine = br2.readLine()) != null) {

				if (sCurrentLine.contains("jboss.socket.binding.port-offset")) {

					String[] foundOffset = sCurrentLine.split(":");
					offset = (foundOffset[1].split("}"))[0];

					break;

				}

			}

			int portPartInt = Integer.parseInt(portPart);
			int offsetInt = Integer.parseInt(offset);
			int portInt = portPartInt + offsetInt;
			trenutniPort = String.valueOf(portInt);

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br1 != null)
					br1.close();

				if (br2 != null)
					br2.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	} */

	@PreDestroy
	public void exit() {
		zatvoriKonekciju();
	}

	public static void otvoriKonekciju() {
		client = DatabaseClientFactory.newClient("localhost", 8003, "admin", "admin", Authentication.DIGEST);

	}

	public static void zatvoriKonekciju() {
		client.release();
	}

	public static DatabaseClient getClient() {
		return client;
	}

	public static void setClient(DatabaseClient client) {
		StartApp.client = client;
	}

	public static String getTrenutniPort() {
		return trenutniPort;
	}

	public static void setTrenutniPort(String trenutniPort) {
		StartApp.trenutniPort = trenutniPort;
	}
}