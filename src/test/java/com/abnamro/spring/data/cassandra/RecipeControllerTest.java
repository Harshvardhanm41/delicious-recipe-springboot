package com.abnamro.spring.data.cassandra;

import com.abnamro.spring.data.cassandra.controller.RecipeController;
import com.abnamro.spring.data.cassandra.model.Recipes;
import com.abnamro.spring.data.cassandra.service.RecipeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc

public class RecipeControllerTest {

    private MockMvc mvc;



    @InjectMocks
    private RecipeController recipeController = new RecipeController();

    @Mock
    private RecipeService recipeServiceData;


    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(recipeController).build();
        MockitoAnnotations.initMocks(this);
    }




    @Test
    public void getAllRecipesDetails() throws Exception {
        List<Recipes> mockList = getRecipesList();
        Mockito.when(recipeServiceData.getAllRecipesDetails()).thenReturn(mockList);
        this.mvc.perform(get("/v1/api/recipes/retrieveAllRecipes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

    }

    @Test
    public void searchByCategoryTest() throws Exception {
        List<Recipes> mockList = getRecipesList();
        Mockito.when(recipeServiceData.searchByCategory(Mockito.any())).thenReturn(mockList);
        this.mvc.perform(post("/v1/api/recipes/searchByCategory"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

    }

    @Test
    public void addRecipeTest() throws Exception {
        Recipes recipeObj = recipesObj();
        ResponseEntity responseEntity = new ResponseEntity(recipeObj, HttpStatus.CREATED);
        Mockito.when(recipeServiceData.addRecipe(Mockito.any())).thenReturn(recipeObj);
        Assert.assertEquals(201,responseEntity.getStatusCodeValue());
    }

    @Test
    public void updateRecipeTest() throws Exception {
        Recipes recipeObj = recipesObj();
        ResponseEntity responseEntity = new ResponseEntity(recipeObj, HttpStatus.CREATED);
        Mockito.when(recipeServiceData.updateRecipe(Mockito.any(), Mockito.any())).thenReturn(recipeObj);
        Assert.assertEquals(201,responseEntity.getStatusCodeValue());
    }

    private List<Recipes> getRecipesList() {
        List<Recipes> mockList = new ArrayList<>();
        Recipes recipes = new Recipes();
        recipes.setTitle("Pasta Red Sauce");
        recipes.setVegetarian(true);
        recipes.setCategory("Breakfast");
        mockList.add(recipes);
        return mockList;
    }

    private Recipes recipesObj() {
        Recipes resRecipesObj = new Recipes();
        resRecipesObj.setRecipeId(UUID.randomUUID());
        resRecipesObj.setInstructions("Add 2 Spoons");
        resRecipesObj.setServings(3);
        resRecipesObj.setTitle("Chicken Kabab");
        resRecipesObj.setCreationdate(LocalDateTime.now());
        return resRecipesObj;
    }
}