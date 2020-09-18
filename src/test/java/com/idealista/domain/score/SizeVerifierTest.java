package com.idealista.domain.score;

import com.idealista.domain.model.advertisement.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SizeVerifierTest {
    private SizeVerifier sizeVerifier;

    @BeforeEach
    void setUp() {
        sizeVerifier = new SizeVerifier();
    }

    @Test
    void garage() {
        GarageAdvertisement advertisement = new GarageAdvertisement(1, new Description("AnyText"));
        verifySized(advertisement);
    }

    @Test
    void flatEmptyHouseSizeEmptyGardenSize() {
        FlatAdvertisement advertisement = new FlatAdvertisement(1, new Description("AnyText"), null);
        verifyNoSized(advertisement);
    }

    @Test
    void flatWithHouseSizeEmptyGardenSize() {
        FlatAdvertisement advertisement = new FlatAdvertisement(1, new Description("AnyText"), 100);
        verifySized(advertisement);
    }

    @Test
    void chaletEmptyHouseSizeEmptyGardenSize() {
        ChaletAdvertisement advertisement = new ChaletAdvertisement(1, new Description("AnyText"), null, null);
        verifyNoSized(advertisement);
    }

    @Test
    void chaletWithHouseSizeEmptyGardenSize() {
        ChaletAdvertisement advertisement = new ChaletAdvertisement(1, new Description("AnyText"), 100, null);
        verifyNoSized(advertisement);
    }

    @Test
    void chaletWithHouseSizeGardenSize() {
        ChaletAdvertisement advertisement = new ChaletAdvertisement(1, new Description("AnyText"), 100, 200);
        verifySized(advertisement);
    }

    private void verifyNoSized(Advertisement advertisement) {
        assertEquals(false, sizeVerifier.verify(advertisement));
    }

    private void verifySized(Advertisement advertisement) {
        assertEquals(true, sizeVerifier.verify(advertisement));
    }
}