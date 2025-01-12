package fr.p10.miage.rps.tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import fr.p10.miage.rps.model.*;

public class RockPaperScissorsTest {
    private RockPaperScissors rps;

    @BeforeClass
    public void setUp() {
        rps = new RockPaperScissors();
    }

    @AfterClass
    public void tearDown() {
        rps = null;
    }

    @Parameters({ "paper", "scissors" })
    @Test
    public void testWinPlay(String p1, String p2) {
        assertEquals(rps.play(RPSEnum.valueOf(p1), RPSEnum.valueOf(p2)), Result.WIN);
    }
}
