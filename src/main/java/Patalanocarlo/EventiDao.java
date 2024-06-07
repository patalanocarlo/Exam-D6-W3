package Patalanocarlo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;

import java.util.List;

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

    //Ricerca di un libro in base al suo isbn:
    public Libro ricercaPerISBN(String isbn) {
        return entityManager.find(Libro.class, isbn);
    }

    //Ricerca Per anno di pubblicazione:
    public List<LibraryItem> ricercaPerAnnoPubblicazione(int anno) {
        TypedQuery<LibraryItem> query = entityManager.createQuery(
                "SELECT item FROM LibraryItem item WHERE item.publicationYear = :anno",
                LibraryItem.class
        );
        query.setParameter("anno", anno);
        return query.getResultList();
    }
    //aggiungiamo una ricerca per autore:
    public List<Libro> ricercaPerAutore(String autore) {
        TypedQuery<Libro> query = entityManager.createQuery(
                "SELECT libro FROM Libro libro WHERE libro.author = :autore",
                Libro.class
        );
        query.setParameter("autore", autore);
        return query.getResultList();
    }
    //Ricerca in base al titolo:
    public List<LibraryItem> ricercaPerTitolo(String titolo) {
        TypedQuery<LibraryItem> query = entityManager.createQuery(
                "SELECT item FROM LibraryItem item WHERE item.title LIKE :titolo",
                LibraryItem.class
        );
        query.setParameter("titolo", "%" + titolo + "%");
        return query.getResultList();
    }
    public List<Loan> ricercaPrestitiPerNumeroTessera(String numeroTessera) {
        TypedQuery<Loan> query = entityManager.createQuery(
                "SELECT loan FROM Loan loan WHERE loan.user.membershipNumber = :numeroTessera AND loan.actualReturnDate IS NULL",
                Loan.class
        );
        query.setParameter("numeroTessera", numeroTessera);
        return query.getResultList();
    }
}
