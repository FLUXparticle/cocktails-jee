package com.example.cocktails.service;

import com.example.cocktails.entity.*;
import jakarta.annotation.*;
import jakarta.ejb.*;
import jakarta.enterprise.context.*;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@SessionScoped
public class FridgeSession implements Serializable {

    private final Set<Ingredient> fridgeIngredients = new HashSet<>();

    public Set<Ingredient> getFridgeIngredients() {
        return fridgeIngredients;
    }

    public void addIngredient(Ingredient ingredient) {
        fridgeIngredients.add(ingredient);
    }

    public void removeIngredient(Ingredient ingredient) {
        fridgeIngredients.remove(ingredient);
    }

}
