package guru.springframework.domain;

import guru.springframework.domain.dtos.RecipeCookTime;
import guru.springframework.domain.dtos.RecipeNotes;
import guru.springframework.repositories.custom.RecipeDtoRepositoryImpl;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jt on 6/13/17.
 */
@Getter
@Setter
@Entity
@Table(name = "recipe")
// works only on Entity level
@SqlResultSetMapping(name = RecipeDtoRepositoryImpl.RECIPE_COOK_TIME_MAPPER,
        classes = @ConstructorResult(
                targetClass = RecipeCookTime.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "description", type = String.class),
                        @ColumnResult(name = "prepTime", type = Integer.class),
                        @ColumnResult(name = "cookTime", type = Integer.class)
                }))
@SqlResultSetMapping(name = RecipeDtoRepositoryImpl.RECIPE_NOTES_MAPPER,
        classes = @ConstructorResult(
                targetClass = RecipeNotes.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "description", type = String.class),
                        @ColumnResult(name = "recipeNotes", type = String.class),
                        @ColumnResult(name = "difficulty", type = String.class)
                }))
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    @Column(name = "prep_time")
    private Integer prepTime;
    @Column(name = "cook_time")
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    @Lob
    private String directions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    @Lob
    private Byte[] image;

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    @ManyToMany
    @JoinTable(name = "recipe_category",
        joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public void setNotes(Notes notes) {
        if (notes != null) {
            this.notes = notes;
        }
    }

    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }
}
