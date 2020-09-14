package com.idealista.domain.model.advertisement;

import com.idealista.domain.model.advertisement.Advertisement;
import com.idealista.domain.model.advertisement.Photo;
import org.junit.jupiter.api.Test;

import java.net.URI;

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
        Advertisement advertisement = new Advertisement();
        advertisement.addPhoto(new Photo(URI.create("AnyUri")));
        verifyScore(10, advertisement.score());
    }

    @Test
    void oneHighDefinitionPhoto() {
        Advertisement advertisement = new Advertisement();
        advertisement.addPhoto(new HighDefinitionPhoto(URI.create("AnyUri")));
        verifyScore(20, advertisement.score());
    }

    @Test
    void moreThanOnePhoto() {
        Advertisement advertisement = new Advertisement();
        advertisement.addPhoto(new HighDefinitionPhoto(URI.create("AnyUri")));
        advertisement.addPhoto(new Photo(URI.create("AnyOtherUri")));
        verifyScore(30, advertisement.score());
    }
}
