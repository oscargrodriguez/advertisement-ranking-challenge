package com.idealista.domain.score;

import com.idealista.domain.model.advertisement.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FullChaletVerifierTest {

    private FullVerifier fullVerifier;

    @BeforeEach
    void setUp() {
        fullVerifier = new FullVerifier();
    }

    @Test
    void emptyDescription() {
        ChaletAdvertisement advertisement = new ChaletAdvertisement(1, null, null, null);
        verifyNonFull(advertisement);
    }


    @Test
    void descriptionEmptyPhotos() {
        ChaletAdvertisement advertisement = new ChaletAdvertisement(1, new Description("AnyText"), null, null);
        verifyNonFull(advertisement);
    }

    @Test
    void descriptionPhotosEmptyHouseSize() {
        ChaletAdvertisement advertisement = new ChaletAdvertisement(1, new Description("AnyText"), null, null);
        advertisement.addStandardPhotos(Arrays.asList("AnyUri"));
        verifyNonFull(advertisement);
    }

    @Test
    void descriptionPhotosHouseSizeEmptyGardenSize() {
        ChaletAdvertisement advertisement = new ChaletAdvertisement(1, new Description("AnyText"), 100, null);
        advertisement.addStandardPhotos(Arrays.asList("AnyUri"));
        verifyNonFull(advertisement);
    }

    @Test
    void descriptionPhotosHouseSizeGardenSize() {
        ChaletAdvertisement advertisement = new ChaletAdvertisement(1, new Description("AnyText"), 100, 200);
        advertisement.addStandardPhotos(Arrays.asList("AnyUri"));
        verifyFull(advertisement);
    }

    private void verifyNonFull(Advertisement advertisement) {
        assertEquals(false,fullVerifier.verify(advertisement));
    }

    private void verifyFull(Advertisement advertisement) {
        assertEquals(true,fullVerifier.verify(advertisement));
    }
}