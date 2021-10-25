package com.abnamro.spring.data.cassandra.service;

import com.abnamro.spring.data.cassandra.exceptions.RecipeNotFoundException;
import com.abnamro.spring.data.cassandra.model.Recipes;
import com.abnamro.spring.data.cassandra.repository.RecipeRepository;
import com.datastax.driver.core.utils.UUIDs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    @Override
    public List<Recipes> getAllRecipesDetails() {
        List<Recipes> recipesList = new ArrayList<Recipes>();
        recipeRepository.findAll().forEach(recipesList::add);
        return recipesList;
    }

    @Override
    public Recipes searchByRecipeId(UUID recipeId) {
        Optional<Recipes> recipeData = recipeRepository.findById(recipeId);
        if (recipeData.isPresent()) {
            return recipeData.get();
        } else {
            throw new RecipeNotFoundException("Recipe not found for recipe no " + recipeId);
        }
    }

    @Override
    public List<Recipes> searchByCategory(String category) {
        List<Recipes> recipes = new ArrayList<Recipes>();
        if (category == null)
            recipeRepository.findAll().forEach(recipes::add);
        else
            recipeRepository.findByCategory(category).forEach(recipes::add);
        return recipes;
    }

    @Override
    public Recipes addRecipe(Recipes recipe) {
        int year = recipe.getCreationdate().getYear();
        Month month = recipe.getCreationdate().getMonth();
        int day = recipe.getCreationdate().getDayOfMonth();
        int hh = recipe.getCreationdate().getHour();
        int mm = recipe.getCreationdate().getMinute();
        LocalDateTime localDateValue = LocalDateTime.of(year, month, day, hh, mm);

        Recipes _recipe = recipeRepository.save(new Recipes(UUIDs.timeBased(), recipe.getCategory(), localDateValue,
                recipe.getIngredients(), recipe.getInstructions(), recipe.getServings(), recipe.getTitle(), recipe.getVegetarian()));
        return _recipe;
    }

    @Override
    public Recipes updateRecipe(UUID recipeId, Recipes recipe) {
        Optional<Recipes> recipeData = recipeRepository.findById(recipeId);
        if (recipeData.isPresent()) {
            Recipes _recipe = recipeData.get();

            if (recipe.getIngredients() != null) {
                _recipe.setIngredients(recipe.getIngredients());
            } else {
                recipe.getIngredients();
            }
            if (recipe.getInstructions() != null) {
                _recipe.setInstructions(recipe.getInstructions());
            } else {
                recipe.getInstructions();
            }
            if (recipe.getCategory() != null) {
                _recipe.setCategory(recipe.getCategory());
            } else {
                recipe.getCategory();
            }
            if (recipe.getCreationdate() != null) {
                _recipe.setCreationdate(recipe.getCreationdate());
            } else {
                recipe.getCreationdate();
            }
            if (recipe.getTitle() != null) {
                _recipe.setTitle(recipe.getTitle());
            } else {
                recipe.getTitle();
            }
            if (recipe.getServings() != null) {
                _recipe.setServings(recipe.getServings());
            } else {
                recipe.getServings();
            }
            if (recipe.getVegetarian() != null) {
                _recipe.setVegetarian(recipe.getVegetarian());
            } else {
                recipe.getVegetarian();
            }

            return _recipe = recipeRepository.save(_recipe);
        } else {
            throw new RecipeNotFoundException("Recipe not found for recipe no " + recipeId);
        }
    }

    @Override
    public void removeRecipe(UUID recipeId) {
        recipeRepository.deleteById(recipeId);
    }
}
