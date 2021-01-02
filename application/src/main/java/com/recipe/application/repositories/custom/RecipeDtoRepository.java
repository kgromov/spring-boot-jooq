package com.recipe.application.repositories.custom;

import com.entities.domain.dtos.RecipeCookTime;
import com.entities.domain.dtos.RecipeNotes;

import java.util.List;

public interface RecipeDtoRepository {

    List<RecipeCookTime> getRecipesCookTime();

    List<RecipeNotes> getRecipesWithNote();
}
