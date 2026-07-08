// Autori: Benaoun Tamim - matricola: 759443 - sede: CO
package bin;

import java.util.List ;
import java.util.Scanner;

public class Login {

	// metodo per fare il login
	public static Utente login(Scanner scanner){
		System.out.print("username: ") ;
		String username = scanner.nextLine();
		System.out.print("password: ");
		String password = scanner.nextLine() ;

		List<Utente> utenti = CsvReader.leggiUtenti("data/utenti.csv") ;

		for(int i=0; i<utenti.size(); i++){
			Utente u = utenti.get(i);
			if(u.getUsername().equals(username) && u.getPassword().equals(password)){
				System.out.println("accesso effettuato, benvenuto " + u.getNome() + "!") ;
				return u ;
			}
		}

		System.out.println("username o password non corretti");
		return null ;
	}

	// metodo per registrare un nuovo cliente
	public static void registrazione(Scanner scanner){
		System.out.println("-- registrazione --");
		System.out.print("nome: ") ;
		String nome = scanner.nextLine();
		System.out.print("cognome: ");
		String cognome = scanner.nextLine() ;
		System.out.print("username: ");
		String username = scanner.nextLine() ;
		System.out.print("password: ") ;
		String password = scanner.nextLine();
		System.out.print("data di nascita (formato aaaa-mm-gg): ");
		String dataNascita = scanner.nextLine() ;
		System.out.print("domicilio: ");
		String domicilio = scanner.nextLine() ;

		Utente nuovo = new Utente(nome, cognome, username, password,
			java.time.LocalDate.parse(dataNascita), domicilio, "Cliente") ;

		CsvReader.registraUtente("data/utenti.csv", nuovo);
		System.out.println("registrazione completata!") ;
	}
}
