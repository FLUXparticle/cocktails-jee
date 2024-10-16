package com.example.cocktails.service;

import com.example.cocktails.dao.CocktailDAO;
import com.example.cocktails.dao.IngredientDAO;
import com.example.cocktails.entity.Cocktail;
import com.example.cocktails.entity.Ingredient;
import com.example.cocktails.entity.Instruction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CocktailServiceTest {

    @InjectMocks
    private CocktailService cocktailService;

    @Mock
    private CocktailDAO cocktailDAO;

    @Mock
    private IngredientDAO ingredientDAO;

    @Test
    void testGetAllCocktails() {
        // Arrange
        List<Cocktail> mockCocktails = Arrays.asList(
                new Cocktail(1L, "Mojito", Collections.emptyList()),
                new Cocktail(2L, "Margarita", Collections.emptyList())
        );

        when(cocktailDAO.findAll()).thenReturn(mockCocktails);

        // Act
        Collection<Cocktail> result = cocktailService.getAllCocktails();

        // Assert
        assertNotNull(result, "Die Rückgabe darf nicht null sein.");
        assertEquals(2, result.size(), "Es sollten 2 Cocktails zurückgegeben werden.");
        verify(cocktailDAO, times(1)).findAll();
    }

    @Test
    void testGetCocktail() {
        // Arrange
        Long cocktailId = 1L;

        // Act

        // Assert
        // TODO Der zurückgegebene Cocktail darf nicht null sein.
        // TODO Der Name des Cocktails sollte Mojito sein.
        verify(cocktailDAO, times(1)).findById(cocktailId);
    }

    @Test
    void testGetAllIngredients() {
        // Arrange

        // Act

        // Assert
        // TODO Die Rückgabe darf nicht null sein.
        // TODO Es sollten 2 Zutaten zurückgegeben werden.
        verify(ingredientDAO, times(1)).findAll();
    }

    @Test
    void testSearch_CocktailsAndIngredients() {
        // Arrange
        String query = "Mojito";

        // Simulate instructions

        // Simulate cocktails containing the ingredient

        // Act

        // Assert
        // TODO Die Rückgabe darf nicht null sein.
        // TODO Es sollte 1 Cocktail zurückgegeben werden.
        // TODO "Mojito sollte in den Ergebnissen enthalten sein.

        verify(cocktailDAO, times(1)).findByNameContains(query);
        verify(ingredientDAO, times(1)).findByNameContains(query);
        verify(cocktailDAO, times(1)).findAll();
    }

}
