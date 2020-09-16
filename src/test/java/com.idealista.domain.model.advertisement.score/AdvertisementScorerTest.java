package com.idealista.domain.model.advertisement.score;

import com.idealista.domain.model.advertisement.Description;
import com.idealista.domain.model.advertisement.FlatAdvertisement;
import com.idealista.domain.model.advertisement.Typology;
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
    @Mock
    private FullAdvertisementScorer fullAdvertisementScorer;
    @InjectMocks
    private AdvertisementScorer advertisementScorer;

    @Test
    void score() {
        FlatAdvertisement advertisement = new FlatAdvertisement(1, new Description("AnyText"), null);
        advertisement.addPhotos(asList("AnyUri"), asList("AnyHdUri"));
        when(photoScorer.score(advertisement.getPhotoList())).thenReturn(10);
        when(descriptionScorer.score(FLAT, advertisement.getDescription())).thenReturn(10);
        when(fullAdvertisementScorer.score(advertisement)).thenReturn(10);

        verifyScore(30, advertisementScorer.score(advertisement));
    }

    @Test
    void emptyAdvertisement() {
        FlatAdvertisement advertisement = new FlatAdvertisement(1, new Description(""), null);
        when(photoScorer.score(advertisement.getPhotoList())).thenReturn(-10);
        when(descriptionScorer.score(FLAT, advertisement.getDescription())).thenReturn(0);
        when(fullAdvertisementScorer.score(advertisement)).thenReturn(0);

        verifyScore(0, advertisementScorer.score(advertisement));
    }

    @Test
    void maximalScore() {
        Typology typology = FLAT;
        FlatAdvertisement advertisement = new FlatAdvertisement(1, new Description("AnyText"), null);
        when(photoScorer.score(advertisement.getPhotoList())).thenReturn(60);
        when(descriptionScorer.score(typology, advertisement.getDescription())).thenReturn(60);
        when(fullAdvertisementScorer.score(advertisement)).thenReturn(40);

        verifyScore(100, advertisementScorer.score(advertisement));
    }

    private void verifyScore(int expectedScore, int score) {
        assertEquals(expectedScore, score);
    }
}