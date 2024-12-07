package Project3.com.welcome_home.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Act")
public class Act {

    @Id
    @ManyToOne
    @JoinColumn(name = "userName", referencedColumnName = "userName", nullable = false)
    private Person person;

    @Id
    @ManyToOne
    @JoinColumn(name = "roleID", referencedColumnName = "roleID", nullable = false)
    private Role role;

    // Getters and Setters
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
