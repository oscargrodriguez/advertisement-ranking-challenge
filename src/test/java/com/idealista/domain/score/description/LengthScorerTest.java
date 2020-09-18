package com.idealista.domain.score.description;

import com.idealista.domain.model.advertisement.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Random;

import static com.idealista.domain.model.advertisement.Typology.CHALET;
import static com.idealista.domain.model.advertisement.Typology.FLAT;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LengthScorerTest {

    public static final int FLAT_SHORT_DESCRIPTION_SCORE = 0;
    public static final int FLAT_MEDIUM_DESCRIPTION_SCORE = 10;
    public static final int FLAT_LARGE_DESCRIPTION_SCORE = 30;
    public static final int CHALET_LARGE_DESCRIPTION_SCORE = 20;

    private LengthScorer lengthScorer;

    @BeforeEach
    void setUp() {
        lengthScorer = new LengthScorer();
        ReflectionTestUtils.setField(lengthScorer, "shortFlat", FLAT_SHORT_DESCRIPTION_SCORE);
        ReflectionTestUtils.setField(lengthScorer, "mediumFlat", FLAT_MEDIUM_DESCRIPTION_SCORE);
        ReflectionTestUtils.setField(lengthScorer, "largeFlat", FLAT_LARGE_DESCRIPTION_SCORE);
        ReflectionTestUtils.setField(lengthScorer, "largeChalet", CHALET_LARGE_DESCRIPTION_SCORE);
    }

    @Test
    void shortFlat() {
        verifyScore(FLAT_SHORT_DESCRIPTION_SCORE, lengthScorer.score(FLAT, new Description(generateRandomText(10))));
        verifyScore(FLAT_SHORT_DESCRIPTION_SCORE, lengthScorer.score(FLAT, new Description(generateRandomText(19))));
    }

    @Test
    void mediumFlat() {
        verifyScore(FLAT_MEDIUM_DESCRIPTION_SCORE, lengthScorer.score(FLAT, new Description(generateRandomText(30))));
        verifyScore(FLAT_MEDIUM_DESCRIPTION_SCORE, lengthScorer.score(FLAT, new Description(generateRandomText(20))));
        verifyScore(FLAT_MEDIUM_DESCRIPTION_SCORE, lengthScorer.score(FLAT, new Description(generateRandomText(49))));
    }

    @Test
    void largeFlat() {
        verifyScore(FLAT_LARGE_DESCRIPTION_SCORE, lengthScorer.score(FLAT, new Description(generateRandomText(80))));
        verifyScore(FLAT_LARGE_DESCRIPTION_SCORE, lengthScorer.score(FLAT, new Description(generateRandomText(50))));
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