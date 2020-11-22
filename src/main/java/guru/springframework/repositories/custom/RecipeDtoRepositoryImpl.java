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

// can be applier both on Repository and Entity levels
@SqlResultSetMapping(name = "RecipeCookTime",
        classes = @ConstructorResult(
                targetClass = RecipeCookTime.class,
                columns = {
                        @ColumnResult(name = "id"),
                        @ColumnResult(name = "description"),
                        @ColumnResult(name = "prepTime"),
                        @ColumnResult(name = "cookTime")
                }))
@SqlResultSetMapping(name = "RecipeNotes",
        classes = @ConstructorResult(
                targetClass = RecipeNotes.class,
                columns = {
                        @ColumnResult(name = "id"),
                        @ColumnResult(name = "description"),
                        @ColumnResult(name = "recipeNotes"),
                        @ColumnResult(name = "difficulty")
                }))
@Repository
public class RecipeDtoRepositoryImpl implements RecipeDtoRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DSLContext dslContext;

    @Override
    public List<RecipeCookTime> getRecipesCookTime() {
        Recipe recipe = Recipe.RECIPE;
        SelectJoinStep<Record4<Long, String, Integer, Integer>> jooqQuery = dslContext
                .select(recipe.ID, recipe.DESCRIPTION, recipe.PREP_TIME, recipe.COOK_TIME)
                .from(recipe);
        List<RecipeCookTime> recipesCookTime = getResultList(jooqQuery);
        return recipesCookTime;
    }

    @Override
    public List<RecipeNotes> getRecipesWithNote() {
        Recipe recipe = Recipe.RECIPE;
        Notes notes = Notes.NOTES;
        SelectOnConditionStep<Record4<Long, String, String, String>> jooqQuery = dslContext
                .select(recipe.ID, recipe.DESCRIPTION, notes.RECIPE_NOTES, recipe.DIFFICULTY)
                .from(recipe)
                .join(notes).on(recipe.NOTES_ID.equal(notes.ID));
        List<RecipeNotes> recipeNotes = getResultList(jooqQuery);
        return recipeNotes;
    }

    @SuppressWarnings("unchecked")
    private <T> List<T> getResultList(org.jooq.Query jooqQuery) {
        Query hibernateQuery = entityManager.createNativeQuery(jooqQuery.getSQL());
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
