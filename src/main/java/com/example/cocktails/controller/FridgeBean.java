package com.example.cocktails.controller;

import com.example.cocktails.entity.*;
import com.example.cocktails.model.*;
import com.example.cocktails.service.*;
import jakarta.enterprise.context.*;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.slf4j.*;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.*;

@Named
@RequestScoped
public class FridgeBean implements Serializable {

    @Inject
    private FridgeSession fridgeSession;

    @Inject
    private CocktailService cocktailService;

    // Hinzufügen einer Zutat zum Kühlschrank
    public void addIngredient(Long ingredientId) {
        Ingredient ingredient = cocktailService.getIngredient(ingredientId);
        if (ingredient != null) {
            fridgeSession.addIngredient(ingredient);
        }
    }

    // Entfernen einer Zutat aus dem Kühlschrank
    public void removeIngredient(Long ingredientId) {
        Ingredient ingredient = cocktailService.getIngredient(ingredientId);
        if (ingredient != null) {
            fridgeSession.removeIngredient(ingredient);
        }
    }

    // Getter für die Zutaten im Kühlschrank
    public Set<Ingredient> getFridgeIngredients() {
        return fridgeSession.getFridgeIngredients();
    }

    // Getter für die Zutaten, die nicht im Kühlschrank sind
    public List<Ingredient> getIngredientsNotInFridge() {
        Set<Ingredient> fridgeIngredients = getFridgeIngredients();
        return cocktailService.getAllIngredients().stream()
                .filter(ingredient -> !fridgeIngredients.contains(ingredient))
                .collect(Collectors.toList());
    }

/*
    public List<Cocktail> getPossibleCocktails() {
        System.out.println("getPossibleCocktails()");
        Set<Ingredient> fridgeIngredients = getFridgeIngredients();
        return cocktailService.getAllCocktails().stream()
                .filter(cocktail -> cocktail.getInstructions().stream()
                        .allMatch(instruction -> fridgeIngredients.contains(instruction.getIngredient())))
                .collect(Collectors.toList());
    }
*/

    public List<Cocktail> getPossibleCocktails() {
        System.out.println("getPossibleCocktails()");
        Set<Ingredient> fridgeIngredients = getFridgeIngredients();
        List<Cocktail> possibleCocktails = new ArrayList<>();
        for (Cocktail cocktail : cocktailService.getAllCocktails()) {
            boolean allIngredientsPresent = true;
            for (Instruction instruction : cocktail.getInstructions()) {
                if (!fridgeIngredients.contains(instruction.getIngredient())) {
                    allIngredientsPresent = false;
                    break;
                }
            }
            if (allIngredientsPresent) {
                possibleCocktails.add(cocktail);
            }
        }
        return possibleCocktails;
    }

    public List<ShoppingModel> getShoppingList() {
        Set<Ingredient> fridgeIngredients = getFridgeIngredients();

        List<ShoppingModel> shoppingList = new ArrayList<>();

        for (Cocktail cocktail : cocktailService.getAllCocktails()) {
            List<String> present = new ArrayList<>();
            List<String> missing = new ArrayList<>();

            for (Instruction instruction : cocktail.getInstructions()) {
                Ingredient ingredient = instruction.getIngredient();
                if (fridgeIngredients.contains(ingredient)) {
                    present.add(ingredient.getName());
                } else {
                    missing.add(ingredient.getName());
                }
            }

            if (!missing.isEmpty()) {
                shoppingList.add(new ShoppingModel(cocktail.getName(), present, missing));
            }
        }

        // Sortieren nach Anzahl der fehlenden Zutaten
        shoppingList.sort(comparingInt(m -> m.getMissingIngredients().size()));

        return shoppingList;
    }

}
