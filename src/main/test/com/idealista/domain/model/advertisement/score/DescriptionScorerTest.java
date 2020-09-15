package com.idealista.domain.model.advertisement.score;

import com.idealista.domain.model.advertisement.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static com.idealista.domain.model.advertisement.Typology.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DescriptionScorerTest {

    private DescriptionScorer descriptionScorer;

    @BeforeEach
    void setUp() {
        descriptionScorer = new DescriptionScorer();
    }

    @Test
    void emptyText() {
        verifyScore(0, descriptionScorer.score(FLAT, new Description()));
    }

    @Test
    void shortFlat() {
        verifyScore(5, descriptionScorer.score(FLAT, new Description(generateRandomText(10))));
        verifyScore(5, descriptionScorer.score(FLAT, new Description(generateRandomText(19))));
    }

    @Test
    void mediumFlat() {
        verifyScore(15, descriptionScorer.score(FLAT, new Description(generateRandomText(30))));
        verifyScore(15, descriptionScorer.score(FLAT, new Description(generateRandomText(20))));
        verifyScore(15, descriptionScorer.score(FLAT, new Description(generateRandomText(49))));
    }

    @Test
    void largeFlat() {
        verifyScore(35, descriptionScorer.score(FLAT, new Description(generateRandomText(80))));
        verifyScore(35, descriptionScorer.score(FLAT, new Description(generateRandomText(50))));
    }

    @Test
    void largeChalet() {
        verifyScore(25, descriptionScorer.score(CHALET, new Description(generateRandomText(80))));
        verifyScore(25, descriptionScorer.score(CHALET, new Description(generateRandomText(50))));
    }

    @Test
    void shortChalet() {
        verifyScore(5, descriptionScorer.score(CHALET, new Description("AnyShortText")));
    }

    @Test
    void garage() {
        verifyScore(5, descriptionScorer.score(GARAGE, new Description("AnyText")));
    }

    @Test
    void oneKeyword() {
        verifyScore(10, descriptionScorer.score(CHALET, new Description("Text with Luminoso keyword added")));
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