package com.idealista.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdvertisementTest {

    @Test
    public void emptyAdvertisement() {
        Advertisement advertisement = new Advertisement();
        assertEquals(0, advertisement.score());
    }

    @Test
    void descriptiveText() {
        Advertisement advertisement = new Advertisement("Any text");
        assertEquals(5, advertisement.score());
    }
}
