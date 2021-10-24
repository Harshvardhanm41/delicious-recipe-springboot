package com.abnamro.spring.data.cassandra.controller;

import com.abnamro.spring.data.cassandra.model.Recipes;
import com.abnamro.spring.data.cassandra.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v1/api/recipes")
public class RecipeController {

    @Autowired
    RecipeService recipeServiceData;

    /**
     * To perform the healthCheck of the spring recipe app api
     * @return Success String
     */
    @GetMapping("/healthCheck")
    public String healthCheck(){
        return "Delicious recipes app is alive!!!";
    }

    /**
     * TO retrive all the available recipes in the application
     * @return List of Recipes details
     */
    @GetMapping("/retrieveAllRecipes")
    @ResponseBody
    public ResponseEntity<List<Recipes>> getAllRecipesDetails(){
        return recipeServiceData.getAllRecipesDetails();
    }

    /**
     * Allows user to search for the specific recipe by passing the recipeId
     * @param recipeId
     * @return Recipe details for the specific recipeId
     */
    @PostMapping("/searchByRecipeId/{recipeId}")
    public ResponseEntity<Recipes> searchByRecipeId(@PathVariable("recipeId") UUID recipeId) {
        return recipeServiceData.searchByRecipeId(recipeId);
    }

    /**
     * Allows user to search for the recipes based on the category
     * @param category
     * @return Recipe details based on the category
     */
    @PostMapping("/searchByCategory")
    public ResponseEntity<List<Recipes>> searchByCategory(@RequestParam(required = false) String category) {
        return recipeServiceData.searchByCategory(category);
    }

    /**
     * API allows to add or create the new delicious recipe
     * @param recipes
     * @return the added recipe details to the user
     */
    @PostMapping("/addNewRecipe")
    public ResponseEntity<Recipes> addRecipe(@RequestBody Recipes recipes) {
        return recipeServiceData.addRecipe(recipes);
    }

    /**
     * API allows the user to update the recipe details for the specific recipeId
     * @param recipeId
     * @param recipe
     * @return returns the updated recipe details
     */
    @PutMapping("/updateRecipe/{recipeId}")
    public ResponseEntity<Recipes> updateRecipe(@PathVariable("recipeId") UUID recipeId, @RequestBody Recipes recipe) {
        return recipeServiceData.updateRecipe(recipeId, recipe);
    }

    /**
     * API allows to remove the specific recipe from the application
     * @param recipeId
     * @return
     */
    @DeleteMapping("/removeRecipe/{recipeId}")
    public ResponseEntity<String> removeRecipe(@PathVariable("recipeId") UUID recipeId) {
        return recipeServiceData.removeRecipe(recipeId);
    }

}
