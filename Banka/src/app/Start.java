package app;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import presek.Presek;
import presek.StavkaPreseka;
import presek.ZaglavljePreseka;
import zahtevZaIzvod.ZahtevZaIzvod;
import mt102.Mt102;
import mt102.PojedinacnoPlacanjeMt102;
import mt102.ZaglavljeMt102;
import mt103.Mt103;
import mt900.Mt900;
import mt910.Mt910;
import nalog.Nalog;
import banka.Banka;
import banka.RacunUBanci;


public class Start {

	public Start() {

	}

	public void init() {

		Nalog nalog = new Nalog();
		nalog.setRacunDuznik("12345");
		nalog.setRacunPoverioca("33333");
		nalog.setIznos(new BigDecimal(1000));

		obradiNalog(nalog);
	}

	public void obradiNalog(Nalog nalog) {

		//IZ BAZE POKUPITI BANKU DUYNIKA(po racunu duznika, tj po prve 3 cifre),
		//TJ. BANKU KOJA JE PRIMILA NALOG
		
		String oznakaBankePrimaoca=nalog.getRacunPoverioca().substring(0, 3);
		String oznakaBankeDuznika=nalog.getRacunDuznik().substring(0, 3);
		
		Banka bankaDuznika=null;
		Banka bankaPrimaoca=null;
		
		
		if (nalog.getRacunPoverioca().substring(0, 3)
				.equals(nalog.getRacunDuznik().substring(0, 3))) {
			// firma A i firma B imaju racun u istoj banci

			BigDecimal iznos = bankaDuznika.getRacunIznos()
					.get(nalog.getRacunDuznik()).getRaspolozivoStanje();
			BigDecimal iznos2 = bankaDuznika.getRacunIznos()
					.get(nalog.getRacunPoverioca()).getRaspolozivoStanje();
			RacunUBanci racunDuznik = new RacunUBanci();
			racunDuznik.setRaspolozivoStanje(iznos.subtract(nalog.getIznos()));

			RacunUBanci racunPoverioc = new RacunUBanci();
			racunPoverioc.setRaspolozivoStanje(iznos2.add(nalog.getIznos()));
			
			
			//UPISIVANJE NOVIH STANJA RACUNA U BAZU
			bankaDuznika.getRacunIznos().put(nalog.getRacunDuznik(), racunDuznik);
			bankaDuznika.getRacunIznos().put(nalog.getRacunPoverioca(), racunPoverioc);
			System.out.println("DUZNIKKK "
					+ bankaDuznika.getRacunIznos().get(nalog.getRacunDuznik())
							.getRaspolozivoStanje());
			System.out.println("POVVV "
					+ bankaDuznika.getRacunIznos().get(nalog.getRacunPoverioca())
							.getRaspolozivoStanje());
			
			//DODAJEM U MAPU UPLATA
			HashMap<String,ArrayList<Nalog>> uplate=bankaDuznika.getUplate();
		
				if(uplate.containsKey(racunPoverioc)){
					uplate.get(racunPoverioc).add(nalog);
				}else{
					ArrayList<Nalog> nalozi=new ArrayList<>();
					nalozi.add(nalog);
					uplate.put(nalog.getRacunPoverioca(), nalozi);
				}
			

		}else{ //NISU IZ ISTE BANKE
		// TREBA DA SE ISCITA IZ BAZE I BANKA KOJOJ SE PLACA - RESeno VALJDA

		int broj = nalog.getIznos().compareTo(new BigDecimal(250000));
		// zato sto compareTo vraca 0 ako je = ili 1 ako je vece
		if (nalog.isHitno() == true || broj >= 0) {
			// slucaj kad je hitan nalog i iznos veci od 250 000
			//!!!! proveri da li se ovako menja, ako ne, onda treba jos setovati ove vrednosti!!!!!!
			bankaDuznika.getRacunIznos().get(nalog.getRacunDuznik())
					.getRezervisanoStanje().add(nalog.getIznos());
			bankaDuznika.getRacunIznos().get(nalog.getRacunDuznik())
					.getRaspolozivoStanje().subtract(nalog.getIznos());
			//ovA DVA MORAJU DA SE SACUVAJU U BAZI U BANCI DUZNIKA
			Mt103 mt103 = new Mt103();
			mt103.setDatumNaloga(nalog.getDatumNaloga());
			mt103.setDatumValute(nalog.getDatumValute());
			mt103.setDuznik(nalog.getDuznik());
			mt103.setIdPoruke(UUID.randomUUID().toString());//nzm je l treba da se uzme sa naloga ili ovaj novi
			mt103.setIznos(nalog.getIznos());
			mt103.setModelOdobrenja(nalog.getModelOdobrenja());
			mt103.setModelZaduzenja(nalog.getModelZaduzenja());
			mt103.setObracunskiRacBankeDuznik(bankaDuznika.getObracunskiRacun());
			mt103.setPozivNaBrOdobrenja(nalog.getPozivNaBrOdobrenja());
			mt103.setPozivNaBrojZaduzenja(nalog.getPozivNaBrZaduzenja());
			mt103.setPrimalac(nalog.getPrimalac());
			mt103.setRacunDuznik(nalog.getRacunDuznik());
			mt103.setRacunPoverioca(nalog.getRacunPoverioca());
			mt103.setSifraValute(nalog.getOznakaValute());
			mt103.setSvrhaPlacanja(nalog.getSvrhaPlacanja());
			mt103.setSwiftBanDuznik(bankaDuznika.getSwiftCode());
			mt103.setSwiftBanPoverioc(bankaPrimaoca.getSwiftCode());
			
			//DODAJEM U MAPU UPLATA
			HashMap<String,ArrayList<Nalog>> uplate=bankaPrimaoca.getUplate();
		
				if(uplate.containsKey(nalog.getRacunPoverioca())){
					uplate.get(nalog.getRacunPoverioca()).add(nalog);
				}else{
					ArrayList<Nalog> nalozi=new ArrayList<>();
					nalozi.add(nalog);
					uplate.put(nalog.getRacunPoverioca(), nalozi);
				}
				//UPIS U BAZU U BANKU PRIMAOCA OVU LISTU
				//TREBA DA SE SACUVA MT103 U BAZU 
				//TREBA DA SE POSALJE MT103 CENTRALNOJ BANCI

		}else if(broj < 0){
			//CLEARING AND SETTLEMENT
		
			//SAD RZERVISEM PARE DUZNIKU
			BigDecimal novoRezStanje=bankaDuznika.getRacunIznos().get(nalog.getRacunDuznik()).getRezervisanoStanje().add(nalog.getIznos());
			bankaDuznika.getRacunIznos().get(nalog.getRacunDuznik()).setRezervisanoStanje(novoRezStanje);
			
			bankaDuznika.getRacunIznos().get(nalog.getRacunDuznik()).getRaspolozivoStanje().subtract(nalog.getIznos());
			
			//CUANJE NOVOG STANJA RACUN IZVNOS U BANCI DUZNIKA
			
			//Ne znam kako iz baze da vidimo je l vec postoji ovo zaglavlje
			ZaglavljeMt102 zaglavljeMt102= new ZaglavljeMt102();
			zaglavljeMt102.setDatum(nalog.getDatumNaloga());
			zaglavljeMt102.setDatumValute(nalog.getDatumValute());
			zaglavljeMt102.setIdPoruke(UUID.randomUUID().toString());//nzm je l treba da se uzme sa naloga ili ovaj nov
			//zaglavljeMt102.setObracunskiRacBanDuznik(nalog.get);
			zaglavljeMt102.setSifraValute(nalog.getOznakaValute());
			zaglavljeMt102.getUkupanIznos().add(nalog.getIznos()); //sa svakog naloga dodajem iznos
			zaglavljeMt102.setSwiftBankaDuznik(bankaDuznika.getSwiftCode());
			zaglavljeMt102.setSwiftBankaPoverioc(bankaPrimaoca.getSwiftCode());
			
			Mt102 mt102=new Mt102();
			mt102.setZaglavljeMt102(zaglavljeMt102);
			
			PojedinacnoPlacanjeMt102 pojedinacnoMt2=new PojedinacnoPlacanjeMt102(nalog.getIdPoruke(), nalog.getDuznik(), nalog.getSvrhaPlacanja(), nalog.getPrimalac(), nalog.getDatumNaloga(), nalog.getRacunDuznik(), nalog.getModelZaduzenja(), nalog.getPozivNaBrZaduzenja() , nalog.getDuznik(), nalog.getModelOdobrenja(), nalog.getPozivNaBrOdobrenja(), nalog.getIznos(), nalog.getOznakaValute());
			//dodajemo pojedinacna placanja u mt2 poruku
			mt102.getPojedinacnoPlacanjeMt102().add(pojedinacnoMt2);
			
			//treba periodicno slati mt102 centralnoj banci???
			
			//DODAJEM U MAPU UPLATA
			HashMap<String,ArrayList<Nalog>> uplate=bankaPrimaoca.getUplate();
		
				if(uplate.containsKey(nalog.getRacunPoverioca())){
					uplate.get(nalog.getRacunPoverioca()).add(nalog);
				}else{
					ArrayList<Nalog> nalozi=new ArrayList<>();
					nalozi.add(nalog);
					uplate.put(nalog.getRacunPoverioca(), nalozi);
				}
				//mislim da treba da se upise ta lista uplata u bazu, tj valjda da se update-uje
			
		}

	}
	}

