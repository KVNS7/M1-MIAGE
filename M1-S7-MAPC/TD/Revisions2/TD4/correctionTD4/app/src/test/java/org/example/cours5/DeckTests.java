package org.example.cours5;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.example.cards.cours5.metier.api.ICard;
import org.example.cards.cours5.metier.api.ShufflableCardSource;
import org.example.cards.cours5.metier.api.ShufflableCardSourceCreator;
import org.example.cards.cours5.metier.impl.Croupier;

public class DeckTests {
 
    private ShufflableCardSourceCreator croupier = new Croupier();
    private ShufflableCardSource cardSource;

    @Before
    public void setUp() {
        cardSource = croupier.create();
    }

    @After
    public void tearDown() {
        cardSource = null;
    }   

    @Test
    public void sizeIsCorrect() {
        int nbCartesPrevues = ICard.Rank.values().length * ICard.Suit.values().length;
        for (int i = 0; i < nbCartesPrevues; i++) {
            try {
                cardSource.draw();
            } catch (AssertionError e) {
                fail();
            }
        }
        assertTrue(cardSource.isEmpty());
    }

    // TODO: more tests

}
