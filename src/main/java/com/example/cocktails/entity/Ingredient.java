package com.example.cocktails.entity;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "ingredients")
public class Ingredient implements Comparable<Ingredient> {

    @Id
    @Column(name = "ingredient_id")
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Ingredient other) {
        return name.compareTo(other.name);
    }


}
