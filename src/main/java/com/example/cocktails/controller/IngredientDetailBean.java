package com.example.cocktails.controller;

import com.example.cocktails.entity.*;
import com.example.cocktails.service.*;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Collection;

@Named
@RequestScoped
public class IngredientDetailBean implements Serializable {

    @Inject
    private CocktailService cocktailService;

    private Long id;
    private Ingredient ingredient;
    private Collection<Cocktail> cocktails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public Collection<Cocktail> getCocktails() {
        return cocktails;
    }

    public void loadIngredient() {
        if (id != null) {
            ingredient = cocktailService.getIngredient(id);
            // Finden Sie alle Cocktails, die diese Zutat beinhalten
            cocktails = cocktailService.getAllCocktailsWithIngredient(id);
        }
    }

}
