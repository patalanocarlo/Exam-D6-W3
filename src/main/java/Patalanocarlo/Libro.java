package Patalanocarlo;


import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Libro extends LibraryItem {

    private String author;
    private String genre;

    public String getAuthor(){
        return author;
    }
    public void setAuthor(String author){
        this.author=author;
    }

    public String getGenre(){
        return genre;
    }
    public void setGenre(String genre){
        this.genre=genre;
    }
    @Override
    public String toString() {
        return "Libro{" +

                "isbn='" + getIsbn() + '\'' +
                ", title='" + getTitle() + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", publicationYear=" + getPublicationYear() +
                ", numberOfPages=" + getNumberOfPages() +
                '}';
    }
}
