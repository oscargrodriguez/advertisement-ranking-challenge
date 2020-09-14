package com.idealista.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdvertisementTest {

    private void verifyScore(int expectedScore, int score) {
        assertEquals(expectedScore, score);
    }

    @Test
    public void emptyAdvertisement() {
        Advertisement advertisement = new Advertisement();
        verifyScore(0, advertisement.score());
    }

    @Test
    void descriptiveText() {
        Advertisement advertisement = new Advertisement("Any text");
        verifyScore(5, advertisement.score());
    }

    @Test
    void onePhoto() {
        Photo photo = new Photo("Anyuri",false);
        Advertisement advertisement = new Advertisement();
        advertisement.addPhoto(photo);
        verifyScore(10, advertisement.score());
    }
}
