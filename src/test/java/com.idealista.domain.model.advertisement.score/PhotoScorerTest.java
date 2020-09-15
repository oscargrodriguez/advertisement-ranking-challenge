package com.idealista.domain.model.advertisement.score;

import com.idealista.domain.model.advertisement.HighDefinitionPhoto;
import com.idealista.domain.model.advertisement.Photo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PhotoScorerTest {

    private PhotoScorer photoScorer;

    @BeforeEach
    void setUp() {
        photoScorer = new PhotoScorer();
    }

    @Test
    void onePhoto() {
        verifyScore(10, photoScorer.score(asList(new Photo("AnyUri"))));
    }

    @Test
    void oneHighDefinitionPhoto() {
        verifyScore(20, photoScorer.score(asList(new HighDefinitionPhoto("AnyUri"))));
    }

    @Test
    void severalPhotos() {
        verifyScore(60, photoScorer.score(
                asList(new HighDefinitionPhoto("AnyUri"),
                        new HighDefinitionPhoto("AnyUri"),
                        new Photo("AnyUri"),
                        new Photo("AnyUri"))));
    }

    @Test
    void noPhoto() {
        verifyScore(-10, photoScorer.score(asList()));
    }

    private void verifyScore(int expectedScore, int score) {
        assertEquals(expectedScore, score);
    }
}