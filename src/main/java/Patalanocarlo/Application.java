package Patalanocarlo;

import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;


public class Application {
    public static void main(String[] args) {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        EventiDao eventiDao = new EventiDao(entityManager); //Mi richiamo Il Dao per iniziare a collegare tutte le iterazioni

        // Creazione di un nuovo libro e con la sua successiva aggiunta
        Libro nuovoLibro = new Libro();  //Mi vado a creare un nuovo Libro che sarà la base per tutti gli altri
        nuovoLibro.setIsbn("9348t");
        nuovoLibro.setTitle("Sea lONLY Island");
        nuovoLibro.setAuthor("La landa deserta");
        nuovoLibro.setGenre("Commedy");
        nuovoLibro.setPublicationYear(1987);
        nuovoLibro.setNumberOfPages(378);

        eventiDao.aggiungiLibro(nuovoLibro); //Faccio riferimento al DAO a cui passo la aggiunta del libro al catalogo e andando nel database mi accorgo della sua aggiunta

       //Vado a rimuovere un elemento in base al isbn:
        String isbnDaRimuovere = "51221323"; //Passo la rimozione come stringa
        eventiDao.rimuoviElementoCatalogoPerISBN(isbnDaRimuovere);

        //Vado a cercare un libro speicifico:
        String isbnDaRicercare = "133456323574352352"; //Passo il libro da cercare
        Libro libroTrovato = eventiDao.ricercaPerISBN(isbnDaRicercare);
        if (libroTrovato != null) { //Avvio un controllo if dove dico che se il libro trovato è diverso da null allora avro trovato un libro altrimenti non trovo il libro con quel id
            System.out.println("Libro trovato:");
            System.out.println(libroTrovato);
        } else {
            System.out.println("Nessun libro trovato con ISBN: " + isbnDaRicercare);
        }

        //Anno di ricerca
        int annoDaRicercare = 1987;
        List<LibraryItem> elementiTrovati = eventiDao.ricercaPerAnnoPubblicazione(annoDaRicercare);//passo la lista dei libri
        if (!elementiTrovati.isEmpty()) {//condizione if dove se trovo un elemento allora mi da il system out che trova un anno di pubblicazione
            System.out.println("Elementi trovati per l'anno di pubblicazione " + annoDaRicercare + ":");
            for (LibraryItem item : elementiTrovati) {//successivamente per ogni elemento trovato con quel anno di pubblicazione allora stampo la lista item.
                System.out.println(item);
            }
        } else { //Altrimenti nulla
            System.out.println("Nessun elemento trovato per l'anno di pubblicazione " + annoDaRicercare);
        }


        //Facciamo la ricerca per autore:
        String autoreDaRicercare = "Le Leggende Del Mare";//Ricerco un titolo che decido io
        List<Libro> libriTrovati = eventiDao.ricercaPerAutore(autoreDaRicercare);
        if (!libriTrovati.isEmpty()) { //Allora come ho fatto per la ricerca se trovo un qualcsoa stampo
            System.out.println("Libri trovati per l'autore " + autoreDaRicercare + ":");
            for (Libro libro : libriTrovati) { //il libro trovato verra stampato tante volte quanti sono i suoi libri con quel nome
                System.out.println(libro);
            }
        } else {
            System.out.println("Nessun libro trovato per l'autore " + autoreDaRicercare);
        }

//Ricerca per il titolo o parte del titolo che è presente:
        String titoloDaRicercare = "Sea";
        List<LibraryItem> elementiTrovati1 = eventiDao.ricercaPerTitolo(titoloDaRicercare);
        if (!elementiTrovati1.isEmpty()) {
            System.out.println("Elementi trovati per il titolo \"" + titoloDaRicercare + "\":");
            for (LibraryItem item : elementiTrovati1) {
                System.out.println(item);
            }
        } else {
            System.out.println("Nessun elemento trovato per il titolo \"" + titoloDaRicercare + "\"");
        }

        //Tessera da ricercare +aggiunta di un nuovo utente che abbia la tessera :

        User nuovoUtente = new User();
        nuovoUtente.setFirstName("Carlo");
        nuovoUtente.setLastName("Patalano");
        nuovoUtente.setBirthDate(LocalDate.of(1990, 5, 15));
        nuovoUtente.setMembershipNumber("12");

// Salvataggio del nuovo utente nel database
        entityManager.getTransaction().begin();
        entityManager.persist(nuovoUtente);
        entityManager.getTransaction().commit();

// Creazione di un prestito per un libro associato all'utente
        LibraryItem libroInPrestito = nuovoLibro; // Ottieni il libro che desideri associare al prestito
        Loan prestito = new Loan();
        prestito.setUser(nuovoUtente);
        prestito.setItem(libroInPrestito);
        prestito.setLoanStartDate(LocalDate.now()); // Imposta la data di inizio prestito
        prestito.setActualReturnDate(LocalDate.now().plusDays(3)); // Il libro non è ancora stato restituito

// Salvataggio del prestito nel database
        entityManager.getTransaction().begin();
        entityManager.persist(prestito);
        entityManager.getTransaction().commit();

// Ora puoi eseguire la ricerca dei prestiti per il numero di tessera
        String numeroTesseraDaRicercare = "12";
        List<Loan> prestitiTrovati = eventiDao.ricercaPrestitiPerNumeroTessera(numeroTesseraDaRicercare);

// Stampa dei prestiti trovati
        if (!prestitiTrovati.isEmpty()) {
            System.out.println("Prestiti trovati per il numero di tessera " + numeroTesseraDaRicercare + ":");
            for (Loan prestito_2 : prestitiTrovati) {
                System.out.println(prestito_2);
            }
        } else {
            System.out.println("Nessun prestito trovato per il numero di tessera " + numeroTesseraDaRicercare);
        }

        entityManager.close();
    }



}
