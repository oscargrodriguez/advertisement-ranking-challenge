package com.idealista.domain.model.advertisement.score;

import com.idealista.domain.model.advertisement.DescriptiveText;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static com.idealista.domain.model.advertisement.AdvertisementType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DescriptiveTextScorerTest {

    private DescriptiveTextScorer descriptiveTextScorer;

    @BeforeEach
    void setUp() {
        descriptiveTextScorer = new DescriptiveTextScorer();
    }

    @Test
    void emptyText() {
        verifyScore(0, descriptiveTextScorer.score(HOUSE, new DescriptiveText()));
    }

    @Test
    void shortHouseDescriptiveText() {
        verifyScore(5, descriptiveTextScorer.score(HOUSE, new DescriptiveText(generateRandomText(10))));
        verifyScore(5, descriptiveTextScorer.score(HOUSE, new DescriptiveText(generateRandomText(19))));
    }

    @Test
    void mediumHouseDescriptiveText() {
        verifyScore(15, descriptiveTextScorer.score(HOUSE, new DescriptiveText(generateRandomText(30))));
        verifyScore(15, descriptiveTextScorer.score(HOUSE, new DescriptiveText(generateRandomText(20))));
        verifyScore(15, descriptiveTextScorer.score(HOUSE, new DescriptiveText(generateRandomText(49))));
    }

    @Test
    void largeHouseDescriptiveText() {
        verifyScore(35, descriptiveTextScorer.score(HOUSE, new DescriptiveText(generateRandomText(80))));
        verifyScore(35, descriptiveTextScorer.score(HOUSE, new DescriptiveText(generateRandomText(50))));
    }

    @Test
    void largeChaletDescriptiveText() {
        verifyScore(25, descriptiveTextScorer.score(CHALET, new DescriptiveText(generateRandomText(80))));
        verifyScore(25, descriptiveTextScorer.score(CHALET, new DescriptiveText(generateRandomText(50))));
    }

    @Test
    void shortChaletDescriptiveText() {
        verifyScore(5, descriptiveTextScorer.score(CHALET, new DescriptiveText("AnyShortText")));
    }

    @Test
    void garage() {
        verifyScore(5, descriptiveTextScorer.score(GARAGE, new DescriptiveText("AnyText")));
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