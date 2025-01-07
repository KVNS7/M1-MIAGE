package org.example.cours3;

import org.example.cards.cours3.Card;
import static org.example.cards.cours3.Card.Suit.*;
import static org.example.cards.cours3.Card.Rank.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CardTests {

    private Card aceOfHearts;

    // inutile pour un cas si simple mais permet d'illustrer un peu les tests
    @Before
    public void setUp() {
        aceOfHearts = new Card(ACE, HEARTS);
    }

    @After
    public void tearDown() {
        aceOfHearts = null;
    }

    @Test
    public void rankIsCorrect() {
        assertEquals(ACE, aceOfHearts.rank());
    }

    @Test
    public void suitIsCorrect() {
        assertEquals(HEARTS, aceOfHearts.suit());
    }
    
}
