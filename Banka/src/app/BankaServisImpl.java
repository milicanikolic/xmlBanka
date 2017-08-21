package app;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import javax.jws.WebService;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Service;

import mt900.Mt900;
import mt910.Mt910;
import nalog.Nalog;
import presek.Presek;
import presek.StavkaPreseka;
import presek.ZaglavljePreseka;
import rs.ac.uns.ftn.cb.CentralnaBankaServis;
import rs.ac.uns.ftn.mt102.Mt102;
import rs.ac.uns.ftn.mt102.PojedinacnoPlacanjeMt102;
import rs.ac.uns.ftn.mt102.ZaglavljeMt102;
import rs.ac.uns.ftn.mt103.Mt103;
import wrapper.Nalozi;
import zahtevZaIzvod.ZahtevZaIzvod;
import banka.Banka;
import banka.Uplata;

import com.marklogic.client.eval.ServerEvaluationCall;

@WebService(portName = "Banka", serviceName = "BankaServis", targetNamespace = "http://ftn.uns.ac.rs/banka", endpointInterface = "app.BankaServis")
// wsdlLocation = "WEB-INF/wsdl/NalogZaUplatu.wsdl")
// name = "NalogZaUplatu")
public class BankaServisImpl implements BankaServis {
	

	private URL wsdlCB;

   private QName serviceNameCB = new QName("http://ftn.uns.ac.rs/CB","CentralnaBankaServis");
   private QName portNameCB = new QName("http://ftn.uns.ac.rs/CB","CentralnaBanka");
	

