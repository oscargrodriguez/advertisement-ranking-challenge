package com.idealista.domain.model.advertisement.score;

import com.idealista.domain.model.advertisement.Advertisement;
import com.idealista.domain.model.advertisement.DescriptiveText;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.idealista.domain.model.advertisement.AdvertisementType.HOUSE;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdvertisementScoreTest {
    @Mock
    private PhotoScorer photoScorer;
    @Mock
    private DescriptiveTextScorer descriptiveTextScorer;
    @InjectMocks
    private AdvertisementScorer advertisementScorer;

    @Test
    void score() {
        Advertisement advertisement = new Advertisement(new DescriptiveText("AnyText"), HOUSE);
        advertisement.addStandardPhotos(asList("AnyUri"));
        when(photoScorer.score(advertisement.getPhotoList())).thenReturn(10);
        when(descriptiveTextScorer.score(HOUSE,advertisement.getDescriptiveText())).thenReturn(10);
        verifyScore(20, advertisementScorer.score(advertisement));
    }

    @Test
    void emptyAdvertisement() {
        Advertisement advertisement = new Advertisement(new DescriptiveText(""), HOUSE);
        when(photoScorer.score(advertisement.getPhotoList())).thenReturn(-10);
        when(descriptiveTextScorer.score(HOUSE,advertisement.getDescriptiveText())).thenReturn(0);
        verifyScore(0, advertisementScorer.score(advertisement));
    }


    private void verifyScore(int expectedScore, int score) {
        assertEquals(expectedScore, score);
    }
}