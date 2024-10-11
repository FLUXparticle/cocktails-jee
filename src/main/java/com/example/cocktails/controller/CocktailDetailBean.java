package com.example.cocktails.controller;

import com.example.cocktails.entity.*;
import com.example.cocktails.service.*;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class CocktailDetailBean implements Serializable {

    @Inject
    private CocktailService cocktailService;

    private Long id;
    private Cocktail cocktail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cocktail getCocktail() {
        return cocktail;
    }

    public void loadCocktail() {
        if (id != null) {
            cocktail = cocktailService.getCocktailById(id);
        }
    }

}
