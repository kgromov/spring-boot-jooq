/*
 * This file is generated by jOOQ.
 */
package guru.springframework.jooq;


import guru.springframework.jooq.tables.Category;
import guru.springframework.jooq.tables.Databasechangelog;
import guru.springframework.jooq.tables.Databasechangeloglock;
import guru.springframework.jooq.tables.Ingredient;
import guru.springframework.jooq.tables.Notes;
import guru.springframework.jooq.tables.Recipe;
import guru.springframework.jooq.tables.RecipeCategory;
import guru.springframework.jooq.tables.UnitOfMeasure;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SpringGuruRecipe extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>spring_guru_recipe</code>
     */
    public static final SpringGuruRecipe SPRING_GURU_RECIPE = new SpringGuruRecipe();

    /**
     * No further instances allowed
     */
    private SpringGuruRecipe() {
        super("spring_guru_recipe", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.<Table<?>>asList(
            Category.CATEGORY,
            Databasechangelog.DATABASECHANGELOG,
            Databasechangeloglock.DATABASECHANGELOGLOCK,
            Ingredient.INGREDIENT,
            Notes.NOTES,
            Recipe.RECIPE,
            RecipeCategory.RECIPE_CATEGORY,
            UnitOfMeasure.UNIT_OF_MEASURE);
    }
}
