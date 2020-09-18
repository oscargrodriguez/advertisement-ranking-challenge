package com.idealista.domain.score;

import com.idealista.domain.model.advertisement.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FullGarageVerifierTest {

    private FullVerifier fullVerifier;

    @BeforeEach
    void setUp() {
        fullVerifier = new FullVerifier();
    }

    @Test
    void garageWithEmptyDescriptionAndPhotos() {
        verifyFull(garageEmptyDescriptionWithOneHdPhoto());
    }

    @Test
    void garageWithDescriptionAndPhotos() {
        verifyFull(garageWithDescriptionAndOneStandardPhoto());
    }

    @Test
    void garageWithEmptyDescriptionNoPhotos() {
        verifyNonFull(garageWithNoDescription());
    }

    private GarageAdvertisement garageWithNoDescription() {
        return new GarageAdvertisement(1, null);
    }

    private GarageAdvertisement garageEmptyDescriptionWithOneHdPhoto() {
        GarageAdvertisement advertisement = new GarageAdvertisement(1, null);
        advertisement.addHighDefinitionPhotos(Arrays.asList("AnyUri"));
        return advertisement;
    }

    private GarageAdvertisement garageWithDescriptionAndOneStandardPhoto() {
        GarageAdvertisement advertisement = new GarageAdvertisement(1, new Description("AnyText"));
        advertisement.addPhotos(Arrays.asList(), Arrays.asList("AnyUri"));
        return advertisement;
    }

    private void verifyNonFull(Advertisement advertisement) {
        assertEquals(false, fullVerifier.verify(advertisement));
    }

    private void verifyFull(Advertisement advertisement) {
        assertEquals(true, fullVerifier.verify(advertisement));
    }
}