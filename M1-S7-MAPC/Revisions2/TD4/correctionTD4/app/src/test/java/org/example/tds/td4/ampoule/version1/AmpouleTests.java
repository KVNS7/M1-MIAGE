package org.example.tds.td4.ampoule.version1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import org.example.tds.td4.ampoule.version1.api.ActionIllegale;
import org.example.tds.td4.ampoule.version1.api.Ampoule;

public class AmpouleTests {
    private Ampoule a;

    @Before
    public void setUp() {
        a = new Ampoule();
    }

    private int repetition(int times) throws ActionIllegale {
        int allumagesReussis = 0;
        for (int n = 0; n < times; n++) {
            a.allumer();
            allumagesReussis++;
            a.eteindre();
        }
        return allumagesReussis;
    }

    @Test
    public void neCassePasAvant3() {
        try {
            assertEquals(3, repetition(3));
        } catch (ActionIllegale ae) {
            fail();
        }
    }

    @Test
    public  void casseApres3() {
        assertThrows(ActionIllegale.class, () -> {
            repetition(3);
            a.allumer();
            a.eteindre();
        });
    }

}
