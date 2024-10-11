package com.example.cocktails.entity;

import jakarta.persistence.*;

import java.io.*;
import java.util.*;

@Embeddable
public class InstructionKey implements Serializable {

    @Column(name = "cocktail_id")
    private Long cocktailId;

    @Column(name = "ingredient_id")
    private Long ingredientId;

//    @ManyToOne
//    @JoinColumn(name = "cocktail_id", referencedColumnName = "cocktail_id")
//    private Cocktail cocktail;

//    @ManyToOne
//    @JoinColumn(name = "ingredient_id", referencedColumnName = "ingredient_id")
//    private Ingredient ingredient;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstructionKey that = (InstructionKey) o;
        return Objects.equals(cocktailId, that.cocktailId) && Objects.equals(ingredientId, that.ingredientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cocktailId, ingredientId);
    }

    public Long getCocktailId() {
        return cocktailId;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

}
