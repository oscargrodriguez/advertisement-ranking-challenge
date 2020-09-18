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

class FullGarageScorerTest {

    public static final int FULL_SCORE = 40;
    private FullScorer fullAdScorer;

    @BeforeEach
    void setUp() {
        fullAdScorer = new FullScorer();
        ReflectionTestUtils.setField(fullAdScorer, "fullScore", FULL_SCORE);
    }

    @Test
    void garageWithEmptyDescriptionAndPhotos() {
        verifyFullScore(fullAdScorer.score(garageEmptyDescriptionWithOneHdPhoto()));
    }

    @Test
    void garageWithDescriptionAndPhotos() {
        verifyFullScore(fullAdScorer.score(garageWithDescriptionAndOneStandardPhoto()));
    }

    @Test
    void garageWithEmptyDescriptionNoPhotos() {
        verifyEmptyScore(fullAdScorer.score(garageWithNoDescription()));
    }

    private GarageAdvertisement garageWithNoDescription() {
        return new GarageAdvertisement(1, null);
    }

    private GarageAdvertisement garageEmptyDescriptionWithOneHdPhoto() {
        GarageAdvertisement advertisement = new GarageAdvertisement(1, null);
        advertisement.addHighDefinitionPhotos(Arrays.asList("AnyUri"));
        return advertisement;
    }

    private GarageAdvertisement garageWithDescriptionAndOneStandardPhoto() {
        GarageAdvertisement advertisement = new GarageAdvertisement(1, new Description("AnyText"));
        advertisement.addPhotos(Arrays.asList(), Arrays.asList("AnyUri"));
        return advertisement;
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