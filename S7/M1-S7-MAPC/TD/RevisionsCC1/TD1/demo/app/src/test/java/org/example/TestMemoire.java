package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestMémoire {

    @Test
    void testTailleMémoire() {

        Chapitre c1 = new Chapitre("Introduction", Arrays.asList("...","..."));
        Chapitre c2 = new Chapitre("Patrons", Arrays.asList("...","...","..."));
        Chapitre c3 = new Chapitre("Anti-Patrons", Arrays.asList("...","..."));
        Chapitre c4 = new Chapitre("Conclusion", Arrays.asList("..."));

        ArrayList<Chapitre> chapitres = new ArrayList<>(Arrays.asList(c1, c2, c3, c4));
        Memoire memoireAlice = new Memoire("Patrons et Anti-Patrons", chapitres);

        assertEquals(7, memoireAlice.taille()); // fail
        assertEquals(8, memoireAlice.taille()); // valide

    }
}