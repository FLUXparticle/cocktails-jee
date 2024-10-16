package com.example.cocktails.controller;

import com.example.cocktails.entity.Cocktail;
import com.example.cocktails.service.CocktailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CocktailSearchBeanTest {

    @InjectMocks
    private CocktailSearchBean cocktailSearchBean;

    @Mock
    private CocktailService cocktailService;

    @BeforeEach
    void setUp() {
        // Es gibt hier nichts manuell zu initialisieren, Mockito initialisiert alles durch die Annotationen.
    }

    @Test
    void testSearch_WithValidQuery() {
        // Arrange: Simuliere das Verhalten des CocktailService
        String searchQuery = "Mojito";
        Cocktail mojito = new Cocktail(1L, "Mojito", Collections.emptyList());
        when(cocktailService.search(searchQuery)).thenReturn(Collections.singletonList(mojito));

        // Act: Setze die Suchanfrage und führe die Suche aus
        cocktailSearchBean.setQuery(searchQuery);
        cocktailSearchBean.search();

        // Assert: Überprüfe, ob die Ergebnisse korrekt gesetzt wurden
        Collection<Cocktail> results = cocktailSearchBean.getResults();
        assertNotNull(results, "Die Ergebnisse dürfen nicht null sein.");
        assertEquals(1, results.size(), "Es sollte genau ein Ergebnis geben.");
        assertTrue(results.contains(mojito), "Mojito sollte in den Ergebnissen enthalten sein.");

        // Verifiziere, dass der Service mit der richtigen Query aufgerufen wurde
        verify(cocktailService, times(1)).search(searchQuery);
    }

    @Test
    void testSearch_WithEmptyQuery() {
        // Arrange: Simuliere ein leeres Query
        String emptyQuery = "   "; // Leerzeichen, keine sinnvolle Suche

        // Act: Setze die leere Suchanfrage und führe die Suche aus

        // Assert: Überprüfe, dass keine Suche ausgeführt wird
        // TODO Ergebnisse sollten null sein, wenn die Suchanfrage leer ist.

        // Verifiziere, dass der Service nicht aufgerufen wurde
        verify(cocktailService, never()).search(anyString());
    }

    @Test
    void testSearch_WithNullQuery() {
        // Act: Setze eine null-Suchanfrage und führe die Suche aus

        // Assert: Überprüfe, dass keine Suche ausgeführt wird
        // TODO Ergebnisse sollten null sein, wenn die Suchanfrage null ist.

        // Verifiziere, dass der Service nicht aufgerufen wurde
        verify(cocktailService, never()).search(anyString());
    }

    @Test
    void testSearch_NoResults() {
        // Arrange: Simuliere eine Suche mit keinen Ergebnissen
        String searchQuery = "NonExistentCocktail";

        // Act: Setze die Suchanfrage und führe die Suche aus

        // Assert: Überprüfe, dass die Ergebnisse leer sind
        Collection<Cocktail> results = cocktailSearchBean.getResults();
        // TODO Die Ergebnisse dürfen nicht null sein.
        // TODO Die Ergebnisse sollten leer sein.

        // Verifiziere, dass der Service aufgerufen wurde
        verify(cocktailService, times(1)).search(searchQuery);
    }

}
