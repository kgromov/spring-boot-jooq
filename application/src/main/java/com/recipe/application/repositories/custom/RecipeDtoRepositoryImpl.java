package com.recipe.application.repositories.custom;

import com.entities.domain.dtos.RecipeCookTime;
import com.entities.domain.dtos.RecipeNotes;
import guru.springframework.jooq.tables.Notes;
import guru.springframework.jooq.tables.Recipe;
import org.jooq.Record4;
import org.jooq.SelectJoinStep;
import org.jooq.SelectOnConditionStep;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.entities.domain.Recipe.RECIPE_COOK_TIME_MAPPER;
import static com.entities.domain.Recipe.RECIPE_NOTES_MAPPER;

/**
 * Impl is required suffix is using fragment as extension to CRUD JPA repository
 * Since fragments are not repositories
 * by themselves, Spring relies on this mechanism to find the fragment
 * implementation.
 * So even @Repository could be omitted.
 * Seems it would be better to introduce some @Fragment annotation
 */
@Repository
public class RecipeDtoRepositoryImpl extends JooqSqlResultMapperRepository implements RecipeDtoRepository {
    @Override
    public List<RecipeCookTime> getRecipesCookTime() {
        Recipe recipe = Recipe.RECIPE;
        SelectJoinStep<Record4<Long, String, Integer, Integer>> jooqQuery = dslContext
                .select(recipe.ID, recipe.DESCRIPTION, recipe.PREP_TIME.as("prepTime"), recipe.COOK_TIME.as("cookTime"))
                .from(recipe);
        return getResultList(jooqQuery, RECIPE_COOK_TIME_MAPPER);
    }

    @Override
    public List<RecipeNotes> getRecipesWithNote() {
        Recipe recipe = Recipe.RECIPE;
        Notes notes = Notes.NOTES;
        SelectOnConditionStep<Record4<Long, String, String, String>> jooqQuery = dslContext
                .select(recipe.ID, recipe.DESCRIPTION, notes.RECIPE_NOTES.as("recipeNotes"), recipe.DIFFICULTY)
                .from(recipe)
                .join(notes).on(recipe.NOTES_ID.equal(notes.ID));
        return getResultList(jooqQuery, RECIPE_NOTES_MAPPER);
    }
}
