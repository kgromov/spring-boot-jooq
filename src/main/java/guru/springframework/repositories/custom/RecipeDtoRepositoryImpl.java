package guru.springframework.repositories.custom;

import guru.springframework.domain.dtos.RecipeCookTime;
import guru.springframework.domain.dtos.RecipeNotes;
import guru.springframework.jooq.tables.Notes;
import guru.springframework.jooq.tables.Recipe;
import org.jooq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RecipeDtoRepositoryImpl implements RecipeDtoRepository {
    public static final String RECIPE_COOK_TIME_MAPPER = "RecipeCookTime";
    public static final String RECIPE_NOTES_MAPPER = "RecipeNotes";

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DSLContext dslContext;

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

    @SuppressWarnings("unchecked")
    private <T> List<T> getResultList(org.jooq.Query jooqQuery, String sqlResultMapperName) {
        Query hibernateQuery = entityManager.createNativeQuery(jooqQuery.getSQL(), sqlResultMapperName);
        setBindParameterValues(hibernateQuery, jooqQuery);
        return (List<T>) hibernateQuery.getResultList();
    }

    private static void setBindParameterValues(Query hibernateQuery, org.jooq.Query jooqQuery) {
        List<Object> values = jooqQuery.getBindValues();
        for (int i = 0; i < values.size(); i++) {
            hibernateQuery.setParameter(i + 1, values.get(i));
        }
    }
}
