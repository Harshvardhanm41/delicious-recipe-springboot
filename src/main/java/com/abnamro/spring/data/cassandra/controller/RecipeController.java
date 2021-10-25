package com.abnamro.spring.data.cassandra.controller;

import com.abnamro.spring.data.cassandra.exceptions.RecipeNotFoundException;
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
        try{
            List recipesList =  recipeServiceData.getAllRecipesDetails();
            if (recipesList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(recipesList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Allows user to search for the specific recipe by passing the recipeId
     * @param recipeId
     * @return Recipe details for the specific recipeId
     */
    @PostMapping("/searchByRecipeId/{recipeId}")
    public ResponseEntity<Recipes> searchByRecipeId(@PathVariable("recipeId") UUID recipeId) {
        Optional<Recipes> recipeData = Optional.ofNullable(recipeServiceData.searchByRecipeId(recipeId));
        if (recipeData.isPresent()) {

            return new ResponseEntity<>(recipeData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Allows user to search for the recipes based on the category
     * @param category
     * @return Recipe details based on the category
     */
    @PostMapping("/searchByCategory")
    public ResponseEntity<List<Recipes>> searchByCategory(@RequestParam(required = false) String category) {
        try{
            List<Recipes> recipeList =  recipeServiceData.searchByCategory(category);
            if (recipeList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(recipeList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * API allows to add or create the new delicious recipe
     * @param recipes
     * @return the added recipe details to the user
     */
    @PostMapping("/addNewRecipe")
    public ResponseEntity<Recipes> addRecipe(@RequestBody Recipes recipes) {
        try{
            Recipes addRecipedata = recipeServiceData.addRecipe(recipes);
            return new ResponseEntity(addRecipedata, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * API allows the user to update the recipe details for the specific recipeId
     * @param recipeId
     * @param recipe
     * @return returns the updated recipe details
     */
    @PutMapping("/updateRecipe/{recipeId}")
    public ResponseEntity<Recipes> updateRecipe(@PathVariable("recipeId") UUID recipeId, @RequestBody Recipes recipe) {
        try{
            Recipes updateRecipes = recipeServiceData.updateRecipe(recipeId, recipe);
            return new ResponseEntity<>(updateRecipes, HttpStatus.OK);
        } catch (Exception e) {
            throw new RecipeNotFoundException("Recipe not found for recipe no " + recipeId);
        }
    }

    /**
     * API allows to remove the specific recipe from the application
     * @param recipeId
     * @return
     */
    @DeleteMapping("/removeRecipe/{recipeId}")
    public ResponseEntity<String> removeRecipe(@PathVariable("recipeId") UUID recipeId) {
        try{
            recipeServiceData.removeRecipe(recipeId);
            return new ResponseEntity<>("Successfully Deleted", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
