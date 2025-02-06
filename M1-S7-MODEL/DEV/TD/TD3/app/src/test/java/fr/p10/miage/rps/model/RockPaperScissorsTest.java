package fr.p10.miage.rps.model;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RockPaperScissorsTest {
    public RockPaperScissors rps;

    @BeforeClass
    public void setUp() {
        this.rps = new RockPaperScissors();
    }

    @AfterClass
    public void tearDown() {
        this.rps = null;
    }

    // Data Provider pour les situations de WIN
    @DataProvider(name = "winData")
    public Object[][] createWinData() {
        return new Object[][] {
            { RPSEnum.PAPER, RPSEnum.ROCK },
            { RPSEnum.ROCK, RPSEnum.SCISSORS },
            { RPSEnum.SCISSORS, RPSEnum.PAPER }
        };
    }

    // Test avec DataProvider pour WIN
    @Test(dataProvider = "winData")
    void testWinPlay(RPSEnum p1, RPSEnum p2) {
        assertEquals(rps.play(p1, p2), Result.WIN);
    }

    // Data Provider pour les situations de LOST
    @DataProvider(name = "lostData")
    public Object[][] createLostData() {
        return new Object[][] {
            { RPSEnum.ROCK, RPSEnum.PAPER },
            { RPSEnum.PAPER, RPSEnum.SCISSORS },
            { RPSEnum.SCISSORS, RPSEnum.ROCK }
        };
    }

    // Test avec DataProvider pour LOST
    @Test(dataProvider = "lostData")
    void testLostPlay(RPSEnum p1, RPSEnum p2) {
        assertEquals(rps.play(p1, p2), Result.LOST);
    }

    // Data Provider pour les situations de TIE
    @DataProvider(name = "tieData")
    public Object[][] createTieData() {
        return new Object[][] {
            { RPSEnum.ROCK, RPSEnum.ROCK },
            { RPSEnum.PAPER, RPSEnum.PAPER },
            { RPSEnum.SCISSORS, RPSEnum.SCISSORS }
        };
    }

    // Test avec DataProvider pour TIE
    @Test(dataProvider = "tieData")
    void testTiePlay(RPSEnum p1, RPSEnum p2) {
        assertEquals(rps.play(p1, p2), Result.TIE);
    }
}
