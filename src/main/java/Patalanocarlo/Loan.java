package Patalanocarlo;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private LibraryItem item;

    private LocalDate loanStartDate;
    private LocalDate expectedReturnDate;
    private LocalDate actualReturnDate;

    @PrePersist
    public void prePersist() {
        this.expectedReturnDate = this.loanStartDate.plusDays(30);
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LibraryItem getItem() {
        return item;
    }

    public void setItem(LibraryItem item) {
        this.item = item;
    }

    public LocalDate getLoanStartDate() {
        return loanStartDate;
    }

    public void setLoanStartDate(LocalDate loanStartDate) {
        this.loanStartDate = loanStartDate;
    }

    public LocalDate getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(LocalDate expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public LocalDate getActualReturnDate() {
        return actualReturnDate;
    }

    public void setActualReturnDate(LocalDate actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }
    @Override
    public String toString() {
        return "Prestito [ID=" + id +
                ", Utente=" + user.getFirstName() + " " + user.getLastName() +
                ", Elemento=" + item.getTitle() +
                ", Data inizio prestito=" + loanStartDate +
                ", Data restituzione prevista=" + expectedReturnDate +
                ", Data restituzione effettiva=" + actualReturnDate + "]";
    }
}