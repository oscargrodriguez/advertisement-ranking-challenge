package com.idealista.domain.score;

import com.idealista.domain.model.advertisement.Advertisement;
import com.idealista.domain.model.advertisement.Description;
import com.idealista.domain.model.advertisement.FlatAdvertisement;
import com.idealista.domain.model.advertisement.FullVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FullFlatVerifierTest {

    private FullVerifier fullVerifier;

    @BeforeEach
    void setUp() {
        fullVerifier = new FullVerifier();
    }

    @Test
    void emptyDescription() {
        verifyNonFull(flatWithNoDescription());
    }

    @Test
    void descriptionEmptyPhotos() {
        verifyNonFull(new FlatAdvertisement(1, new Description("AnyText"), null));
    }

    @Test
    void descriptionPhotosEmptyHouseSize() {
        FlatAdvertisement advertisement = new FlatAdvertisement(1, new Description("AnyText"), null);
        advertisement.addStandardPhotos(Arrays.asList("AnyUri"));
        verifyNonFull(advertisement);
    }

    @Test
    void descriptionPhotosHouseSize() {
        FlatAdvertisement advertisement = new FlatAdvertisement(1, new Description("AnyText"), 100);
        advertisement.addStandardPhotos(Arrays.asList("AnyUri"));
        verifyFull(advertisement);
    }

    private FlatAdvertisement flatWithNoDescription() {
        return new FlatAdvertisement(1, null, null);
    }

    private void verifyNonFull(Advertisement advertisement) {
        assertEquals(false, fullVerifier.verify(advertisement));
    }

    private void verifyFull(Advertisement advertisement) {
        assertEquals(true, fullVerifier.verify(advertisement));
    }
}