package com.recipe.application.services;

import com.recipe.application.domain.Recipe;
import com.recipe.application.domain.dtos.CategoryRecipes;
import com.recipe.application.domain.dtos.RecipeCookTime;
import com.recipe.application.domain.dtos.RecipeNotes;
import com.recipe.application.commands.RecipeCommand;
import com.recipe.application.converters.RecipeCommandToRecipe;
import com.recipe.application.converters.RecipeToRecipeCommand;
import com.recipe.application.exceptions.NotFoundException;
import com.recipe.application.repositories.RecipeRepository;
import com.recipe.application.repositories.custom.CategoryDtoRepository;
import com.recipe.application.repositories.custom.RecipeDtoRepository;
import com.recipe.application.services.profiling.Profiling;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by jt on 6/13/17.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;
    @Autowired
    private CategoryDtoRepository categoryDtoRepository;
    @Autowired
    private RecipeDtoRepository recipeDtoRepository;

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("I'm in the service");
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        log.info("############### Running JOOQ ################");
        List<RecipeNotes> recipesWithNote = recipeDtoRepository.getRecipesWithNote();
        log.info("First recipesWithNote = {}", recipesWithNote.get(0));
        List<RecipeCookTime> recipesCookTime = recipeDtoRepository.getRecipesCookTime();
        log.info("First recipesCookTime = {}", recipesCookTime.get(0));
        List<CategoryRecipes> allRecipesByCategory = categoryDtoRepository.getAllRecipesByCategory();
        CategoryRecipes recipes1 = categoryDtoRepository.getAllRecipesByCategoryRecordMapper().get(0);
        CategoryRecipes recipes2 = categoryDtoRepository.getAllRecipesByCategoryFetchTo().get(0);
        log.info("First categoryRecipe = {}", allRecipesByCategory.get(0));
        log.info("recipes clazz = {}", allRecipesByCategory.get(0).getRecipes().getClass());
        Long countOfRecipe = allRecipesByCategory.get(0).getRecipes();
        log.info("#############################################");
        return recipes;
    }

    @Override
    @Profiling(timeunit = TimeUnit.MICROSECONDS)
    public Recipe findById(Long recipeId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if (!recipeOptional.isPresent()) {
            throw new NotFoundException("Recipe Not Found. For ID value: " + recipeId.toString() );
        }
        return recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long recipeId) {
        return recipeToRecipeCommand.convert(findById(recipeId));
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);
        if (detachedRecipe == null) {
            throw new NotFoundException("Recipe Not Found. For ID value: " + command.getId() );
        }
        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved RecipeId:" + savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    public void deleteById(Long recipeId) {
        recipeRepository.deleteById(recipeId);
    }
}
