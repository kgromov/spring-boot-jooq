package com.recipe.application.repositories.custom;

import com.recipe.application.domain.dtos.CategoryRecipes;

import java.util.List;

public interface CategoryDtoRepository {

    List<CategoryRecipes> getAllRecipesByCategory();

    List<CategoryRecipes> getAllRecipesByCategoryRecordMapper();

    List<CategoryRecipes> getAllRecipesByCategoryFetchTo();
}
