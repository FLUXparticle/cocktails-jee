package com.example.cocktails.entity;

import jakarta.persistence.*;

import java.util.*;

@Entity
public class Cocktail implements Comparable<Cocktail> {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn // (name="cocktail_id")
    private Collection<Instruction> instructions;

    protected Cocktail() {
        // Required by JAXB
    }

    public Cocktail(String name, List<Instruction> instructions) {
        this.name = name;
        this.instructions = instructions;
    }

    @Override
    public int compareTo(Cocktail other) {
        return name.compareTo(other.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cocktail cocktail = (Cocktail) o;
        return id.equals(cocktail.id) && name.equals(cocktail.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Collection<Instruction> getInstructions() {
        return instructions;
    }

}
