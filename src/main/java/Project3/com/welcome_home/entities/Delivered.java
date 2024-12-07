package Project3.com.welcome_home.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Delivered")
public class Delivered {

    @Id
    @ManyToOne
    @JoinColumn(name = "userName", referencedColumnName = "userName", nullable = false)
    private Person person;

    @Id
    @ManyToOne
    @JoinColumn(name = "orderID", referencedColumnName = "orderID", nullable = false)
    private Ordered ordered;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "date", nullable = false)
    private java.sql.Date date;

    // Getters and Setters
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Ordered getOrdered() {
        return ordered;
    }

    public void setOrdered(Ordered ordered) {
        this.ordered = ordered;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }
}
