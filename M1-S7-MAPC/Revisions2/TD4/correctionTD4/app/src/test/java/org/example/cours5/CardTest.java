package org.example.cours5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.example.cards.cours5.metier.api.ICard;
import org.example.cards.cours5.metier.impl.Card;
import static org.example.cards.cours5.metier.api.ICard.Suit.*;
import static org.example.cards.cours5.metier.api.ICard.Rank.*;

public class CardTest extends ICardTest {
    private ICard aceOfHearts;
    private ICard aceOfHearts2;
    private ICard twoOfHearts;
    private ICard twoOfSpades;

    // on pourrait rajouter des property based testing sur les relations compareTo/equals/hashCode

    @Before
    public void setUp() {
        aceOfHearts = Card.get(ACE, HEARTS);
        aceOfHearts2 = Card.get(ACE, HEARTS); // pour vérifier instance unique
        twoOfHearts = Card.get(TWO, HEARTS);
        twoOfSpades = Card.get(TWO, SPADES);
    }

    @After
    public void tearDown() {
        aceOfHearts = null;
        twoOfHearts = null;
        twoOfSpades = null;
    }

    @Test
    public void uniqueInstances() {
        assertTrue(aceOfHearts == aceOfHearts2); // on vérifie que l'on a bien une instance unique
    }

    @Test
    public void rankIsCorrect() {
        assertEquals(ACE, aceOfHearts.rank());
        assertEquals(TWO, twoOfHearts.rank());
        assertEquals(TWO, twoOfSpades.rank());
    }

    @Test
    public void suitIsCorrect() {
        assertEquals(HEARTS, aceOfHearts.suit());
        assertEquals(HEARTS, twoOfHearts.suit());
        assertEquals(SPADES, twoOfSpades.suit());
    }

    @Test
    public void rankComparatorIsCorrect() {
        testRankComparator(aceOfHearts, twoOfHearts, -1);
        testRankComparator(aceOfHearts, aceOfHearts, 0);
        testRankComparator(twoOfHearts, aceOfHearts, 1);
        testRankComparator(twoOfHearts, twoOfSpades, 0);
        testRankComparator(aceOfHearts, twoOfSpades, -1);
    }

    @Test
    public void suitComparatorIsCorrect() {
        testSuitComparator(twoOfSpades, twoOfHearts, -1);
        testSuitComparator(twoOfHearts, twoOfHearts, 0);
        testSuitComparator(twoOfHearts, twoOfSpades, 1);
        testSuitComparator(aceOfHearts, twoOfHearts, 0);
        testSuitComparator(twoOfSpades, aceOfHearts, -1);
    }

    @Test
    public void rankAndThenSuitComparatorIsCorrect() {
        testRankAndThenSuitComparator(aceOfHearts, twoOfHearts, -1);
        testRankAndThenSuitComparator(aceOfHearts, aceOfHearts, 0);
        testRankAndThenSuitComparator(twoOfHearts, aceOfHearts, 1);
        testRankAndThenSuitComparator(twoOfSpades, twoOfHearts, -1);
        testRankAndThenSuitComparator(twoOfHearts, twoOfSpades, 1);
        testRankAndThenSuitComparator(aceOfHearts, twoOfSpades, -1);
    }
}
