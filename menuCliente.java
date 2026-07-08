package bin;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class menuCliente {

	private Utente utente ;
	private List<Prenotazione> prenotazioni;

	public menuCliente(Utente utente){
		this.utente = utente;
		this.prenotazioni = new ArrayList<>() ;
	}

	public void avvia(Scanner scanner){
		int scelta ;

		do{
		System.out.println("\n== Menu Cliente ==") ;
		System.out.println("1. visualizza prenotazioni");
		System.out.println("2. Crea nuova prenotazione") ;
		System.out.println("3. modifica prenotazione");
		System.out.println("4. Elimina prenotazione") ;
		System.out.println("0. logout");
		System.out.print("Scelta: ") ;

		scelta = scanner.nextInt() ;
		scanner.nextLine();

		if(scelta==1){
			visualizzaPrenotazioni() ;
		}
		else if(scelta == 2){
				creaPrenotazione(scanner);
		}
		else if(scelta==3){
			modificaPrenotazione(scanner) ;
		}
		else if(scelta == 4){
				eliminaPrenotazione(scanner);
		}
		else if(scelta==0){
			System.out.println("logout effettuato") ;
		}
		else{
			System.out.println("scelta non valida");
		}

		} while(scelta != 0) ;
	}

	private void visualizzaPrenotazioni(){
		if(prenotazioni.isEmpty()){
			System.out.println("nessuna prenotazione trovata") ;
			return;
		}
		for(int i = 0; i<prenotazioni.size(); i++){
			System.out.println((i+1) + ". " + prenotazioni.get(i).toString());
		}
	}

	private void creaPrenotazione(Scanner scanner){
		System.out.print("inserisci titolo film: ") ;
		String titolo = scanner.nextLine().toLowerCase();

		List<Proiezione> risultati = CsvReader.cercaPerTitolo("data/proiezioni.csv", titolo) ;

		if(risultati.isEmpty()){
			System.out.println("nessun film trovato");
			return ;
		}

		for(int i=0; i<risultati.size(); i++){
			System.out.println((i+1)+ ". "+risultati.get(i).getFilm().getTitolo()
				+" "+risultati.get(i).getDataOra().toLocalDate()) ;
		}

		System.out.print("seleziona proiezione: ");
		int sel = scanner.nextInt() ;
		scanner.nextLine();

		if(sel < 1 || sel > risultati.size()){
			System.out.println("selezione non valida") ;
			return;
		}

		System.out.print("quanti biglietti? ") ;
		int num = scanner.nextInt();
		scanner.nextLine() ;

		Proiezione proiezioneScelta = risultati.get(sel-1);
		Prenotazione p = new Prenotazione(utente, proiezioneScelta, num) ;
		prenotazioni.add(p);
		System.out.println("prenotazione creata! codice: " + p.getCodice()) ;
	}

	private void modificaPrenotazione(Scanner scanner){
		if(prenotazioni.isEmpty()){
			System.out.println("nessuna prenotazione da modificare");
			return ;
		}

		visualizzaPrenotazioni();
		System.out.print("seleziona numero prenotazione da modificare: ") ;
		int sel = scanner.nextInt();
		scanner.nextLine() ;

		if(sel < 1 || sel > prenotazioni.size()){
			System.out.println("selezione non valida");
			return ;
		}

		System.out.print("nuovo numero biglietti: ");
		int num = scanner.nextInt() ;
		scanner.nextLine();

		prenotazioni.get(sel-1).setNumeroBiglietto(num);
		System.out.println("prenotazione modificata") ;
	}

	private void eliminaPrenotazione(Scanner scanner){
		if(prenotazioni.isEmpty()){
			System.out.println("nessuna prenotazione da eliminare");
			return ;
		}

		visualizzaPrenotazioni() ;
		System.out.print("seleziona numero prenotazione da eliminare: ");
		int sel = scanner.nextInt();
		scanner.nextLine() ;

		if(sel < 1 || sel > prenotazioni.size()){
			System.out.println("selezione non valida");
			return ;
		}

		prenotazioni.remove(sel-1) ;
		System.out.println("prenotazione eliminata");
	}
}