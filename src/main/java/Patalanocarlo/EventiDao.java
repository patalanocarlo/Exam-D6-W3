package Patalanocarlo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;

public class EventiDao {
    private EntityManager entityManager;

    public EventiDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
//Aggiunta di un libro:
    public void aggiungiLibro(Libro libro) {
        entityManager.getTransaction().begin();
        entityManager.persist(libro);
        entityManager.getTransaction().commit();
    }

    //Rimoazione di un libro in bace al isbn:
    public void rimuoviElementoCatalogoPerISBN(String isbn) {
        entityManager.getTransaction().begin();
        Libro libroDaRimuovere = entityManager.find(Libro.class, isbn);
        if (libroDaRimuovere != null) {
            entityManager.remove(libroDaRimuovere);
            entityManager.getTransaction().commit();
        } else {
            entityManager.getTransaction().rollback();
            throw new EntityNotFoundException("Elemento del catalogo non trovato per il codice ISBN: " + isbn);
        }
    }

    //Ricerca di un libro in base al suo isb:
    public Libro ricercaPerISBN(String isbn) {
        return entityManager.find(Libro.class, isbn);
    }

}
