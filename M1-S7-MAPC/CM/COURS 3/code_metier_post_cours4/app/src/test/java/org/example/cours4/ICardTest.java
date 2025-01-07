package org.example.cours4;

import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.example.cards.cours4.metier.api.ICard;

public class ICardTest {

    protected void testComparator(Comparator<ICard> comparator, ICard c1, ICard c2, int expected) {
        assertEquals(expected, comparator.compare(c1, c2));
    }

    protected void testRankComparator(ICard c1, ICard c2, int expected) {
        testComparator(ICard.rankComparator(), c1, c2, expected);
    }
    
    protected void testSuitComparator(ICard c1, ICard c2, int expected) {
        testComparator(ICard.suitComparator(), c1, c2, expected);
    }
    
    protected void testRankAndThenSuitComparator(ICard c1, ICard c2, int expected) {
        testComparator(ICard.rankAndThenSuitComparator(), c1, c2, expected);
    }

}
