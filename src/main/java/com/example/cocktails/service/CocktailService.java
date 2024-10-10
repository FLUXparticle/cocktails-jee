package com.example.cocktails.service;

import com.example.cocktails.entity.*;
import jakarta.enterprise.context.*;
import jakarta.persistence.*;

import java.util.*;

@ApplicationScoped
public class CocktailService {

    @PersistenceContext(unitName = "CocktailPU")
    private EntityManager em;

    public List<Cocktail> getAllCocktails() {
        TypedQuery<Cocktail> query = em.createQuery("SELECT c FROM Cocktail c ORDER BY c.name", Cocktail.class);
        return query.getResultList();
    }

    public Cocktail getCocktailById(Long id) {
        return em.find(Cocktail.class, id);
    }

    public void addCocktail(Cocktail cocktail) {
        em.getTransaction().begin();
        em.persist(cocktail);
        em.getTransaction().commit();
    }

    // Weitere CRUD-Methoden nach Bedarf
}