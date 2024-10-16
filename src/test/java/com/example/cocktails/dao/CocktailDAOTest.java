package com.example.cocktails.dao;

import com.example.cocktails.entity.Cocktail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;

import java.util.Collection;

import static java.util.Collections.*;
import static org.junit.jupiter.api.Assertions.*;

class CocktailDAOTest {

    private static EntityManagerFactory emf;

    private EntityManager em;

    private CocktailDAO cocktailDAO;

    private Long mojitoId;

    @BeforeAll
    static void setUpBeforeClass() {
        // Eine separate Persistence Unit für Tests
        emf = Persistence.createEntityManagerFactory("CocktailPU-Test");
    }

    @AfterAll
    static void tearDownAfterClass() {
        if (emf != null) {
            emf.close();
        }
    }

    @BeforeEach
    void setUp() {
        em = emf.createEntityManager();

        cocktailDAO = new CocktailDAO();
        cocktailDAO.setEm(em);

        // Beginnt eine Transaktion und fügt Testdaten hinzu
        em.getTransaction().begin();

        Cocktail mojito = new Cocktail("Mojito", emptyList());
        em.persist(mojito);

        Cocktail margarita = new Cocktail("Margarita", emptyList());
        em.persist(margarita);

        em.getTransaction().commit();

        mojitoId = mojito.getId();
    }

    @AfterEach
    void tearDown() {
        if (em != null) {
            em.getTransaction().begin();

            // Löscht alle Cocktails nach jedem Test
            em.createQuery("DELETE FROM Cocktail").executeUpdate();

            em.getTransaction().commit();
            em.close();
        }
    }

    @Test
    void testFindAll() {
        Collection<Cocktail> cocktails = cocktailDAO.findAll();
        assertNotNull(cocktails, "Die Rückgabe darf nicht null sein.");
        assertEquals(2, cocktails.size(), "Es sollten 2 Cocktails gefunden werden.");

        assertTrue(cocktails.stream().anyMatch(c -> c.getName().equals("Mojito")), "Mojito sollte vorhanden sein.");
        assertTrue(cocktails.stream().anyMatch(c -> c.getName().equals("Margarita")), "Margarita sollte vorhanden sein.");
    }

    @Test
    void testFindById() {
        // Suche nach mojitoId
        // TODO Der gefundene Cocktail darf nicht null sein.
        // TODO Der Name des gefundenen Cocktails sollte Mojito sein.
    }

    @Test
    void testFindByNameContains() {
        // Suche nach "Moj"
        // TODO Die Rückgabe darf nicht null sein.
        // TODO Es sollte genau 1 Cocktail gefunden werden.
        // TODO Der gefundene Cocktail sollte Mojito sein.
    }

}
