package com.abnamro.spring.data.cassandra.service;

import com.abnamro.spring.data.cassandra.model.Recipes;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface RecipeService {

    List<Recipes> getAllRecipesDetails();

    Recipes searchByRecipeId(UUID recipeId);

    List<Recipes> searchByCategory(String category);

    Recipes addRecipe(Recipes recipe);

    Recipes updateRecipe(UUID recipeId, Recipes recipe);

    void removeRecipe(UUID recipeId);

}
