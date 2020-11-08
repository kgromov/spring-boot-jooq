/*
 * This file is generated by jOOQ.
 */
package guru.springframework.jooq.tables.records;


import guru.springframework.jooq.tables.RecipeCategory;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RecipeCategoryRecord extends UpdatableRecordImpl<RecipeCategoryRecord> implements Record2<Long, Long> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>RECIPE_CATEGORY.RECIPE_ID</code>.
     */
    public void setRecipeId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>RECIPE_CATEGORY.RECIPE_ID</code>.
     */
    public Long getRecipeId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>RECIPE_CATEGORY.CATEGORY_ID</code>.
     */
    public void setCategoryId(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>RECIPE_CATEGORY.CATEGORY_ID</code>.
     */
    public Long getCategoryId() {
        return (Long) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<Long, Long> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<Long, Long> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Long, Long> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return RecipeCategory.RECIPE_CATEGORY.RECIPE_ID;
    }

    @Override
    public Field<Long> field2() {
        return RecipeCategory.RECIPE_CATEGORY.CATEGORY_ID;
    }

    @Override
    public Long component1() {
        return getRecipeId();
    }

    @Override
    public Long component2() {
        return getCategoryId();
    }

    @Override
    public Long value1() {
        return getRecipeId();
    }

    @Override
    public Long value2() {
        return getCategoryId();
    }

    @Override
    public RecipeCategoryRecord value1(Long value) {
        setRecipeId(value);
        return this;
    }

    @Override
    public RecipeCategoryRecord value2(Long value) {
        setCategoryId(value);
        return this;
    }

    @Override
    public RecipeCategoryRecord values(Long value1, Long value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached RecipeCategoryRecord
     */
    public RecipeCategoryRecord() {
        super(RecipeCategory.RECIPE_CATEGORY);
    }

    /**
     * Create a detached, initialised RecipeCategoryRecord
     */
    public RecipeCategoryRecord(Long recipeId, Long categoryId) {
        super(RecipeCategory.RECIPE_CATEGORY);

        setRecipeId(recipeId);
        setCategoryId(categoryId);
    }
}
