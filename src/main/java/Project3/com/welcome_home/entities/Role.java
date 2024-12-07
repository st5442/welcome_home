package Project3.com.welcome_home.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Role")
public class Role {

    @Id
    @Column(name = "roleID", nullable = false)
    private String roleID;

    @Column(name = "rDescription")
    private String rDescription;

    // Getters and Setters
    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getrDescription() {
        return rDescription;
    }

    public void setrDescription(String rDescription) {
        this.rDescription = rDescription;
    }
}
