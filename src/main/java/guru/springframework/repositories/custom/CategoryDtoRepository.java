package guru.springframework.repositories.custom;

import guru.springframework.domain.dtos.CategoryRecipes;

import java.util.List;

public interface CategoryDtoRepository {

    List<CategoryRecipes> getAllRecipesByCategory();

    List<CategoryRecipes> getAllRecipesByCategoryRecordMapper();

    List<CategoryRecipes> getAllRecipesByCategoryFetchTo();
}
