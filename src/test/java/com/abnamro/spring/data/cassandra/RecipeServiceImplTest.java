package com.abnamro.spring.data.cassandra;

import com.abnamro.spring.data.cassandra.model.Recipes;
import com.abnamro.spring.data.cassandra.repository.RecipeRepository;
import com.abnamro.spring.data.cassandra.service.RecipeServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.Silent.class)
public class RecipeServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    @InjectMocks
    private RecipeServiceImpl recipeServiceImpl;

    @Test
    public void getAllRecipesDetailsTest() throws Exception {
        List<Recipes> mockList = getRecipesList();
        Mockito.when(recipeRepository.findAll()).thenReturn(mockList);
        Assert.assertEquals("Breakfast",mockList.get(0).getCategory());

    }




    @Test
    public void getAllRecipesDetailsTest2() throws Exception {
        List<Recipes> mockList = new ArrayList<>();
        Mockito.when(recipeRepository.findAll()).thenReturn(mockList);
       // Assert.assertEquals(HttpStatus.NO_CONTENT,);

    }
    @Test(expected = Exception.class)
    public void getAllRecipesDetailsTest3() throws Exception {
        List<Recipes> mockList = new ArrayList<>();
        Mockito.when(recipeRepository.findAll()).thenThrow(Exception.class);
        // Assert.assertEquals(HttpStatus.NO_CONTENT,);

    }

    @Test
    public void searchByCategoryTest() throws Exception {
        List<Recipes> mockList = getRecipesList();
        Mockito.when(recipeRepository.findAll()).thenReturn(mockList);
        Assert.assertEquals("Breakfast",mockList.get(0).getCategory());

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
}
