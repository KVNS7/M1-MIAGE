package org.example.tds.td4.sechoir;

import static org.junit.Assert.fail;

import org.example.tds.td4.sechoir.api.ActionIllegale;
import org.example.tds.td4.sechoir.api.Sechoir;
import org.junit.Before;
import org.junit.Test;

public class SechoirTests {

    // beaucoup de traces d'exécution correctes (infini)
    // on va vérifier deux traces
    // pour l'incorrect même principe que pour l'ampoule
    // (+ questionnement sur points d'observation à éventuellement rajouter)
    // (on le fera pour le distributeur)

    private Sechoir s;

    @Before
    public void setUp() {
        s = new Sechoir();
    }

    @Test
    public void correctRun1() {
        try {
            s.payer();
            s.fermer();
            s.secher();
            s.timeout();
        } catch (ActionIllegale ae) {
            fail();
        }
    }

    @Test
    public void correctRun2() {
        try {
            s.fermer();
            s.payer();
            s.secher();
            s.timeout();
        } catch (ActionIllegale ae) {
            fail();
        }
    }
}