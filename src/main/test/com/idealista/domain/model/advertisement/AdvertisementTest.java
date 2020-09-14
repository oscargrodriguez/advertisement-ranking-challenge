package com.idealista.domain.model.advertisement;

import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
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
    void onePhoto() {
        Advertisement advertisement = new Advertisement();
        advertisement.addPhotos(asList("AnyUri"));
        verifyScore(10, advertisement.score());
    }

    @Test
    void oneHighDefinitionPhoto() {
        Advertisement advertisement = new Advertisement();
        advertisement.addHighDefinitionPhotos(asList(("AnyUri")));
        verifyScore(20, advertisement.score());
    }

    @Test
    void severalPhotos() {
        Advertisement advertisement = new Advertisement();
        advertisement.addHighDefinitionPhotos(asList("AnyUriOne","AnyUriTwo"));
        advertisement.addPhotos(asList("AnyHdUriOne","AnyHdUriTwo"));
        verifyScore(60, advertisement.score());
    }

    @Test
    void descriptiveText() {
        Advertisement advertisement = new Advertisement("Any text");
        verifyScore(5, advertisement.score());
    }
}
