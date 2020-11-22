package guru.springframework.repositories.custom;

import guru.springframework.domain.dtos.RecipeCookTime;
import guru.springframework.domain.dtos.RecipeNotes;

import java.util.List;

public interface RecipeDtoRepository {

    List<RecipeCookTime> getRecipesCookTime();

    List<RecipeNotes> getRecipesWithNote();
}
