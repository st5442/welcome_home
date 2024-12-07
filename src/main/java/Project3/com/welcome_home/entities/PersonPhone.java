package Project3.com.welcome_home.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "PersonPhone")
public class PersonPhone {

    @Id
    @ManyToOne
    @JoinColumn(name = "userName", referencedColumnName = "userName", nullable = false)
    private Person person;

    @Id
    @Column(name = "phone", nullable = false)
    private String phone;

    // Getters and Setters
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
