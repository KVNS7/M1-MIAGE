package org.example.tds.td1.memoires;

import java.util.List;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MemoireTests {

    private Document mémoireAlice;

    @Before
    public void setUp() {
        // on reviendra sur ça plus tard dans le cours (SOLID)
        Chapitre c1 = new Chapitre("Introduction", List.of("...", "..."));
        Chapitre c2 = new Chapitre("Patrons", List.of("...", "...", "..."));
        Chapitre c3 = new Chapitre("Anti-Patrons", List.of("...", "..."));
        Chapitre c4 = new Chapitre("Conclusion", List.of("..."));
        List<Chapitre> chapitres = new ArrayList<>();
        chapitres.add(c1);
        chapitres.add(c2);
        chapitres.add(c3);
        chapitres.add(c4);
        mémoireAlice = new Memoire("Patrons et Anti-Patrons", chapitres);
    }

    @After
    public void tearDown() {
        mémoireAlice = null;
    }

    @Test
    public void tailleCorrecteErroné() {
        assertEquals(7, mémoireAlice.taille());
    }

    @Test
    public void tailleCorrecteCorrigé() {
        assertEquals(8, mémoireAlice.taille());
    }
}