	public void primiMt900(Mt900 mt900) {
		//ISCITAJ BANKU IZ BAZE,PO OBRACUNSKOM RACUNU BANKE DUYNIKA
		//MOZDA NE TREBA DA SE CITA IZ BANKE NEGO GORE DA SE IZVUCE
		Banka bankaDuznika= new Banka();
		bankaDuznika.setSwiftCode(mt900.getSwiftBanDuznik());
		bankaDuznika.setObracunskiRacun(mt900.getObracunskiRacBanDuznik());
		
		RacunUBanci rac1=new RacunUBanci();
		rac1.setRaspolozivoStanje(new BigDecimal(120000));
		rac1.setRezervisanoStanje(mt900.getIznos());
		
		HashMap<String, RacunUBanci> racunIznos = new HashMap<>();
		racunIznos.put("12345", rac1);
		bankaDuznika.setRacunIznos(racunIznos);
		
		//SAD SKIDAM IZNOS SA RACUNA DUZNIKA TAKO STO MU UZMEM REZERVISANO
	//	bankaDuznika.getRacunIznos().get(mt900.getRacunDuznika()).setRezervisanoStanje(new BigDecimal(0));
		//UPISI NOVO RACUN IZNOS U BANKUDUYNIKA NA NJEGOVO MESTO U MAPI PO NJEGOVOM RACUNU
	}
	
