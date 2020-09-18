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

class FullChaletScorerTest {

    public static final int FULL_SCORE = 40;
    private FullScorer fullAdScorer;

    @BeforeEach
    void setUp() {
        fullAdScorer = new FullScorer();
        ReflectionTestUtils.setField(fullAdScorer, "fullScore", FULL_SCORE);
    }

    @Test
    void emptyDescription() {
        ChaletAdvertisement advertisement = new ChaletAdvertisement(1, null, null, null);
        verifyEmptyScore(fullAdScorer.score(advertisement));
    }


    @Test
    void descriptionEmptyPhotos() {
        ChaletAdvertisement advertisement = new ChaletAdvertisement(1, new Description("AnyText"), null, null);
        verifyEmptyScore(fullAdScorer.score(advertisement));
    }

    @Test
    void descriptionPhotosEmptyHouseSize() {
        ChaletAdvertisement advertisement = new ChaletAdvertisement(1, new Description("AnyText"), null, null);
        advertisement.addStandardPhotos(Arrays.asList("AnyUri"));
        verifyEmptyScore(fullAdScorer.score(advertisement));
    }

    @Test
    void descriptionPhotosHouseSizeEmptyGardenSize() {
        ChaletAdvertisement advertisement = new ChaletAdvertisement(1, new Description("AnyText"), 100, null);
        advertisement.addStandardPhotos(Arrays.asList("AnyUri"));
        verifyScore(0, fullAdScorer.score(advertisement));
    }

    @Test
    void descriptionPhotosHouseSizeGardenSize() {
        ChaletAdvertisement advertisement = new ChaletAdvertisement(1, new Description("AnyText"), 100, 200);
        advertisement.addStandardPhotos(Arrays.asList("AnyUri"));
        verifyFullScore(fullAdScorer.score(advertisement));
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