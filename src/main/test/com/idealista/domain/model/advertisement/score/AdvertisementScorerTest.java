package com.idealista.domain.model.advertisement.score;

import com.idealista.domain.model.advertisement.Advertisement;
import com.idealista.domain.model.advertisement.Description;
import com.idealista.domain.model.advertisement.score.description.DescriptionScorer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.idealista.domain.model.advertisement.Typology.FLAT;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdvertisementScorerTest {
    @Mock
    private PhotoScorer photoScorer;
    @Mock
    private DescriptionScorer descriptionScorer;
    @InjectMocks
    private AdvertisementScorer advertisementScorer;

    @Test
    void score() {
        Advertisement advertisement = new Advertisement(new Description("AnyText"), FLAT);
        advertisement.addStandardPhotos(asList("AnyUri"));
        advertisement.addHighDefinitionPhotos(asList("AnyHdUri"));
        when(photoScorer.score(advertisement.getPhotoList())).thenReturn(10);
        when(descriptionScorer.score(FLAT,advertisement.getDescription())).thenReturn(10);

        verifyScore(20, advertisementScorer.score(advertisement));
    }

    @Test
    void emptyAdvertisement() {
        Advertisement advertisement = new Advertisement(new Description(""), FLAT);
        when(photoScorer.score(advertisement.getPhotoList())).thenReturn(-10);
        when(descriptionScorer.score(FLAT,advertisement.getDescription())).thenReturn(0);

        verifyScore(0, advertisementScorer.score(advertisement));
    }


    private void verifyScore(int expectedScore, int score) {
        assertEquals(expectedScore, score);
    }
}