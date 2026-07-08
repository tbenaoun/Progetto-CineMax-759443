package bin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static List<Proiezione> cercaPerTitolo(String percorso, String titolo){
        List<Proiezione> risultati = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(percorso))){
            String riga;
            boolean prima = true;

            while((riga = br.readLine()) != null){
                if(prima){
                    prima = false;
                    continue;
                }

                riga = riga.replace("\"", "");
                String[] campi = riga.split(",");

                if(campi.length < 8) continue;

                if(campi[1].trim().toLowerCase().contains(titolo)){

                    Film f = new Film(campi[1].trim(), campi[2].trim(), campi[3].trim(),
                        Integer.parseInt(campi[4].trim()),
                        Integer.parseInt(campi[5].trim()),
                        Integer.parseInt(campi[6].trim()));

                    LocalDateTime data = LocalDateTime.parse(campi[0].trim(), FORMATTER);
                    Proiezione p = new Proiezione(f, data, Double.parseDouble(campi[7].trim()));
                    risultati.add(p);
                }
            }

        } catch(IOException e){
            System.out.println("errore nella lettura del file: " + e.getMessage());
        }

        return risultati;
    }


    public static List<Utente> leggiUtenti(String percorso){
        List<Utente> utenti = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(percorso))){
            String riga;
            boolean prima = true;

            while((riga = br.readLine()) != null){
                if(prima){ prima = false; continue; }

                String[] campi = riga.split(",");
                if(campi.length < 7) continue;

                Utente u = new Utente(campi[0].trim(), campi[1].trim(),
                    campi[2].trim(), campi[3].trim(),
                    java.time.LocalDate.parse(campi[4].trim()),
                    campi[5].trim(), campi[6].trim());

                utenti.add(u);
            }

        } catch(IOException e){
            System.out.println("errore nella lettura del file: " + e.getMessage());
        }

        return utenti;
    }


    public static void registraUtente(String percorso, Utente u){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(percorso, true))){
            bw.write(u.getNome()+","+u.getCognome()+","+u.getUsername()+","
                +u.getPassword()+","+u.getDataNascita()+","+u.getDomicilio()+","+u.getRuolo());
            bw.newLine();
        } catch(IOException e){
            System.out.println("errore nella scrittura del file: " + e.getMessage());
        }
    }
}