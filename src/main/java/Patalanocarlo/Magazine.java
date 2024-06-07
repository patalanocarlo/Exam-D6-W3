package Patalanocarlo;

import jakarta.persistence.Entity;

@Entity
public class Magazine extends LibraryItem {
public enum Periodicity{
    SETTIMANALE, MENSILE, SEMESTRALE
}
private Periodicity periodicity;

public Periodicity getPeriodicity(){
    return periodicity;
}
    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }
}
