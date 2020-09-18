package com.idealista.domain.score;

import com.idealista.domain.model.advertisement.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FullFlatVerifierTest {
    @Mock
    SizeVerifier sizeVerifier;
    @InjectMocks
    private FullVerifier fullVerifier;


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
        when(sizeVerifier.verify(advertisement)).thenReturn(true);
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