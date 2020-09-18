package com.idealista.domain.score;

import com.idealista.domain.model.advertisement.ChaletAdvertisement;
import com.idealista.domain.model.advertisement.Description;
import com.idealista.domain.model.advertisement.FlatAdvertisement;
import com.idealista.domain.model.advertisement.GarageAdvertisement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FullFlatScorerTest {

    public static final int FULL_SCORE = 40;
    private FullScorer fullAdScorer;

    @BeforeEach
    void setUp() {
        fullAdScorer = new FullScorer();
        ReflectionTestUtils.setField(fullAdScorer, "fullScore", FULL_SCORE);
    }

    @Test
    void emptyDescription() {
        verifyEmptyScore(fullAdScorer.score(flatWithNoDescription()));
    }

    @Test
    void descriptionEmptyPhotos() {
        FlatAdvertisement advertisement = new FlatAdvertisement(1, new Description("AnyText"), null);
        verifyEmptyScore(fullAdScorer.score(advertisement));
    }

    @Test
    void descriptionPhotosEmptyHouseSize() {
        FlatAdvertisement advertisement = new FlatAdvertisement(1, new Description("AnyText"), null);
        advertisement.addStandardPhotos(Arrays.asList("AnyUri"));
        verifyEmptyScore(fullAdScorer.score(advertisement));
    }

    @Test
    void descriptionPhotosHouseSize() {
        FlatAdvertisement advertisement = new FlatAdvertisement(1, new Description("AnyText"), 100);
        advertisement.addStandardPhotos(Arrays.asList("AnyUri"));
        verifyFullScore(fullAdScorer.score(advertisement));
    }

    private FlatAdvertisement flatWithNoDescription() {
        return new FlatAdvertisement(1, null, null);
    }

    private void verifyFullScore(int score) {
        verifyScore(FULL_SCORE, score);
    }

    private void verifyEmptyScore(int score) {
        verifyScore(0, score);
    }

    private void verifyScore(int expectedScore, int score) {
        assertEquals(expectedScore, score);
    }
}