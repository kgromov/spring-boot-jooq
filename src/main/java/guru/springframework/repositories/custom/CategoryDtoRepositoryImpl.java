package guru.springframework.repositories.custom;

import guru.springframework.domain.dtos.CategoryRecipes;
import guru.springframework.jooq.tables.Category;
import guru.springframework.jooq.tables.RecipeCategory;
import org.jooq.Record3;
import org.jooq.SelectHavingStep;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDtoRepositoryImpl extends JooqSqlResultMapperRepository implements CategoryDtoRepository {
    public static final String CATEGORY_RECIPES_MAPPER = "CategoryRecipes";

    @Override
    public List<CategoryRecipes> getAllRecipesByCategory() {
        Category category = Category.CATEGORY;
        RecipeCategory recipeCategory = RecipeCategory.RECIPE_CATEGORY;

        SelectHavingStep<Record3<Long, String, Integer>> jooqQuery = dslContext
                .select(category.ID, category.DESCRIPTION, DSL.count().as("recipes")) // default count as BigInteger
                .from(category)
                .join(recipeCategory)
                .on(recipeCategory.CATEGORY_ID.equal(category.ID))
                .groupBy(category.ID);
        return getResultList(jooqQuery, CATEGORY_RECIPES_MAPPER);
    }
}
