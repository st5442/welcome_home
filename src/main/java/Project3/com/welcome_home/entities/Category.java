package Project3.com.welcome_home.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Category")
public class Category {

    @Id
    @Column(name = "mainCategory", nullable = false)
    private String mainCategory;

    @Id
    @Column(name = "subCategory", nullable = false)
    private String subCategory;

    @Column(name = "catNotes")
    private String catNotes;

    // Getters and Setters
    public String getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getCatNotes() {
        return catNotes;
    }

    public void setCatNotes(String catNotes) {
        this.catNotes = catNotes;
    }
}