	public String obradiNalog(Nalog nalog) {
		System.out.println("MILICEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
		
		try {
			wsdlCB=new URL("http://SarvanLaptop:8083/CentralnaBanka/CentralnaBankaServis?wsdl");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String oznakaBankeDuznika = nalog.getRacunDuznik().substring(0, 3);
		System.out.println("racun duznika u banci sa oznakom "
				+ oznakaBankeDuznika);
		String upit1 = "for $x in doc('/content/banka.xml')/banke/banka where $x/oznakaBanke='"
				+ oznakaBankeDuznika + "' return $x";
		String odgovor1 = posaljiUpit(upit1);

		Banka bankaDuznika = null;
		try {
			bankaDuznika = unmarshaluj(Banka.class, new StreamSource(
					new StringReader(odgovor1)));
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		System.out.println("----------------------");
		System.out.println("BANKA DUZNIKA " + bankaDuznika.getNaziv());
		System.out.println("----------------------");

		String oznakaBankePrimaoca = nalog.getRacunPoverioca().substring(0, 3);
		System.out.println("racun primaoca u banci sa oznakom "
				+ oznakaBankePrimaoca);
		String upit2 = "for $x in doc('/content/banka.xml')/banke/banka where $x/oznakaBanke='"
				+ oznakaBankePrimaoca + "' return $x";
		String odgovor2 = posaljiUpit(upit2);

		Banka bankaPrimaoca = null;
		try {
			bankaPrimaoca = unmarshaluj(Banka.class, new StreamSource(
					new StringReader(odgovor2)));
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		System.out.println("----------------------");
		System.out.println("BANKA PRIMAOCA " + bankaPrimaoca.getNaziv());
		System.out.println("----------------------");

		if (nalog.getRacunPoverioca().substring(0, 3)
				.equals(nalog.getRacunDuznik().substring(0, 3))) {
			// firma A i firma B imaju racun u istoj banci

			BigDecimal iznos = bankaDuznika.getRacunIznos()
					.get(nalog.getRacunDuznik()).getRaspolozivoStanje();
			BigDecimal iznos2 = bankaPrimaoca.getRacunIznos()
					.get(nalog.getRacunPoverioca()).getRaspolozivoStanje();

			bankaDuznika.getRacunIznos().get(nalog.getRacunDuznik())
					.setRaspolozivoStanje(iznos.subtract(nalog.getIznos()));
			bankaDuznika.getRacunIznos().get(nalog.getRacunPoverioca())
					.setRaspolozivoStanje(iznos2.add(nalog.getIznos()));

			System.out.println("DUZNIKKK "
					+ bankaDuznika.getRacunIznos().get(nalog.getRacunDuznik())
							.getRaspolozivoStanje());
			System.out.println("POVVV "
					+ bankaDuznika.getRacunIznos()
							.get(nalog.getRacunPoverioca())
							.getRaspolozivoStanje());

			try {

				StringWriter sw1 = new StringWriter();
				marshaluj(
						bankaDuznika.getRacunIznos()
								.get(nalog.getRacunDuznik()), sw1);
				System.out.println("odgovooor " + sw1.toString());
				String kon1 = sw1.toString().substring(
						sw1.toString().indexOf("raspolozivoStanje") - 1,
						sw1.toString().indexOf("</racunUBanci>"));
				System.out.println("skraceno " + kon1);
				// UPISIVANJE NOVIH STANJA RACUNA U BAZU
				String upit3 = "xdmp:node-replace(doc('/content/banka.xml')/banke/banka[oznakaBanke='"
						+ bankaDuznika.getOznakaBanke()
						+ "']/racunIznos/entry[key='"
						+ nalog.getRacunDuznik()
						+ "']/value, <value>" + kon1 + "</value>)";
				posaljiUpit(upit3);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				StringWriter sw2 = new StringWriter();
				marshaluj(
						bankaDuznika.getRacunIznos().get(
								nalog.getRacunPoverioca()), sw2);
				String kon2 = sw2.toString().substring(
						sw2.toString().indexOf("raspolozivoStanje") - 1,
						sw2.toString().indexOf("</racunUBanci>"));

				String upit4 = "xdmp:node-replace(doc('/content/banka.xml')/banke/banka[oznakaBanke='"
						+ bankaPrimaoca.getOznakaBanke()
						+ "']/racunIznos/entry[key='"
						+ nalog.getRacunPoverioca()
						+ "']/value, <value>"
						+ kon2 + "</value>)";
				posaljiUpit(upit4);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// DODAJEM U MAPU UPLATA

			String upitPre1 = "for $x in doc('/content/uplata.xml')/uplate/uplata[racunPrimaoca='"
					+ nalog.getRacunPoverioca() + "'] return $x";

			String odgovorUplata = posaljiUpit(upitPre1);
			if (odgovorUplata == null) {

				Uplata novaUplata = new Uplata();
				novaUplata.setRacunPrimaoca(nalog.getRacunPoverioca());
				novaUplata.dodajNalog(nalog);

				// uises samo uplatu u bazu
				try {
					StringWriter sw = new StringWriter();
					marshaluj(novaUplata, sw);
					String kon = sw.toString().substring(
							sw.toString().indexOf("uplata") - 1,
							sw.toString().length());

					System.out.println("kon " + kon);
					String upit = "xdmp:node-insert-child(doc('/content/uplata.xml')/uplate,"
							+ kon + ");";
					posaljiUpit(upit);
					System.out.println(kon.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				System.out.println("VEC IMA UPLATU");
				Uplata uplata = null;
				try {
					uplata = unmarshaluj(Uplata.class, new StreamSource(
							new StringReader(odgovorUplata)));
					System.out.println("uplata racun primaoca "
							+ uplata.getRacunPrimaoca());
					uplata.dodajNalog(nalog);
					System.out.println("size " + uplata.getNalozi().size());
					// sacuva se nova uplata

					StringWriter sw = new StringWriter();
					marshaluj(uplata, sw);
					String kon = sw.toString().substring(
							sw.toString().indexOf("uplata") - 1,
							sw.toString().length());
					System.out.println("marsalovanje uplata " + kon);
					String upit = "xdmp:node-replace(doc('/content/uplata.xml')/uplate/uplata[racunPrimaoca='"
							+ nalog.getRacunPoverioca() + "']," + kon + ")";
					posaljiUpit(upit);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		} else { // NISU IZ ISTE BANKE

			int broj = nalog.getIznos().compareTo(new BigDecimal(250000));
			// zato sto compareTo vraca 0 ako je = ili 1 ako je vece
			if (nalog.isHitno() == true || broj >= 0) {
				// slucaj kad je hitan nalog i iznos veci od 250 000
				// !!!! proveri da li se ovako menja, ako ne, onda treba jos
				// setovati ove vrednosti!!!!!!
				bankaDuznika
						.getRacunIznos()
						.get(nalog.getRacunDuznik())
						.setRezervisanoStanje(
								bankaDuznika.getRacunIznos()
										.get(nalog.getRacunDuznik())
										.getRezervisanoStanje()
										.add(nalog.getIznos()));
				bankaDuznika
						.getRacunIznos()
						.get(nalog.getRacunDuznik())
						.setRaspolozivoStanje(
								bankaDuznika.getRacunIznos()
										.get(nalog.getRacunDuznik())
										.getRaspolozivoStanje()
										.subtract(nalog.getIznos()));

				try {

					StringWriter sw1 = new StringWriter();
					marshaluj(
							bankaDuznika.getRacunIznos().get(
									nalog.getRacunDuznik()), sw1);
					String kon1 = sw1.toString().substring(
							sw1.toString().indexOf("raspolozivoStanje") - 1,
							sw1.toString().indexOf("</racunUBanci>"));

					// UPISIVANJE NOVIH STANJA RACUNA U BAZU
					String upit3 = "xdmp:node-replace(doc('/content/banka.xml')/banke/banka[oznakaBanke='"
							+ bankaDuznika.getOznakaBanke()
							+ "']/racunIznos/entry[key='"
							+ nalog.getRacunDuznik()
							+ "']/value, <value>"
							+ kon1 + "</value>)";
					posaljiUpit(upit3);
				} catch (Exception e) {
					e.printStackTrace();
				}

				// ovA DVA MORAJU DA SE SACUVAJU U BAZI U BANCI DUZNIKA
				Mt103 mt103 = new Mt103();
				mt103.setDatumNaloga(nalog.getDatumNaloga());
				mt103.setDatumValute(nalog.getDatumValute());
				mt103.setDuznik(nalog.getDuznik());
				mt103.setIdPoruke(nalog.getIdPoruke());
				mt103.setIznos(nalog.getIznos());
				mt103.setModelOdobrenja(nalog.getModelOdobrenja());
				mt103.setModelZaduzenja(nalog.getModelZaduzenja());
				mt103.setObracunskiRacBankeDuznik(bankaDuznika
						.getObracunskiRacun());
		//		mt103.setObracunskiRacunBankePoverioca(bankaPrimaoca.getObracunskiRacun());
				mt103.setPozivNaBrOdobrenja(nalog.getPozivNaBrOdobrenja());
				mt103.setPozivNaBrojZaduzenja(nalog.getPozivNaBrZaduzenja());
				mt103.setPrimalac(nalog.getPrimalac());
				mt103.setRacunDuznik(nalog.getRacunDuznik());
				mt103.setRacunPoverioca(nalog.getRacunPoverioca());
				mt103.setSifraValute(nalog.getOznakaValute());
				mt103.setSvrhaPlacanja(nalog.getSvrhaPlacanja());
				mt103.setSwiftBanDuznik(bankaDuznika.getSwiftCode());
				mt103.setSwiftBanPoverioc(bankaPrimaoca.getSwiftCode());

				// TREBA DA SE SACUVA MT103 U BAZU
				try {
					StringWriter sw = new StringWriter();
					marshaluj(mt103, sw);
					String kon = sw.toString().substring(
							sw.toString().indexOf("mt103") - 1,
							sw.toString().length());
					String upit = "declare namespace mt103='http://ftn.uns.ac.rs/mt103';"
							+ " xdmp:node-insert-child(doc('/content/mt103.xml')/mt103:mt103S,"
							+ kon + ");";
					posaljiUpit(upit);
					System.out.println(kon.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}

				String upitPre1 = "for $x in doc('/content/uplata.xml')/uplate/uplata[racunPrimaoca='"
						+ nalog.getRacunPoverioca() + "'] return $x";

				String odgovorUplata = posaljiUpit(upitPre1);
				if (odgovorUplata == null) {

					Uplata novaUplata = new Uplata();
					novaUplata.setRacunPrimaoca(nalog.getRacunPoverioca());
					novaUplata.dodajNalog(nalog);

					// uises samo uplatu u bazu
					try {
						StringWriter sw = new StringWriter();
						marshaluj(novaUplata, sw);
						String kon = sw.toString().substring(
								sw.toString().indexOf("uplata") - 1,
								sw.toString().length());

						System.out.println("kon " + kon);
						String upit = "xdmp:node-insert-child(doc('/content/uplata.xml')/uplate,"
								+ kon + ");";
						posaljiUpit(upit);
						System.out.println(kon.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else {
					System.out.println("VEC IMA UPLATU");
					Uplata uplata = null;
					try {
						uplata = unmarshaluj(Uplata.class, new StreamSource(
								new StringReader(odgovorUplata)));
						System.out.println("uplata racun primaoca "
								+ uplata.getRacunPrimaoca());
						uplata.dodajNalog(nalog);
						System.out.println("size " + uplata.getNalozi().size());
						// sacuva se nova uplata

						StringWriter sw = new StringWriter();
						marshaluj(uplata, sw);
						String kon = sw.toString().substring(
								sw.toString().indexOf("uplata") - 1,
								sw.toString().length());
						System.out.println("marsalovanje uplata " + kon);
						String upit = "xdmp:node-replace(doc('/content/uplata.xml')/uplate/uplata[racunPrimaoca='"
								+ nalog.getRacunPoverioca() + "']," + kon + ")";
						posaljiUpit(upit);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				// TREBA DA SE POSALJE MT103 CENTRALNOJ BANCI
				
				Service service = Service.create(wsdlCB, serviceNameCB);
			      CentralnaBankaServis inter = service
			               .getPort(portNameCB, CentralnaBankaServis.class);

			       inter.primiMt103(mt103);

			} else if (broj < 0) {
				// CLEARING AND SETTLEMENT
				System.out.println("ISPIS PRe ekSPEsna");
				System.out.println(nalog.getRacunDuznik());
				System.out.println(bankaDuznika.getRacunIznos().get(nalog.getRacunDuznik()));
				System.out.println(nalog.getIznos());
				// SAD RZERVISEM PARE DUZNIKU
				BigDecimal novoRezStanje = bankaDuznika.getRacunIznos()
						.get(nalog.getRacunDuznik()).getRezervisanoStanje()
						.add(nalog.getIznos());
				bankaDuznika.getRacunIznos().get(nalog.getRacunDuznik())
						.setRezervisanoStanje(novoRezStanje);
				
				System.out.println("REZerVISANO StaNJE: " + bankaDuznika.getRacunIznos().get(nalog.getRacunDuznik()).getRezervisanoStanje());

				bankaDuznika
						.getRacunIznos()
						.get(nalog.getRacunDuznik())
						.setRaspolozivoStanje(
								bankaDuznika.getRacunIznos()
										.get(nalog.getRacunDuznik())
										.getRaspolozivoStanje()
										.subtract(nalog.getIznos()));

				// CUANJE NOVOG STANJA RACUN IZVNOS U BANCI DUZNIKA

				try {

					StringWriter sw1 = new StringWriter();
					marshaluj(
							bankaDuznika.getRacunIznos().get(
									nalog.getRacunDuznik()), sw1);
					String kon1 = sw1.toString().substring(
							sw1.toString().indexOf("raspolozivoStanje") - 1,
							sw1.toString().indexOf("</racunUBanci>"));

					// UPISIVANJE NOVIH STANJA RACUNA U BAZU
					String upit3 = "xdmp:node-replace(doc('/content/banka.xml')/banke/banka[oznakaBanke='"
							+ bankaDuznika.getOznakaBanke()
							+ "']/racunIznos/entry[key='"
							+ nalog.getRacunDuznik()
							+ "']/value, <value>"
							+ kon1 + "</value>)";
					posaljiUpit(upit3);
				} catch (Exception e) {
					e.printStackTrace();
				}

				// iscitati sve mt102 (ako je ista banka duznika tada proveravam
				// koliko placanja ima toj mt102 ako ih ima 3 treba da se
				// posalje taj mt102
				// i da se izbrise iz baze, ako ih nema 3 napravis novo 102
				// setujes i dodas novo u listu, opet vidis je l ih ima 3 ako
				// ima saljes
				// i brises ako nema upises ceo mt u bazu)

				String upitPre = "declare namespace mt102='http://ftn.uns.ac.rs/mt102';"
						+ " for $x in doc('/content/mt102.xml')/mt102:mt102S/mt102:mt102[mt102:zaglavljeMt102/mt102:swiftBankaDuznik='"
						+ bankaDuznika.getSwiftCode() + "'] return $x";

				String odgovor = posaljiUpit(upitPre);
				if (odgovor == null) {
					System.out.println("nema nijedan mt102, pravi ceo nov");
					// ne postoji, radi sta treba
					// napravi sve

					Mt102 mt102 = new Mt102();

					ZaglavljeMt102 zaglavljeMt102 = new ZaglavljeMt102();
					zaglavljeMt102.setDatum(nalog.getDatumNaloga());
					zaglavljeMt102.setDatumValute(nalog.getDatumValute());
					zaglavljeMt102.setIdPoruke(UUID.randomUUID().toString());// nzm
																				// je
																				// l
																				// treba
																				// da
																				// se
																				// uzme
																				// sa
																				// naloga
																				// ili
																				// ovaj
																				// nov
					// zaglavljeMt102.setObracunskiRacBanDuznik(nalog.get);
					zaglavljeMt102.setSifraValute(nalog.getOznakaValute());
					zaglavljeMt102.getUkupanIznos().add(nalog.getIznos()); // sa
																			// svakog
																			// naloga
																			// dodajem
																			// iznos
					zaglavljeMt102.setSwiftBankaDuznik(bankaDuznika
							.getSwiftCode());
					zaglavljeMt102.setSwiftBankaPoverioc(bankaPrimaoca
							.getSwiftCode());

					PojedinacnoPlacanjeMt102 pojedinacnoMt2 = new PojedinacnoPlacanjeMt102(
							nalog.getIdPoruke(), nalog.getDuznik(),
							nalog.getSvrhaPlacanja(), nalog.getPrimalac(),
							nalog.getDatumNaloga(), nalog.getRacunDuznik(),
							nalog.getModelZaduzenja(),
							nalog.getPozivNaBrZaduzenja(),
							nalog.getRacunPoverioca(),
							nalog.getModelOdobrenja(),
							nalog.getPozivNaBrOdobrenja(), nalog.getIznos(),
							nalog.getOznakaValute());

					mt102.setZaglavljeMt102(zaglavljeMt102);
					mt102.getPojedinacnoPlacanjeMt102().add(pojedinacnoMt2);

					try {
						StringWriter sw = new StringWriter();
						marshaluj(mt102, sw);
						String kon = sw.toString().substring(
								sw.toString().indexOf("mt102") - 1,
								sw.toString().length());
						String upit = "declare namespace mt102='http://ftn.uns.ac.rs/mt102';"
								+ " xdmp:node-insert-child(doc('/content/mt102.xml')/mt102:mt102S,"
								+ kon + ");";
						posaljiUpit(upit);
						System.out.println(kon.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

				else {
					// vratio mi je taj MT102

					Mt102 mt102 = null;
					try {
						mt102 = unmarshaluj(Mt102.class, new StreamSource(
								new StringReader(odgovor)));
						System.out
								.println("postoji takav mt i vratio ga je sa racunom poverioca ");
					} catch (JAXBException e) {
						e.printStackTrace();
					}

					if (mt102.getPojedinacnoPlacanjeMt102().size() == 3) {
						// poslati mt102
						System.out
								.println("Nasao MT102 i on ima tri pojedinacna u sebi");

						String upit = "declare namespace mt102='http://ftn.uns.ac.rs/mt102';"
								+ " xdmp:node-delete(doc('/content/mt102.xml')/mt102:mt102S/mt102:mt102[mt102:zaglavljeMt102/mt102:idPoruke='"
								+ mt102.getZaglavljeMt102().getIdPoruke()
								+ "'])";
						posaljiUpit(upit);

					} else {
						System.out
								.println("Nasao MT102 i ina razlicito od 3 pojedinacnih");
						PojedinacnoPlacanjeMt102 pojedinacnoMt2 = new PojedinacnoPlacanjeMt102(
								nalog.getIdPoruke(), nalog.getDuznik(),
								nalog.getSvrhaPlacanja(), nalog.getPrimalac(),
								nalog.getDatumNaloga(), nalog.getRacunDuznik(),
								nalog.getModelZaduzenja(),
								nalog.getPozivNaBrZaduzenja(),
								nalog.getDuznik(), nalog.getModelOdobrenja(),
								nalog.getPozivNaBrOdobrenja(),
								nalog.getIznos(), nalog.getOznakaValute());
						// mt102.getPojedinacnoPlacanjeMt102().add(pojedinacnoMt2);
						mt102.dodajPlacanje(pojedinacnoMt2);
						System.out.println("Dodao pojedinacno,sa ima "
								+ mt102.getPojedinacnoPlacanjeMt102().size());
						if (mt102.getPojedinacnoPlacanjeMt102().size() == 3) {
							// poslati mt102
							System.out.println("treba da briseee");
							String upit = "declare namespace mt102='http://ftn.uns.ac.rs/mt102';"
									+ "xdmp:node-delete(doc('/content/mt102.xml')/mt102:mt102S/mt102:mt102[mt102:zaglavljeMt102/mt102:idPoruke='"
									+ mt102.getZaglavljeMt102().getIdPoruke()
									+ "'])";
							posaljiUpit(upit);

						} else {
							System.out
									.println("I dalje nema tri sad cuva MT102 u bazu");
							try {
								StringWriter sw = new StringWriter();
								marshaluj(mt102, sw);
								String kon = sw.toString().substring(
										sw.toString().indexOf("mt102") - 1,
										sw.toString().length());
								String upit = "declare namespace mt102='http://ftn.uns.ac.rs/mt102';"
										+ " xdmp:node-replace(doc('/content/mt102.xml')/mt102:mt102S/mt102:mt102[mt102:zaglavljeMt102/mt102:idPoruke='"
										+ mt102.getZaglavljeMt102()
												.getIdPoruke()
										+ "'],"
										+ kon
										+ ")";
								System.out.println(kon);
								posaljiUpit(upit);
							} catch (Exception e) {
								e.printStackTrace();
							}

						}
					}

				}

				String upitPre1 = "for $x in doc('/content/uplata.xml')/uplate/uplata[racunPrimaoca='"
						+ nalog.getRacunPoverioca() + "'] return $x";

				String odgovorUplata = posaljiUpit(upitPre1);
				if (odgovorUplata == null) {

					Uplata novaUplata = new Uplata();
					novaUplata.setRacunPrimaoca(nalog.getRacunPoverioca());
					novaUplata.dodajNalog(nalog);

					// uises samo uplatu u bazu
					try {
						StringWriter sw = new StringWriter();
						marshaluj(novaUplata, sw);
						String kon = sw.toString().substring(
								sw.toString().indexOf("uplata") - 1,
								sw.toString().length());
						System.out.println("kon " + kon);
						String upit = "xdmp:node-insert-child(doc('/content/uplata.xml')/uplate,"
								+ kon + ");";
						posaljiUpit(upit);
						System.out.println(kon.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else {
					System.out.println("VEC IMA UPLATU");
					Uplata uplata = null;
					try {
						uplata = unmarshaluj(Uplata.class, new StreamSource(
								new StringReader(odgovorUplata)));
						System.out.println("uplata racun primaoca "
								+ uplata.getRacunPrimaoca());
						uplata.dodajNalog(nalog);
						System.out.println("size " + uplata.getNalozi().size());
						// sacuva se nova uplata

						StringWriter sw = new StringWriter();
						marshaluj(uplata, sw);
						String kon = sw.toString().substring(
								sw.toString().indexOf("uplata") - 1,
								sw.toString().length());
						System.out.println("marsalovanje uplata " + kon);
						String upit = "xdmp:node-replace(doc('/content/uplata.xml')/uplate/uplata[racunPrimaoca='"
								+ nalog.getRacunPoverioca() + "']," + kon + ")";
						posaljiUpit(upit);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			}

		}
		return "Evo ti glupi string";
	}

	@Override
	public void primiMt900(Mt900 mt900) {
		// ISCITAJ BANKU IZvBAZE,PO OBRACUNSKOM RACUNU BANKE DUYNIKA
		// MOZDA NE TREBA DA SE CITA IZ BANKE NEGO GORE DA SE IZVUCE
		try {
			wsdlCB=new URL("http://SarvanLaptop:8083/CentralnaBanka/CentralnaBankaServis?wsdl");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("-----------------");
		System.out.println("BANKA PRIMILA MT900");
		System.out.println("-----------------");

		String upit1 = "for $x in doc('/content/banka.xml')/banke/banka where $x/swiftCode='"
				+ mt900.getSwiftBanDuznik() + "' return $x";
		String odgovor1 = posaljiUpit(upit1);

		Banka bankaDuznika = null;
		try {
			bankaDuznika = unmarshaluj(Banka.class, new StreamSource(
					new StringReader(odgovor1)));
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		System.out.println("----------------------");
		System.out.println("BANKA DUZNIKA U MT900" + bankaDuznika.getNaziv());
		System.out.println("----------------------");

		// uzmi iz baze nalog po idNaloga
		String idNaloga = mt900.getIdPorukeNaloga();
		String upit = "declare namespace nal='http://ftn.uns.ac.rs/nalog';" + "for $x in doc('/content/uplata.xml')/uplate/uplata/nalozi[nal:idPoruke='"
				+ idNaloga + "'] return $x"; 
		String odgovor = posaljiUpit(upit);

		Nalog nal = null;
		try {
			nal = unmarshaluj(Nalog.class, new StreamSource(new StringReader(
					odgovor)));
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		String brojRacunaDuznik = nal.getRacunDuznik();

		System.out.println("--------------------");
		System.out.println("BANKA DUZNIKA PRE NEGO STO JOJ VRATIMO PARE: " + bankaDuznika.getIznosObracunskiRacun());
		System.out.println("--------------------");
		
		// vracam banci rezervisane pare, posto ce CB da skine banci sa racuna
		// iznos sa naloga.

		BigDecimal noviIznos = bankaDuznika.getIznosObracunskiRacun().add(
				bankaDuznika.getRacunIznos().get(brojRacunaDuznik)
						.getRezervisanoStanje());
		bankaDuznika.setIznosObracunskiRacun(noviIznos);
		
		// mislim da treba da se update-uje racun od banke, jer sam menjala
		// iznos
		
		System.out.println("--------------------");
		System.out.println("BANKA DUZNIKA NAKON STO JOJ VRATIMO PARE: " + bankaDuznika.getIznosObracunskiRacun());
		System.out.println("--------------------");

		try {

			// UPISIVANJE NOVIH STANJA RACUNA U BAZU
			String upit3 = "xdmp:node-replace(doc('/content/banka.xml')/banke/banka[oznakaBanke='"
					+ bankaDuznika.getOznakaBanke()
					+ "']/iznosObracunskiRacun, <iznosObracunskiRacun> "
					+ noviIznos + "</iznosObracunskiRacun>);";
			posaljiUpit(upit3);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// SAD SKIDAM IZNOS SA RACUNA DUZNIKA TAKO STO MU UZMEM REZERVISANO
		bankaDuznika.getRacunIznos().get(brojRacunaDuznik)
				.setRezervisanoStanje(new BigDecimal(0));
		// UPISI NOVO RACUN IZNOS U BANKUDUYNIKA NA NJEGOVO MESTO U MAPI PO
		// NJEGOVOM RACUNU
		
		System.out.println("--------------------------");
		System.out.println("VRACAMO REZERVISANO STANJE DUZNIKA NA: " + bankaDuznika.getRacunIznos().get(brojRacunaDuznik).getRezervisanoStanje());
		System.out.println("--------------------------");
		try {

			StringWriter sw1 = new StringWriter();
			marshaluj(bankaDuznika.getRacunIznos().get(nal.getRacunDuznik()),
					sw1);
			String kon1 = sw1.toString().substring(
					sw1.toString().indexOf("raspolozivoStanje") - 1,
					sw1.toString().indexOf("</racunUBanci>"));

			// UPISIVANJE NOVIH STANJA RACUNA U BAZU
			String upit3 = "xdmp:node-replace(doc('/content/banka.xml')/banke/banka[oznakaBanke='"
					+ bankaDuznika.getOznakaBanke()
					+ "']/racunIznos/entry[key='"
					+ nal.getRacunDuznik()
					+ "']/value, <value>" + kon1 + "</value>)";
			posaljiUpit(upit3);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void odobriSredstva(Mt103 mt103, Mt910 mt910) {
		// IZ BAYE UZMI BANKU PO OBRACUNSKOM RACUNU BANKE POVERIOCA IZ MT910
		try {
			wsdlCB=new URL("http://SarvanLaptop:8083/CentralnaBanka/CentralnaBankaServis?wsdl");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String obracunskiRacBanPoverioca = mt910.getObracunskiRacBanPoverioc();
		
		System.out.println("------------------------");
		System.out.println("USAO U ODOBRi SREDSTVA");
		System.out.println("------------------------");
		
		String upit1 = "for $x in doc('/content/banka.xml')/banke/banka where $x/obracunskiRacun='"
				+ obracunskiRacBanPoverioca + "' return $x";
		String odgovor1 = posaljiUpit(upit1);

		Banka bankaPoverioca = null;
		try {
			bankaPoverioca = unmarshaluj(Banka.class, new StreamSource(
					new StringReader(odgovor1)));
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		System.out.println("----------------------");
		System.out.println("BANKA POVERIOCA " + bankaPoverioca.getNaziv());
		System.out.println("----------------------");

		System.out.println("-----------------------");
		System.out.println("POVERIOC PRE PRIMANJA PARA: " + bankaPoverioca.getRacunIznos().get(mt103.getRacunPoverioca()).getRaspolozivoStanje());
		System.out.println("-----------------------");
		BigDecimal noviIznos = bankaPoverioca.getRacunIznos()
				.get(mt103.getRacunPoverioca()).getRaspolozivoStanje()
				.add(mt103.getIznos());
		bankaPoverioca.getRacunIznos().get(mt103.getRacunPoverioca())
				.setRaspolozivoStanje(noviIznos);
		// upisi u bazu racuna u banci
		System.out.println("-----------------------");
		System.out.println("POVERIOC POSLE PRIMANJA PARA: " + bankaPoverioca.getRacunIznos().get(mt103.getRacunPoverioca()).getRaspolozivoStanje());
		System.out.println("-----------------------");

		try {

			StringWriter sw1 = new StringWriter();
			marshaluj(
					bankaPoverioca.getRacunIznos().get(
							mt103.getRacunPoverioca()), sw1);
			String kon1 = sw1.toString().substring(
					sw1.toString().indexOf("raspolozivoStanje") - 1,
					sw1.toString().indexOf("</racunUBanci>"));
			
		
			
			// UPISIVANJE NOVIH STANJA RACUNA U BAZU
			String upit3 = "xdmp:node-replace(doc('/content/banka.xml')/banke/banka[oznakaBanke='"
					+ bankaPoverioca.getOznakaBanke()
					+ "']/racunIznos/entry[key='"
					+ mt103.getRacunPoverioca()
					+ "']/value, <value>"
					+ kon1 + "</value>)";
			
			posaljiUpit(upit3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("------------------------------");
		System.out.println("BANKA sA PARAMA od FIRME: " + bankaPoverioca.getIznosObracunskiRacun());
		System.out.println("------------------------------");
		// sad smanjujem pare sa bankinog racuna, posto je druga firma uplatila
		// na bankin racun
		BigDecimal noviObracunski = bankaPoverioca.getIznosObracunskiRacun()
				.subtract(mt103.getIznos());
		bankaPoverioca.setIznosObracunskiRacun(noviObracunski);

		// mislim da treba da se update-uje racun od banke, jer sam menjala
		// iznos
		System.out.println("---------------------");
		System.out.println("BANKA sA SKINUTIM PARAMA FIRME: " + bankaPoverioca.getIznosObracunskiRacun());
		System.out.println("------------------------------");

		try {

			// UPISIVANJE NOVIH STANJA RACUNA U BAZU
			String upit3 = "xdmp:node-replace(doc('/content/banka.xml')/banke/banka[oznakaBanke='"
					+ bankaPoverioca.getOznakaBanke()
					+ "']/iznosObracunskiRacun, <iznosObracunskiRacun> "
					+ noviObracunski + "</iznosObracunskiRacun>);";
			posaljiUpit(upit3);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Presek obradiZahtevZaIzvod(ZahtevZaIzvod zahtevZaIzvod) {
		try {
			wsdlCB=new URL("http://SarvanLaptop:8083/CentralnaBanka/CentralnaBankaServis?wsdl");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int brojStavki = 3;
		// IZ BAZE UZMI SVE NALOGE ZA UPLATU GDE JE BROJ RACUNA PRIMAOCA ISTI
		// KAO I BROJ NA ZAHTEVU ZA IZVOD I GDE JE DATUM ISTI
		// KAO ID DATUM NA ZAHTEVU. //NAPRAVILA SAM HASH MAPU U BANCI, SA SVIM
		// UPLATAMA, VIDI JE L IMA SMISLA, I AKO JE
		// OK, ONDA TREBA DODAVATI PO KODU U TU HASH MAPU KAD GOD PRIMA ILI
		// SALJE NALOG.->DODALA

		String brojRacunaFirme = zahtevZaIzvod.getBrojRacuna();
		String oznakaBanke = brojRacunaFirme.substring(0, 3);

		String upit1 = "for $x in doc('/content/banka.xml')/banke/banka where $x/oznakaBanke='"
				+ oznakaBanke + "' return $x";
		String odgovor1 = posaljiUpit(upit1);

		Banka izvodIzBanke = null;
		try {
			izvodIzBanke = unmarshaluj(Banka.class, new StreamSource(
					new StringReader(odgovor1)));
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		System.out.println("----------------------");
		System.out.println("BANKA DUZNIKA " + izvodIzBanke.getNaziv());
		System.out.println("----------------------");

		String upitPre1 = "for $x in doc('/content/uplata.xml')/uplate/uplata[racunPrimaoca='"
				+ zahtevZaIzvod.getBrojRacuna()
				+ "']/nalozi[datumNaloga= "
				+ zahtevZaIzvod.getDatum() + "] return $x";
		System.out.println("PRE SLANJA");
		String odgovorUplata = posaljiUpit(upitPre1);
		System.out.println("POSle slanjaaa");
		Nalozi listaNaloga = null;
		
		if (odgovorUplata == null) {

			// sta ako nema????
			listaNaloga = new Nalozi();
			return null;

		} else {

			try {
				listaNaloga = unmarshaluj(Nalozi.class, new StreamSource(
						new StringReader(odgovorUplata)));
			} catch (JAXBException e) {
				e.printStackTrace();
			}
			
			Presek presek = new Presek();
			StavkaPreseka stavka;

			int rbrPreseka = zahtevZaIzvod.getRbrPreseka().intValue() - 1;

			for (int i = rbrPreseka * brojStavki; i < i + brojStavki; i++) {
				if (listaNaloga.getNalozi().size() >= i) {
					Nalog n = listaNaloga.getNalozi().get(i);
					stavka = new StavkaPreseka(n.getDuznik(), n.getSvrhaPlacanja(),
							n.getPrimalac(), n.getDatumNaloga(),
							n.getDatumValute(), n.getRacunDuznik(),
							n.getModelZaduzenja(), n.getPozivNaBrZaduzenja(),
							n.getRacunPoverioca(), n.getModelOdobrenja(),
							n.getPozivNaBrOdobrenja(), n.getIznos(), "a");
					presek.getStavkaPreseka().add(stavka);
				}
			}

			ZaglavljePreseka zaglavlje = new ZaglavljePreseka();
			zaglavlje.setBrojPreseka(zahtevZaIzvod.getRbrPreseka());
			zaglavlje.setBrojRacuna(zahtevZaIzvod.getBrojRacuna());
			zaglavlje.setDatumNaloga(zahtevZaIzvod.getDatum());
			// NE MOGU OVO DA SETUJEM STARO I NOVO STANJE, BEZ BANKE???
			// ??????????????

			presek.setZaglavljePreseka(zaglavlje);

			return presek;

		}

	
	}

	public String posaljiUpit(String upit) {
		ServerEvaluationCall poziv = StartApp.getClient().newServerEval();
		String odgovor = poziv.xquery(upit).evalAs(String.class);
		return odgovor;
	}

	public static <T> T unmarshaluj(Class<T> cl, Source s) throws JAXBException {
		JAXBContext ctx = JAXBContext.newInstance(cl);
		Unmarshaller u = ctx.createUnmarshaller();
		return u.unmarshal(s, cl).getValue();
	}

	public static <T> void marshaluj(T obj, Writer wr) throws JAXBException {
		JAXBContext ctx = JAXBContext.newInstance(obj.getClass());
		Marshaller m = ctx.createMarshaller();
		m.marshal(obj, wr);
	}
}
