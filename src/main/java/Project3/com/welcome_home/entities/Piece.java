package Project3.com.welcome_home.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Piece")
public class Piece {

    @Id
    @ManyToOne
    @JoinColumn(name = "ItemID", referencedColumnName = "ItemID", nullable = false)
    private Item item;

    @Id
    @Column(name = "pieceNum", nullable = false)
    private Integer pieceNum;

    @Column(name = "pDescription")
    private String pDescription;

    @Column(name = "length", nullable = false)
    private Integer length;

    @Column(name = "width", nullable = false)
    private Integer width;

    @Column(name = "height", nullable = false)
    private Integer height;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "roomNum", referencedColumnName = "roomNum", nullable = false),
            @JoinColumn(name = "shelfNum", referencedColumnName = "shelfNum", nullable = false)
    })
    private Location location;

    @Column(name = "pNotes")
    private String pNotes;

    // Getters and Setters
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getPieceNum() {
        return pieceNum;
    }

    public void setPieceNum(Integer pieceNum) {
        this.pieceNum = pieceNum;
    }

    public String getpDescription() {
        return pDescription;
    }

    public void setpDescription(String pDescription) {
        this.pDescription = pDescription;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getpNotes() {
        return pNotes;
    }

    public void setpNotes(String pNotes) {
        this.pNotes = pNotes;
    }
}