	public void odobriSredstva(Mt103 mt103, Mt910 mt910){
		//IZ BAZE UZMI BANKU PO OBRACUNSKOM RACUNU BANKE POVERIOCA IZ MT900
	//	String obracunskiRacBanPoverioca = mt910.getObracunskiRacBanPoverioc();
		Banka bankaPoverioca=null;//ovde strpaj ovu iz baze
		
	//	BigDecimal noviIznos=bankaPoverioca.getRacunIznos().get(mt103.getRacunPoverioca()).getRaspolozivoStanje().add(mt103.getIznos());
	//	bankaPoverioca.getRacunIznos().get(mt103.getRacunPoverioca()).setRaspolozivoStanje(noviIznos);
		//upisi u bazu racuna u banci
	}
	
	public Presek obradiZahtevZaIzvod(ZahtevZaIzvod zahtevZaIzvod){
		int brojStavki=3;
		//IZ BAZE UZMI SVE NALOGE ZA UPLATU GDE JE BROJ RACUNA PRIMAOCA ISTI KAO I BROJ 
		//NA ZAHTEVU ZA IZVOD I GDE JE DATUM ISTI KAO ID DATUM NA ZAHTEVU.
		//NAPRAVILA SAM HASH MAPU U BANCI, SA SVIM UPLATAMA, VIDI JE L IMA SMISLA, I AKO JE
		//OK, ONDA TREBA DODAVATI PO KODU U TU HASH MAPU KAD GOD PRIMA ILI SALJE NALOG.->DODALA
		
		Banka izvodIzBanke=null;//ovde strpaj banku iz baze, po oznaci banke
		String brojRacunaFirme=zahtevZaIzvod.getBrojRacuna();
		String oznakaBanke=brojRacunaFirme.substring(0, 3);
		
		ArrayList<Nalog> listaNaloga=izvodIzBanke.getUplate().get(zahtevZaIzvod.getBrojRacuna());
		
		Presek presek=new Presek();
		StavkaPreseka stavka;

		int rbrPreseka=zahtevZaIzvod.getRbrPreseka().intValue()-1;
		
		for(int i=rbrPreseka*brojStavki; i<i+brojStavki; i++){
			if(listaNaloga.size()>=i){
				Nalog n=listaNaloga.get(i);
				stavka= new StavkaPreseka(n.getDuznik(), n.getSvrhaPlacanja(),n.getPrimalac(),n.getDatumNaloga(),n.getDatumValute(),n.getRacunDuznik(),n.getModelZaduzenja(),n.getPozivNaBrZaduzenja(),n.getRacunPoverioca(),n.getModelOdobrenja(),n.getPozivNaBrOdobrenja(),n.getIznos(),"a");
				presek.getStavkaPreseka().add(stavka);
			}
		}

		ZaglavljePreseka zaglavlje= new ZaglavljePreseka();
		zaglavlje.setBrojPreseka(zahtevZaIzvod.getRbrPreseka());
		zaglavlje.setBrojRacuna(zahtevZaIzvod.getBrojRacuna());
		zaglavlje.setDatumNaloga(zahtevZaIzvod.getDatum());
		//NE MOGU OVO DA SETUJEM STARO I NOVO STANJE, BEZ BANKE???
		
		presek.setZaglavljePreseka(zaglavlje);
		return presek;
	}

}
