package org.example.cours3;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import org.example.cards.cours3.Deck;

public class DeckTests {
 
    private Deck deck;

    @Before
    public void setUp() {
        deck = new Deck();
    }

    @After
    public void tearDown() {
        deck = null;
    }   

    @Test
    public void sizeIsCorrect() {
        assertEquals(52,deck.size());
    }

    // TODO: more tests

}
