package com.abnamro.spring.data.cassandra.model;

import com.datastax.driver.core.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Table
public class Recipes {

    @PrimaryKey("recipe_id")
    private UUID recipeId;


    private String category;

    private LocalDateTime creationdate;

    private List<String> ingredients;

    private String instructions;

    private Integer servings;

    private String title;

    private Boolean vegetarian;

    public Recipes() {
    }

    public Recipes(UUID recipeId, String category, LocalDateTime creationdate, List<String> ingredients, String instructions, Integer servings, String title, Boolean vegetarian) {
        this.recipeId = recipeId;
        this.category = category;
        this.creationdate = creationdate;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.servings = servings;
        this.title = title;
        this.vegetarian = vegetarian;
    }

    public UUID getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(UUID recipeId) {
        this.recipeId = recipeId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(LocalDateTime creationdate) {
        this.creationdate = creationdate;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(Boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    @Override
    public String toString() {
        return "Recipes{" +
                "recipeId=" + recipeId +
                ", category='" + category + '\'' +
                ", creationdate=" + creationdate +
                ", ingredients=" + ingredients +
                ", instructions='" + instructions + '\'' +
                ", servings=" + servings +
                ", title='" + title + '\'' +
                ", vegetarian=" + vegetarian +
                '}';
    }
}
