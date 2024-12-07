package Project3.com.welcome_home.entities;

import java.io.Serializable;
import java.util.Objects;

public class PieceId implements Serializable {

    private Integer itemID;
    private Integer pieceNum;

    // Default constructor
    public PieceId() {}

    // Constructor
    public PieceId(Integer itemID, Integer pieceNum) {
        this.itemID = itemID;
        this.pieceNum = pieceNum;
    }

    // Getters and Setters
    public Integer getItemID() {
        return itemID;
    }

    public void setItemID(Integer itemID) {
        this.itemID = itemID;
    }

    public Integer getPieceNum() {
        return pieceNum;
    }

    public void setPieceNum(Integer pieceNum) {
        this.pieceNum = pieceNum;
    }

    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PieceId pieceId = (PieceId) o;
        return Objects.equals(itemID, pieceId.itemID) &&
                Objects.equals(pieceNum, pieceId.pieceNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemID, pieceNum);
    }
}
