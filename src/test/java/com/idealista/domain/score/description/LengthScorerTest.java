package com.idealista.domain.score.description;

import com.idealista.domain.model.advertisement.ChaletAdvertisement;
import com.idealista.domain.model.advertisement.Description;
import com.idealista.domain.model.advertisement.FlatAdvertisement;
import com.idealista.domain.model.advertisement.GarageAdvertisement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Random;

import static com.idealista.domain.model.advertisement.Typology.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LengthScorerTest {

    private static final int DEFAULT_SCORE = 0;
    private static final int FLAT_SHORT_DESCRIPTION_SCORE = 0;
    private static final int FLAT_MEDIUM_DESCRIPTION_SCORE = 10;
    private static final int FLAT_LARGE_DESCRIPTION_SCORE = 30;
    private static final int CHALET_LARGE_DESCRIPTION_SCORE = 20;

    private LengthScorer lengthScorer;

    @BeforeEach
    void setUp() {
        lengthScorer = new LengthScorer();
        ReflectionTestUtils.setField(lengthScorer, "defaultScore", DEFAULT_SCORE);
        ReflectionTestUtils.setField(lengthScorer, "flatShort", FLAT_SHORT_DESCRIPTION_SCORE);
        ReflectionTestUtils.setField(lengthScorer, "flatMedium", FLAT_MEDIUM_DESCRIPTION_SCORE);
        ReflectionTestUtils.setField(lengthScorer, "flatLarge", FLAT_LARGE_DESCRIPTION_SCORE);
        ReflectionTestUtils.setField(lengthScorer, "chaletLarge", CHALET_LARGE_DESCRIPTION_SCORE);
    }

    @Test
    void shortFlat() {
        FlatAdvertisement flatAdvertisement = new FlatAdvertisement(1, new Description(generateRandomText(10)), null);
        verifyScore(FLAT_SHORT_DESCRIPTION_SCORE, lengthScorer.score(flatAdvertisement));
        flatAdvertisement = new FlatAdvertisement(1, new Description(generateRandomText(19)), null);
        verifyScore(FLAT_SHORT_DESCRIPTION_SCORE, lengthScorer.score(flatAdvertisement));
    }

    @Test
    void mediumFlat() {
        FlatAdvertisement flatAdvertisement = new FlatAdvertisement(1, new Description(generateRandomText(30)), null);
        verifyScore(FLAT_MEDIUM_DESCRIPTION_SCORE, lengthScorer.score(flatAdvertisement));
        flatAdvertisement = new FlatAdvertisement(1, new Description(generateRandomText(20)), null);
        verifyScore(FLAT_MEDIUM_DESCRIPTION_SCORE, lengthScorer.score(flatAdvertisement));
        flatAdvertisement = new FlatAdvertisement(1, new Description(generateRandomText(49)), null);
        verifyScore(FLAT_MEDIUM_DESCRIPTION_SCORE, lengthScorer.score(flatAdvertisement));
    }

    @Test
    void largeFlat() {
        FlatAdvertisement flatAdvertisement = new FlatAdvertisement(1, new Description(generateRandomText(80)), null);
        verifyScore(FLAT_LARGE_DESCRIPTION_SCORE, lengthScorer.score(flatAdvertisement));
        flatAdvertisement = new FlatAdvertisement(1, new Description(generateRandomText(50)), null);
        verifyScore(FLAT_LARGE_DESCRIPTION_SCORE, lengthScorer.score(flatAdvertisement));
    }

    @Test
    void largeChalet() {
        ChaletAdvertisement advertisement = new ChaletAdvertisement(1, new Description(generateRandomText(80)), null, null);
        verifyScore(CHALET_LARGE_DESCRIPTION_SCORE, lengthScorer.score(advertisement));
        advertisement = new ChaletAdvertisement(1, new Description(generateRandomText(50)), null, null);
        verifyScore(CHALET_LARGE_DESCRIPTION_SCORE, lengthScorer.score(advertisement));
    }

    @Test
    void shortChalet() {
        ChaletAdvertisement advertisement = new ChaletAdvertisement(1, new Description("AnyShortText"), null, null);
        verifyScore(DEFAULT_SCORE, lengthScorer.score(advertisement));
    }

    @Test
    void garage() {
        GarageAdvertisement advertisement = new GarageAdvertisement(1, new Description("AnyShortText"));
        verifyScore(DEFAULT_SCORE, lengthScorer.score(advertisement));
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