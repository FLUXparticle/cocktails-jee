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

    @Override
    public int compareTo(Ingredient other) {
        return name.compareTo(other.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
