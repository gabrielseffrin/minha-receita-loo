package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "recipe")
public class Recipe implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_recipe")
    private long id_recipe;

    @Column(name = "nameRecipe")
    private String nameRecipe;

    @Column(name = "ingredients")
    private String ingredients;

    @Column(name = "preparetionMode")
    private String preparationMode;

    @Column(name = "notes")
    private String note;

    @Column(name = "category")
    private String category;

    @Column(name = "approved")
    private boolean status;

    @Column(name = "public")
    private boolean isPublic;

    @ManyToOne
    private RecipeOwner owner;

    public void setNameRecipe(String nameRecipe) {
        this.nameRecipe = nameRecipe;
    }

    public String getNameRecipe() {
        return nameRecipe;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setId_recipe(int id_recipe) {
        this.id_recipe = id_recipe;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setPreparationMode(String preparationMode) {
        this.preparationMode = preparationMode;
    }

    public String getPreparationMode() {
        return preparationMode;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setOwner(RecipeOwner owner) {
        this.owner = owner;
    }

    public RecipeOwner getOwner() {
        return owner;
    }

    public long getId() {
        return id_recipe;
    }
}