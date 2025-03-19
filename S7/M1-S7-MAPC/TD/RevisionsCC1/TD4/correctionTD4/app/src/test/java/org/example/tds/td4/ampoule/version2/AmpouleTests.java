package org.example.tds.td4.ampoule.version2;

import static org.junit.Assert.assertEquals;

import java.util.function.Consumer;

import org.junit.Before;
import org.junit.Test;

import io.vavr.control.Option;

import org.example.tds.td4.ampoule.version2.api.Ampoule;

public class AmpouleTests {
    private Ampoule a;

    private static final Consumer<String> afficheur = System.out::println;
    private static final int LUMEN = 2500;

    private enum ModeTest { SIMPLE, AVANCE; }

    // choisir un des deux modes

    // private static final ModeTest MODE_TEST = ModeTest.SIMPLE;
    private static final ModeTest MODE_TEST = ModeTest.AVANCE;

    @Before
    public void setUp() {
        a = new Ampoule(afficheur, LUMEN);
    }

    private int repetition(int times) {
        int allumagesReussis = 0;
        Option<Integer> res;
        for (int n = 0; n < times; n++) {
            res = a.allumer();
            // Ici on a heureusement le résultat qu'on peut tester (Option présent et pas 0)
            // car le contexte "masque" les erreurs sinon.
            // Cela serait plus simple si l'erreur était observable au niveau de ce qui est testé
            // c-à-d l'Ampoule.
            if (MODE_TEST == ModeTest.SIMPLE) {
                if (res.isDefined()) { allumagesReussis++; }
            } else {
                if (res.isDefined() && res.get() != 0) { allumagesReussis++; }
            }
            a.eteindre();
        }
        return allumagesReussis;
    }

    @Test
    public void neCassePasAvant3() {
        assertEquals(3, repetition(3));
    }

    @Test
    public void casseApres3() {
        // Comme le contexte "masque" les erreurs, on joue sur les retours d'allumage.
        // Si on vérifie bien Option présent ET non 0 (dans repetition) alors on peut tester 4 donne 3.
        // Si on y vérifie seulement Option présent alors on ne peut tester que x >=5 donne 4.
        if (MODE_TEST == ModeTest.SIMPLE) {
            assertEquals(4, repetition(5));
        } else {
            assertEquals(3, repetition(4));
        }
    }

}
