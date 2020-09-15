package com.idealista.domain.model.advertisement.score;

import com.idealista.domain.model.advertisement.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.idealista.domain.model.advertisement.Typology.CHALET;
import static com.idealista.domain.model.advertisement.Typology.FLAT;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FullAdvertisementScorerTest {

    private FullAdvertisementScorer fullAdScorer;

    @BeforeEach
    void setUp() {
        fullAdScorer = new FullAdvertisementScorer();
    }

    @Test
    void garage() {
        GarageAdvertisement advertisement = new GarageAdvertisement();
        advertisement.addHighDefinitionPhotos(Arrays.asList("AnyUri"));
        verifyScore(40, fullAdScorer.score(advertisement));
    }

    @Test
    void garageWithNoPhotos() {
        GarageAdvertisement advertisement = new GarageAdvertisement();
        verifyScore(0, fullAdScorer.score(advertisement));
    }

    @Test
    void flatWithEmptyDescription() {
        FlatAdvertisement advertisement = new FlatAdvertisement();
        verifyScore(0, fullAdScorer.score(advertisement));
    }

    @Test
    void chaletWithEmptyDescription() {
        ChaletAdvertisement advertisement = new ChaletAdvertisement();
        verifyScore(0, fullAdScorer.score(advertisement));
    }

    @Test
    void flatWithEmptyPhotos() {
        FlatAdvertisement advertisement = new FlatAdvertisement(new Description("AnyText"));
        verifyScore(0, fullAdScorer.score(advertisement));
    }

    @Test
    void chaletWithEmptyPhotos() {
        ChaletAdvertisement advertisement = new ChaletAdvertisement(new Description("AnyText"));
        verifyScore(0, fullAdScorer.score(advertisement));
    }

    @Test
    void flatWithEmptyHouseSize() {
        FlatAdvertisement advertisement = new FlatAdvertisement(new Description("AnyText"));
        advertisement.addStandardPhotos(Arrays.asList("AnyUri"));
        verifyScore(0, fullAdScorer.score(advertisement));
    }

    @Test
    void chaletWithEmptyHouseSize() {
        ChaletAdvertisement advertisement = new ChaletAdvertisement(new Description("AnyText"));
        advertisement.addStandardPhotos(Arrays.asList("AnyUri"));
        verifyScore(0, fullAdScorer.score(advertisement));
    }

    @Test
    void flatFully() {
        FlatAdvertisement advertisement = new FlatAdvertisement(new Description("AnyText"),100);
        advertisement.addStandardPhotos(Arrays.asList("AnyUri"));
        verifyScore(40, fullAdScorer.score(advertisement));
    }

    @Test
    void chaletWithEmptyGardenSize() {
        ChaletAdvertisement advertisement = new ChaletAdvertisement(new Description("AnyText"),100);
        advertisement.addStandardPhotos(Arrays.asList("AnyUri"));
        verifyScore(0, fullAdScorer.score(advertisement));
    }

    @Test
    void chaletFully() {
        ChaletAdvertisement advertisement = new ChaletAdvertisement(new Description("AnyText"),100, 200);
        advertisement.addStandardPhotos(Arrays.asList("AnyUri"));
        verifyScore(40, fullAdScorer.score(advertisement));
    }

    private void verifyScore(int expectedScore, int score) {
        assertEquals(expectedScore, score);
    }
}