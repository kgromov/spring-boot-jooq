package com.recipe.application.repositories.custom;

import com.recipe.application.domain.dtos.RecipeCookTime;
import com.recipe.application.domain.dtos.RecipeNotes;

import java.util.List;

public interface RecipeDtoRepository {

    List<RecipeCookTime> getRecipesCookTime();

    List<RecipeNotes> getRecipesWithNote();
}
