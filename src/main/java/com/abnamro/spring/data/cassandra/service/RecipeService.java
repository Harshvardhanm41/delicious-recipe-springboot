package com.abnamro.spring.data.cassandra.service;

import com.abnamro.spring.data.cassandra.model.Recipes;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface RecipeService {

    ResponseEntity<List<Recipes>> getAllRecipesDetails();

    ResponseEntity<Recipes> searchByRecipeId(UUID recipeId);

    ResponseEntity<List<Recipes>> searchByCategory(String category);

    ResponseEntity<Recipes> addRecipe(Recipes recipe);

    ResponseEntity<Recipes> updateRecipe(UUID recipeId, Recipes recipe);

    ResponseEntity<String> removeRecipe(UUID recipeId);

}
