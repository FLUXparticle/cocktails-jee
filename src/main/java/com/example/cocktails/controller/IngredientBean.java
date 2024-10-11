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
public class IngredientBean implements Serializable {

    @Inject
    private CocktailService cocktailService;

    private Collection<Ingredient> ingredients;

    public Collection<Ingredient> getIngredients() {
        if (ingredients == null) {
            ingredients = cocktailService.getAllIngredients();
        }
        return ingredients;
    }

    // Weitere Methoden wie Hinzufügen, Bearbeiten etc. nach Bedarf

}
