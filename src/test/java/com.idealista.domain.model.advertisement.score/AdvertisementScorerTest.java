package com.idealista.domain.model.advertisement.score;

import com.idealista.domain.model.advertisement.Description;
import com.idealista.domain.model.advertisement.FlatAdvertisement;
import com.idealista.domain.model.advertisement.Typology;
import com.idealista.domain.model.advertisement.score.description.DescriptionScorer;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

import static com.idealista.domain.model.advertisement.Typology.FLAT;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdvertisementScorerTest {
    private static final int MAX_SCORE = 100;
    private static final int MIN_SCORE = 0;
    @Mock
    private PhotoScorer photoScorer;
    @Mock
    private DescriptionScorer descriptionScorer;
    @Mock
    private FullAdvertisementScorer fullAdvertisementScorer;
    @InjectMocks
    private AdvertisementScorer advertisementScorer;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(advertisementScorer, "maxValue", MAX_SCORE);
        ReflectionTestUtils.setField(advertisementScorer, "minValue", MIN_SCORE);
    }


    @Test
    void score() {
        FlatAdvertisement advertisement = new FlatAdvertisement(1, null, null);
        when(photoScorer.score(advertisement.getPhotoList())).thenReturn(10);
        when(descriptionScorer.score(FLAT, advertisement.getDescription())).thenReturn(10);
        when(fullAdvertisementScorer.score(advertisement)).thenReturn(10);

        verifyScore(30, advertisementScorer.score(advertisement));
    }

    @Test
    void emptyAdvertisement() {
        FlatAdvertisement advertisement = new FlatAdvertisement(1, null, null);
        when(photoScorer.score(advertisement.getPhotoList())).thenReturn(-10);
        when(descriptionScorer.score(FLAT, advertisement.getDescription())).thenReturn(MIN_SCORE);
        when(fullAdvertisementScorer.score(advertisement)).thenReturn(MIN_SCORE);

        verifyScore(MIN_SCORE, advertisementScorer.score(advertisement));
    }

    @Test
    void when_score_over_maximal_should_return_maximal_score() {
        FlatAdvertisement advertisement = new FlatAdvertisement(1, null, null);
        when(photoScorer.score(advertisement.getPhotoList())).thenReturn(60);
        when(descriptionScorer.score(FLAT, advertisement.getDescription())).thenReturn(60);
        when(fullAdvertisementScorer.score(advertisement)).thenReturn(40);

        verifyScore(MAX_SCORE, advertisementScorer.score(advertisement));
    }

    private void verifyScore(int expectedScore, int score) {
        assertEquals(expectedScore, score);
    }
}