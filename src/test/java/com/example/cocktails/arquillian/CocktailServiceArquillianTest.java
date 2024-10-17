package com.example.cocktails.arquillian;

import com.example.cocktails.entity.*;
import com.example.cocktails.service.*;
import jakarta.inject.*;
import org.jboss.arquillian.container.test.api.*;
import org.jboss.arquillian.junit5.*;
import org.jboss.shrinkwrap.api.*;
import org.jboss.shrinkwrap.api.spec.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ArquillianExtension.class)
public class CocktailServiceArquillianTest {

    @Inject
    private CocktailService cocktailService;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackages(true, "com.example.cocktails")
//                .addAsManifestResource("arquillian-persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                ;
    }

    @Test
    public void testGetAllCocktails() {
        Collection<Cocktail> cocktails = cocktailService.getAllCocktails();
        assertNotNull(cocktails);
        assertFalse(cocktails.isEmpty());
    }

    @Test
    public void testGetCocktailById() {
        Long testId = 1L; // Stellen Sie sicher, dass dieser ID in Ihrem Testdatensatz existiert
        Cocktail cocktail = cocktailService.getCocktail(testId);
        assertNotNull(cocktail);
        assertEquals(testId, cocktail.getId());
    }

    @Test
    public void testSearchCocktails() {
        String query = "Mojito";
        Collection<Cocktail> results = cocktailService.search(query);
        assertNotNull(results);
        assertFalse(results.isEmpty());
        for (Cocktail c : results) {
            assertTrue(c.getName().contains(query) || 
                c.getInstructions().stream()
                    .anyMatch(instr -> instr.getIngredient().getName().contains(query)));
        }
    }

}
