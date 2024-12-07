package Project3.com.welcome_home.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ItemIn")
public class ItemIn {

    @Id
    @ManyToOne
    @JoinColumn(name = "ItemID", referencedColumnName = "ItemID", nullable = false)
    private Item item;

    @Id
    @ManyToOne
    @JoinColumn(name = "orderID", referencedColumnName = "orderID", nullable = false)
    private Ordered ordered;

    @Column(name = "found", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean found = false;

    // Getters and Setters
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Ordered getOrdered() {
        return ordered;
    }

    public void setOrdered(Ordered ordered) {
        this.ordered = ordered;
    }

    public Boolean getFound() {
        return found;
    }

    public void setFound(Boolean found) {
        this.found = found;
    }
}
