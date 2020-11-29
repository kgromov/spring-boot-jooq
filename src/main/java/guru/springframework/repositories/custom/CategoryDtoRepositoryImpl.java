package guru.springframework.repositories.custom;

import guru.springframework.domain.dtos.CategoryRecipes;
import guru.springframework.jooq.tables.Category;
import guru.springframework.jooq.tables.RecipeCategory;
import org.jooq.Record3;
import org.jooq.SelectHavingStep;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Here are 3 different ways how to retrieve data with jooq:
 * 1) build query and execute with Hibernate,
 *  mapping with @SqlResultSetMapping specified in {@link guru.springframework.domain.Category} entity class;
 * 2) using {@link org.jooq.ResultQuery#stream()} + map() record -> dto
 *  could be moved to bean; similar to JDBCTemplate mapping
 * 3) execute via jooq fetchInto method.
 * For some reason 1st case is the fastest one
 */
@Repository
public class CategoryDtoRepositoryImpl extends JooqSqlResultMapperRepository implements CategoryDtoRepository {
    public static final String CATEGORY_RECIPES_MAPPER = "CategoryRecipes";

    @Override
    public List<CategoryRecipes> getAllRecipesByCategory() {
        SelectHavingStep<Record3<Long, String, Integer>> jooqQuery = selectRecipesByCategory();
        return getResultList(jooqQuery, CATEGORY_RECIPES_MAPPER);
    }

    public List<CategoryRecipes> getAllRecipesByCategoryRecordMapper() {
        Category category = Category.CATEGORY;
        RecipeCategory recipeCategory = RecipeCategory.RECIPE_CATEGORY;

        return selectRecipesByCategory()
                .stream()
                .map(r -> new CategoryRecipes(r.getValue(category.ID, Long.class),
                        r.getValue(category.DESCRIPTION, String.class),
                        r.getValue(DSL.count().as("recipes"), Long.class))
                )
                .collect(Collectors.toList());
    }

    public List<CategoryRecipes> getAllRecipesByCategoryFetchTo() {
        return selectRecipesByCategory()
                .fetchInto(CategoryRecipes.class);
    }

    private SelectHavingStep<Record3<Long, String, Integer>> selectRecipesByCategory() {
        Category category = Category.CATEGORY;
        RecipeCategory recipeCategory = RecipeCategory.RECIPE_CATEGORY;
        return dslContext
                .select(category.ID, category.DESCRIPTION, DSL.count().as("recipes")) // default count as BigInteger
                .from(category)
                .join(recipeCategory)
                .on(recipeCategory.CATEGORY_ID.equal(category.ID))
                .groupBy(category.ID);
    }
}
