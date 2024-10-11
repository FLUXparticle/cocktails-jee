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
public class CocktailSearchBean implements Serializable {

    @Inject
    private CocktailService cocktailService;

    private String query;
    private Collection<Cocktail> results;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Collection<Cocktail> getResults() {
        return results;
    }

    public void search() {
        if (query != null && !query.trim().isEmpty()) {
            results = cocktailService.search(query);
        }
    }

}
