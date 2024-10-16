package com.example.cocktails.entity;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "instructions")
public class Instruction {

    @EmbeddedId
    private InstructionKey key;

    @Column(name = "amount_cl")
    private Integer amountCL;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ingredient_id", insertable=false, updatable=false)
    private Ingredient ingredient;

    protected Instruction() {
        // empty
    }

    public Instruction(InstructionKey key, Integer amountCL, Ingredient ingredient) {
        this.key = key;
        this.amountCL = amountCL;
        this.ingredient = ingredient;
    }

    public Integer getAmountCL() {
        return amountCL;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instruction that = (Instruction) o;
        return Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(key);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (amountCL != null) {
            sb.append(amountCL);
            sb.append("cl ");
        }
        sb.append(ingredient.getName());

        return sb.toString();
    }

}
