package Project3.com.welcome_home.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Location")
public class Location {

    @Id
    @Column(name = "roomNum", nullable = false)
    private Integer roomNum;

    @Id
    @Column(name = "shelfNum", nullable = false)
    private Integer shelfNum;

    @Column(name = "shelf")
    private String shelf;

    @Column(name = "shelfDescription")
    private String shelfDescription;

    // Getters and Setters
    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    public Integer getShelfNum() {
        return shelfNum;
    }

    public void setShelfNum(Integer shelfNum) {
        this.shelfNum = shelfNum;
    }

    public String getShelf() {
        return shelf;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    public String getShelfDescription() {
        return shelfDescription;
    }

    public void setShelfDescription(String shelfDescription) {
        this.shelfDescription = shelfDescription;
    }
}
