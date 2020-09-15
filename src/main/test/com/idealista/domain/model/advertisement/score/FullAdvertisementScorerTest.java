package com.idealista.domain.model.advertisement.score;

import com.idealista.domain.model.advertisement.Advertisement;
import com.idealista.domain.model.advertisement.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.idealista.domain.model.advertisement.Typology.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FullAdvertisementScorerTest {

    private FullAdvertisementScorer fullAdScorer;

    @BeforeEach
    void setUp() {
        fullAdScorer = new FullAdvertisementScorer();
    }

    @Test
    void garage() {
        Advertisement advertisement = new Advertisement(GARAGE);
        advertisement.addHighDefinitionPhotos(Arrays.asList("AnyUri"));
        verifyScore(40, fullAdScorer.score(advertisement));
    }

    @Test
    void garageWithNoPhotos() {
        Advertisement advertisement = new Advertisement(GARAGE);
        verifyScore(0, fullAdScorer.score(advertisement));
    }

    @Test
    void flatWithEmptyDescription() {
        Advertisement advertisement = new Advertisement(FLAT);
        verifyScore(0, fullAdScorer.score(advertisement));
    }

    @Test
    void chaletWithEmptyDescription() {
        Advertisement advertisement = new Advertisement(CHALET);
        verifyScore(0, fullAdScorer.score(advertisement));
    }

    @Test
    void flatWithEmptyPhotos() {
        Advertisement advertisement = new Advertisement(new Description("AnyText"), FLAT);
        verifyScore(0, fullAdScorer.score(advertisement));
    }

    @Test
    void chaletWithEmptyPhotos() {
        Advertisement advertisement = new Advertisement(new Description("AnyText"), CHALET);
        verifyScore(0, fullAdScorer.score(advertisement));
    }

    @Test
    void flatWithEmptyHouseSize() {
        Advertisement advertisement = new Advertisement(new Description("AnyText"), FLAT);
        advertisement.addStandardPhotos(Arrays.asList("AnyUri"));
        verifyScore(0, fullAdScorer.score(advertisement));
    }

    @Test
    void chaletWithEmptyHouseSize() {
        Advertisement advertisement = new Advertisement(new Description("AnyText"), CHALET);
        advertisement.addStandardPhotos(Arrays.asList("AnyUri"));
        verifyScore(0, fullAdScorer.score(advertisement));
    }

    @Test
    void flatFully() {
        Advertisement advertisement = new Advertisement(new Description("AnyText"), FLAT, 100);
        advertisement.addStandardPhotos(Arrays.asList("AnyUri"));
        verifyScore(40, fullAdScorer.score(advertisement));
    }

    @Test
    void chaletWithEmptyGardenSize() {
        Advertisement advertisement = new Advertisement(new Description("AnyText"), CHALET, 100);
        advertisement.addStandardPhotos(Arrays.asList("AnyUri"));
        verifyScore(0, fullAdScorer.score(advertisement));
    }

    @Test
    void chaletFully() {
        Advertisement advertisement = new Advertisement(new Description("AnyText"), CHALET, 100, 200);
        advertisement.addStandardPhotos(Arrays.asList("AnyUri"));
        verifyScore(40, fullAdScorer.score(advertisement));
    }

    private void verifyScore(int expectedScore, int score) {
        assertEquals(expectedScore, score);
    }
}