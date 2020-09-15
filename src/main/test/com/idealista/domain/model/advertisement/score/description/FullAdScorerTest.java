package com.idealista.domain.model.advertisement.score.description;

import com.idealista.domain.model.advertisement.Advertisement;
import com.idealista.domain.model.advertisement.Typology;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FullAdScorerTest {

    private FullAdScorer fullAdScorer;

    @BeforeEach
    void setUp() {
        fullAdScorer = new FullAdScorer();
    }

    @Test
    void garage() {
        Advertisement advertisement = new Advertisement(Typology.GARAGE);
        advertisement.addHighDefinitionPhotos(Arrays.asList("AnyUri"));
        verifyScore(40, fullAdScorer.score(advertisement));
    }

    @Test
    void garageWithNoPhotos() {
        Advertisement advertisement = new Advertisement(Typology.GARAGE);
        verifyScore(0, fullAdScorer.score(advertisement));
    }

    private void verifyScore(int expectedScore, int score) {
        assertEquals(expectedScore, score);
    }
}