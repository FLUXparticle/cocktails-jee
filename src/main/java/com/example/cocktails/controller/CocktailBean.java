package com.example.cocktails.controller;

import com.example.cocktails.entity.*;
import com.example.cocktails.service.*;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.*;

import static java.util.Collections.*;

@Named
@RequestScoped
public class CocktailBean implements Serializable {

    @Inject
    private CocktailService cocktailService;

    private List<Cocktail> cocktails;

    public List<Cocktail> getCocktails() {
        if (cocktails == null) {
            cocktails = cocktailService.getAllCocktails();
        }
        return cocktails;
    }

    // Weitere Methoden nach Bedarf, z.B. zum Hinzufügen oder Anzeigen eines Cocktails
}