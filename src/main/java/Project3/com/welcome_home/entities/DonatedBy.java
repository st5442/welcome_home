package Project3.com.welcome_home.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "DonatedBy")
public class DonatedBy {

    @Id
    @ManyToOne
    @JoinColumn(name = "ItemID", referencedColumnName = "ItemID", nullable = false)
    private Item item;

    @Id
    @ManyToOne
    @JoinColumn(name = "userName", referencedColumnName = "userName", nullable = false)
    private Person person;

    @Column(name = "donateDate", nullable = false)
    private java.sql.Date donateDate;

    // Getters and Setters
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public java.sql.Date getDonateDate() {
        return donateDate;
    }

    public void setDonateDate(java.sql.Date donateDate) {
        this.donateDate = donateDate;
    }
}
