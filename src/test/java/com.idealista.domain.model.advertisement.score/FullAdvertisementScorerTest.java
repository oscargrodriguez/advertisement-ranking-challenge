package com.idealista.domain.model.advertisement.score;

import com.idealista.domain.model.advertisement.ChaletAdvertisement;
import com.idealista.domain.model.advertisement.Description;
import com.idealista.domain.model.advertisement.FlatAdvertisement;
import com.idealista.domain.model.advertisement.GarageAdvertisement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FullAdvertisementScorerTest {

    private FullAdvertisementScorer fullAdScorer;

    @BeforeEach
    void setUp() {
        fullAdScorer = new FullAdvertisementScorer();
    }

    @Test
    void garageWithEmptyDescription() {
        GarageAdvertisement advertisement = new GarageAdvertisement(1);
        advertisement.addHighDefinitionPhotos(Arrays.asList("AnyUri"));
        verifyScore(40, fullAdScorer.score(advertisement));
    }

    @Test
    void garage() {
        GarageAdvertisement advertisement = new GarageAdvertisement(1, new Description("AnyText"));
        advertisement.addPhotos(Arrays.asList(), Arrays.asList("AnyUri"));
        verifyScore(40, fullAdScorer.score(advertisement));
    }

    @Test
    void garageWithNoPhotos() {
        GarageAdvertisement advertisement = new GarageAdvertisement(1);
        verifyScore(0, fullAdScorer.score(advertisement));
    }

    @Test
    void flatWithEmptyDescription() {
        FlatAdvertisement advertisement = new FlatAdvertisement(1);
        verifyScore(0, fullAdScorer.score(advertisement));
    }

    @Test
    void chaletWithEmptyDescription() {
        ChaletAdvertisement advertisement = new ChaletAdvertisement(1);
        verifyScore(0, fullAdScorer.score(advertisement));
    }

    @Test
    void flatWithEmptyPhotos() {
        FlatAdvertisement advertisement = new FlatAdvertisement(1, new Description("AnyText"));
        verifyScore(0, fullAdScorer.score(advertisement));
    }

    @Test
    void chaletWithEmptyPhotos() {
        ChaletAdvertisement advertisement = new ChaletAdvertisement(1, new Description("AnyText"));
        verifyScore(0, fullAdScorer.score(advertisement));
    }

    @Test
    void flatWithEmptyHouseSize() {
        FlatAdvertisement advertisement = new FlatAdvertisement(1, new Description("AnyText"));
        advertisement.addStandardPhotos(Arrays.asList("AnyUri"));
        verifyScore(0, fullAdScorer.score(advertisement));
    }

    @Test
    void chaletWithEmptyHouseSize() {
        ChaletAdvertisement advertisement = new ChaletAdvertisement(1, new Description("AnyText"));
        advertisement.addStandardPhotos(Arrays.asList("AnyUri"));
        verifyScore(0, fullAdScorer.score(advertisement));
    }

    @Test
    void flatFully() {
        FlatAdvertisement advertisement = new FlatAdvertisement(1, new Description("AnyText"), 100);
        advertisement.addStandardPhotos(Arrays.asList("AnyUri"));
        verifyScore(40, fullAdScorer.score(advertisement));
    }

    @Test
    void chaletWithEmptyGardenSize() {
        ChaletAdvertisement advertisement = new ChaletAdvertisement(1, new Description("AnyText"), 100, null);
        advertisement.addStandardPhotos(Arrays.asList("AnyUri"));
        verifyScore(0, fullAdScorer.score(advertisement));
    }

    @Test
    void chaletFully() {
        ChaletAdvertisement advertisement = new ChaletAdvertisement(1, new Description("AnyText"), 100, 200);
        advertisement.addStandardPhotos(Arrays.asList("AnyUri"));
        verifyScore(40, fullAdScorer.score(advertisement));
    }

    private void verifyScore(int expectedScore, int score) {
        assertEquals(expectedScore, score);
    }
}