package com.abnamro.spring.data.cassandra.service;

import com.abnamro.spring.data.cassandra.exceptions.RecipeNotFoundException;
import com.abnamro.spring.data.cassandra.model.Recipes;
import com.abnamro.spring.data.cassandra.repository.RecipeRepository;
import com.datastax.driver.core.utils.UUIDs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Recipes>> getAllRecipesDetails() {
      try {
          List<Recipes> recipesList = new ArrayList<Recipes>();
          recipeRepository.findAll().forEach(recipesList::add);
          if (recipesList.isEmpty()) {
              return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          }
          return new ResponseEntity<>(recipesList, HttpStatus.OK);
      } catch (Exception e){
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @Override
    public ResponseEntity<Recipes> searchByRecipeId(UUID recipeId) {
        Optional<Recipes> recipeData = recipeRepository.findById(recipeId);

        if (recipeData.isPresent()) {
            return new ResponseEntity<>(recipeData.get(), HttpStatus.OK);
        } else {
            throw new RecipeNotFoundException("Recipe not found for recipe no " + recipeId);
        }
    }

    @Override
    public ResponseEntity<List<Recipes>> searchByCategory(String category) {
        try {
            List<Recipes> recipes = new ArrayList<Recipes>();
            if (category == null)
                recipeRepository.findAll().forEach(recipes::add);
            else
                recipeRepository.findByCategory(category).forEach(recipes::add);
            if (recipes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(recipes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Recipes> addRecipe(Recipes recipe) {
        try {
            int year = recipe.getCreationdate().getYear();
            Month month = recipe.getCreationdate().getMonth();
            int day = recipe.getCreationdate().getDayOfMonth();
            int hh = recipe.getCreationdate().getHour();
            int mm = recipe.getCreationdate().getMinute();
            LocalDateTime localDateValue = LocalDateTime.of(year,month,day,hh,mm);

            Recipes _recipe = recipeRepository.save(new Recipes(UUIDs.timeBased(), recipe.getCategory(), localDateValue,
                    recipe.getIngredients(), recipe.getInstructions(), recipe.getServings(), recipe.getTitle(), recipe.getVegetarian()));
            return new ResponseEntity(_recipe, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    public ResponseEntity<Recipes> updateRecipe(UUID recipeId, Recipes recipe) {

            Optional<Recipes> recipeData = recipeRepository.findById(recipeId);

            if (recipeData.isPresent()) {
                Recipes _recipe = recipeData.get();

                if (recipe.getIngredients() != null) {
                    _recipe.setIngredients(recipe.getIngredients());
                } else {recipe.getIngredients();
                }if (recipe.getInstructions() != null) {
                    _recipe.setInstructions(recipe.getInstructions());
                }else {recipe.getInstructions();
                }if (recipe.getCategory() != null) {
                    _recipe.setCategory(recipe.getCategory());
                }else {recipe.getCategory();
                }if (recipe.getCreationdate() != null) {
                    _recipe.setCreationdate(recipe.getCreationdate());
                }else {recipe.getCreationdate();
                }if (recipe.getTitle() != null) {
                    _recipe.setTitle(recipe.getTitle());
                }else{recipe.getTitle();}
                if (recipe.getServings() != null) {
                    _recipe.setServings(recipe.getServings());
                }else{recipe.getServings();}
                if (recipe.getVegetarian() != null) {
                    _recipe.setVegetarian(recipe.getVegetarian());
                }else{recipe.getVegetarian();}
                return new ResponseEntity<>(recipeRepository.save(_recipe), HttpStatus.OK);
            }else{
                throw new RecipeNotFoundException("Recipe not found for recipe no " + recipeId);
            }
    }

    @Override
    public ResponseEntity<String> removeRecipe(UUID recipeId) {
        try {
            recipeRepository.deleteById(recipeId);
            return new ResponseEntity<>("Successfully Deleted", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
