// Autori: Benaoun Tamim - matricola: 759443 - sede: CO
package bin;

import java.util.ArrayList;
import java.util.List ;
import java.util.Scanner;
import java.time.LocalDateTime ;

public class menuProiezionista {

	private Utente utente;
	private List<Proiezione> proiezioni ;

	public menuProiezionista(Utente utente){
		this.utente = utente ;
		this.proiezioni = new ArrayList<>();
	}

	public void avvia(Scanner scanner){
		int scelta;

		do{
		System.out.println("\n== Menu Proiezionista ==") ;
		System.out.println("1. aggiungi proiezione");
		System.out.println("2. Modifica proiezione") ;
		System.out.println("3. elimina proiezione");
		System.out.println("0. logout") ;
		System.out.print("Scelta: ");

		scelta = scanner.nextInt() ;
		scanner.nextLine();

		if(scelta == 1){
			aggiungiProiezione(scanner) ;
		}
		else if(scelta==2){
			modificaProiezione(scanner);
		}
		else if(scelta == 3){
			eliminaProiezione(scanner) ;
		}
		else if(scelta==0){
			System.out.println("logout effettuato");
		}
		else{
			System.out.println("scelta non valida") ;
		}

		} while(scelta != 0);
	}

	private void aggiungiProiezione(Scanner scanner){
		System.out.print("titolo film: ") ;
		String titolo = scanner.nextLine();
		System.out.print("genere: ");
		String genere = scanner.nextLine() ;
		System.out.print("regista: ");
		String regista = scanner.nextLine();
		System.out.print("anno: ") ;
		int anno = scanner.nextInt();
		System.out.print("durata (minuti): ");
		int durata = scanner.nextInt() ;
		System.out.print("eta minima: ");
		int eta = scanner.nextInt();
		scanner.nextLine() ;
		System.out.print("data e ora (aaaa-mm-gg hh:mm): ");
		String dataOra = scanner.nextLine() ;
		System.out.print("prezzo biglietto: ");
		double prezzo = scanner.nextDouble() ;
		scanner.nextLine();

		// controlla che non si sovrapponga con una proiezione esistente
		LocalDateTime nuovaData = LocalDateTime.parse(dataOra,
			java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) ;

		for(int i=0; i<proiezioni.size(); i++){
			if(proiezioni.get(i).getDataOra().equals(nuovaData)){
				System.out.println("esiste gia una proiezione in quella data e ora");
				return ;
			}
		}

		Film f = new Film(titolo, genere, regista, anno, durata, eta) ;
		Proiezione p = new Proiezione(f, nuovaData, prezzo);
		proiezioni.add(p) ;
		System.out.println("proiezione aggiunta!");
	}

	private void modificaProiezione(Scanner scanner){
		if(proiezioni.isEmpty()){
			System.out.println("nessuna proiezione da modificare") ;
			return;
		}

		for(int i=0; i<proiezioni.size(); i++){
			System.out.println((i+1) + ". " + proiezioni.get(i).getFilm().getTitolo()
				+ " " + proiezioni.get(i).getDataOra()) ;
		}

		System.out.print("seleziona numero proiezione: ");
		int sel = scanner.nextInt() ;
		scanner.nextLine();

		if(sel < 1 || sel > proiezioni.size()){
			System.out.println("selezione non valida");
			return ;
		}

		System.out.print("nuova data e ora (aaaa-mm-gg hh:mm): ") ;
		String nuovaDataStr = scanner.nextLine();
		LocalDateTime nuovaData = LocalDateTime.parse(nuovaDataStr,
			java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) ;

		System.out.print("nuovo prezzo: ");
		double prezzo = scanner.nextDouble() ;
		scanner.nextLine();

		proiezioni.get(sel-1).setDataOra(nuovaData);
		proiezioni.get(sel-1).setPrezzoBiglietto(prezzo) ;
		System.out.println("proiezione modificata");
	}

	private void eliminaProiezione(Scanner scanner){
		if(proiezioni.isEmpty()){
			System.out.println("nessuna proiezione da eliminare") ;
			return;
		}

		for(int i=0; i<proiezioni.size(); i++){
			System.out.println((i+1) + ". " + proiezioni.get(i).getFilm().getTitolo()
				+ " " + proiezioni.get(i).getDataOra()) ;
		}

		System.out.print("seleziona numero proiezione da eliminare: ");
		int sel = scanner.nextInt() ;
		scanner.nextLine();

		if(sel < 1 || sel > proiezioni.size()){
			System.out.println("selezione non valida");
			return ;
		}

		proiezioni.remove(sel-1) ;
		System.out.println("proiezione eliminata");
	}
}
