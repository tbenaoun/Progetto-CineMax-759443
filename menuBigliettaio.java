// Autori: Benaoun Tamim - matricola: 759443 - sede: CO
package bin;

import java.util.ArrayList;
import java.util.List ;
import java.util.Scanner;

public class menuBigliettaio {

	private Utente utente ;
	private List<Prenotazione> prenotazioni;

	public menuBigliettaio(Utente utente){
		this.utente = utente ;
		this.prenotazioni = new ArrayList<>();
	}

	public void avvia(Scanner scanner){
		int scelta;

		do{
		System.out.println("\n== Menu Bigliettaio ==") ;
		System.out.println("1. visualizza tutte le prenotazioni");
		System.out.println("2. Cerca prenotazione per codice") ;
		System.out.println("0. logout");
		System.out.print("Scelta: ") ;

		scelta = scanner.nextInt();
		scanner.nextLine() ;

		if(scelta==1){
			visualizzaPrenotazioni() ;
		}
		else if(scelta == 2){
			cercaPrenotazione(scanner);
		}
		else if(scelta==0){
			System.out.println("logout effettuato") ;
		}
		else{
			System.out.println("scelta non valida");
		}

		} while(scelta != 0) ;
	}

	public void aggiungiPrenotazione(Prenotazione p){
		prenotazioni.add(p) ;
	}

	private void visualizzaPrenotazioni(){
		if(prenotazioni.isEmpty()){
			System.out.println("nessuna prenotazione trovata") ;
			return;
		}
		for(int i=0; i<prenotazioni.size(); i++){
			System.out.println(prenotazioni.get(i).toString()) ;
		}
	}

	private void cercaPrenotazione(Scanner scanner){
		System.out.print("inserisci codice prenotazione: ") ;
		String codice = scanner.nextLine().toUpperCase();

		for(int i=0; i<prenotazioni.size(); i++){
			if(prenotazioni.get(i).getCodice().equals(codice)){
				System.out.println("prenotazione trovata:") ;
				System.out.println(prenotazioni.get(i).toString());
				return ;
			}
		}
		System.out.println("nessuna prenotazione trovata con quel codice") ;
	}
}
