package Patalanocarlo;

import jakarta.persistence.EntityManager;


public class Application {
    public static void main(String[] args) {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();
        EventiDao eventiDao = new EventiDao(entityManager);

        // Creazione di un nuovo libro e con la sua successiva aggiunta
        Libro nuovoLibro = new Libro();
        nuovoLibro.setIsbn("5122");
        nuovoLibro.setTitle("Sea of thieves");
        nuovoLibro.setAuthor("J.R.R. Tolkien");
        nuovoLibro.setGenre("Fantasy");
        nuovoLibro.setPublicationYear(2000);
        nuovoLibro.setNumberOfPages(178);

        eventiDao.aggiungiLibro(nuovoLibro);

       //Vado a rimuovere un elemento in base al isbn:
        String isbnDaRimuovere = "133456323";
        eventiDao.rimuoviElementoCatalogoPerISBN(isbnDaRimuovere);

        //Vado a cercare un libro speicifico:
        String isbnDaRicercare = "133456323574352352";
        Libro libroTrovato = eventiDao.ricercaPerISBN(isbnDaRicercare);
        if (libroTrovato != null) {
            System.out.println("Libro trovato:");
            System.out.println(libroTrovato);
        } else {
            System.out.println("Nessun libro trovato con ISBN: " + isbnDaRicercare);
        }


        entityManager.close();
    }
}
