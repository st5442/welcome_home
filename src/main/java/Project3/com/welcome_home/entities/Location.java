package Project3.com.welcome_home.entities;

import jakarta.persistence.*;

@Entity
@IdClass(LocationId.class)
public class Location {

    @Id
    @Column(name = "roomNum")
    private int roomNum;

    @Id
    @Column(name = "shelfNum")
    private int shelfNum;

    @Column(name = "shelfDescription")
    private String shelfDescription;

    public Location() {
    }

    public Location(int roomNum, int shelfNum, String shelfDescription) {
        this.roomNum = roomNum;
        this.shelfNum = shelfNum;
        this.shelfDescription = shelfDescription;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public int getShelfNum() {
        return shelfNum;
    }

    public void setShelfNum(int shelfNum) {
        this.shelfNum = shelfNum;
    }

    public String getShelfDescription() {
        return shelfDescription;
    }

    public void setShelfDescription(String shelfDescription) {
        this.shelfDescription = shelfDescription;
    }
}
