package com.idealista.domain.model.advertisement.score.description;

import com.idealista.domain.model.advertisement.Advertisement;
import com.idealista.domain.model.advertisement.Description;
import com.idealista.domain.model.advertisement.Typology;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.idealista.domain.model.advertisement.Typology.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FullAdScorerTest {

    private FullAdScorer fullAdScorer;

    @BeforeEach
    void setUp() {
        fullAdScorer = new FullAdScorer();
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

    private void verifyScore(int expectedScore, int score) {
        assertEquals(expectedScore, score);
    }
}