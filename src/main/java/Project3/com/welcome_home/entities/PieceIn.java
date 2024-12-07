package Project3.com.welcome_home.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "PieceIn")
public class PieceIn {

    @Id
    @ManyToOne
    @JoinColumn(name = "pieceNum", referencedColumnName = "pieceNum", nullable = false)
    private Piece piece;

    @Id
    @ManyToOne
    @JoinColumn(name = "roomNum", referencedColumnName = "roomNum", nullable = false)
    private Location location;

    @Id
    @ManyToOne
    @JoinColumn(name = "shelfNum", referencedColumnName = "shelfNum", nullable = false)
    private Location shelfLocation;

    @Column(name = "notes")
    private String notes;

    // Getters and Setters
    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getShelfLocation() {
        return shelfLocation;
    }

    public void setShelfLocation(Location shelfLocation) {
        this.shelfLocation = shelfLocation;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
