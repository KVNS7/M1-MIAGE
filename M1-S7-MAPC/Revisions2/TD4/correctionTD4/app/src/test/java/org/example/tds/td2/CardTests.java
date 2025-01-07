package org.example.tds.td2;

import static org.example.tds.td2.Card.Colour.*;
import static org.example.tds.td2.Card.Suit.*;
import static org.example.tds.td2.Card.Rank.*;
import static org.example.tds.td2.Card.CardEnum.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CardTests {

    private Card twoOfHearts;
    private Card kingOfSpades;
    private Card joker;

    @Before
    public void setUp() {
        twoOfHearts = new Card(TWO_OF_HEARTS);
        kingOfSpades = new Card(KING_OF_SPADES);
        joker = new Card(JOKER);
    }

    @After
    public void tearDown() {
        twoOfHearts = null;
        kingOfSpades = null;
        joker = null;
    }

    @Test
    public void rankIsCorrect() {
        assertEquals(TWO, twoOfHearts.rank());
        assertEquals(KING, kingOfSpades.rank());
    }

    @Test
    public void suitIsCorrect() {
        assertEquals(HEARTS, twoOfHearts.suit());
        assertEquals(SPADES, kingOfSpades.suit());
    }

    @Test
    public void colourIsCorrect() {
        assertEquals(RED, twoOfHearts.colour());
        assertEquals(BLACK, kingOfSpades.colour());
    }
    
}
