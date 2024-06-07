package Patalanocarlo;



import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class LibraryItem {
    @id
    private String isbn;
    private String title;
    private int publicationYear;
    private int numberOfPages;


    public String getIsbn(){
        return isbn;
    }
    public void setIsbn(String isbn){
        this.isbn=isbn;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public int getPublicationYear(){
        return publicationYear;
    }
    public void setPublicationYear(int publicationYear){
        this.publicationYear=publicationYear;
    }
    public int getNumberOfPages(){
        return numberOfPages;
    }
    public void setNumberOfPages(int numberOfPages){
        this.numberOfPages=numberOfPages;
    }


}
