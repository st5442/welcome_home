package Project3.com.welcome_home.entities;

import jakarta.persistence.*;

@Entity
@IdClass(LocationId.class)
public class Location {

    @Id
    private int roomNum;

    @Id
    private int shelfNum;

    private String shelf;
    private String shelfDescription;

    public Location() {
    }

    public Location(int roomNum, int shelfNum, String shelf, String shelfDescription) {
        this.roomNum = roomNum;
        this.shelfNum = shelfNum;
        this.shelf = shelf;
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
