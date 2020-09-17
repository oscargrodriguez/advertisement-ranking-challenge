package com.idealista.domain.score.description;

import com.idealista.domain.model.advertisement.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static com.idealista.domain.model.advertisement.Typology.CHALET;
import static com.idealista.domain.model.advertisement.Typology.FLAT;
import static org.junit.jupiter.api.Assertions.*;

class LengthScorerTest {

    private LengthScorer lengthScorer;

    @BeforeEach
    void setUp() {
        lengthScorer = new LengthScorer();
    }

    @Test
    void shortFlat() {
        verifyScore(0, lengthScorer.score(FLAT, new Description(generateRandomText(10))));
        verifyScore(0, lengthScorer.score(FLAT, new Description(generateRandomText(19))));
    }

    @Test
    void mediumFlat() {
        verifyScore(10, lengthScorer.score(FLAT, new Description(generateRandomText(30))));
        verifyScore(10, lengthScorer.score(FLAT, new Description(generateRandomText(20))));
        verifyScore(10, lengthScorer.score(FLAT, new Description(generateRandomText(49))));
    }

    @Test
    void largeFlat() {
        verifyScore(30, lengthScorer.score(FLAT, new Description(generateRandomText(80))));
        verifyScore(30, lengthScorer.score(FLAT, new Description(generateRandomText(50))));
    }

    @Test
    void largeChalet() {
        verifyScore(20, lengthScorer.score(CHALET, new Description(generateRandomText(80))));
        verifyScore(20, lengthScorer.score(CHALET, new Description(generateRandomText(50))));
    }

    @Test
    void shortChalet() {
        verifyScore(0, lengthScorer.score(CHALET, new Description("AnyShortText")));
    }

    private void verifyScore(int expectedScore, int score) {
        assertEquals(expectedScore, score);
    }

    private String generateRandomText(int length) {
        String fakeText = "";
        Random random = new Random();
        String chars = "abcxyz";
        for (int i = 0; i < length; i++) {
            fakeText += chars.charAt(random.nextInt(chars.length()));
        }
        return fakeText;
    }
}