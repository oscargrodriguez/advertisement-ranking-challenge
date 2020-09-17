package com.idealista.domain.model.advertisement.score.photo;

import com.idealista.domain.model.advertisement.Photo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PhotoScorerTest {

    private static final int EMPTY_PENALTY = -10;
    private static final int STANDARD_SCORE = 10;
    private static final int HD_SCORE = 20;

    @Mock
    private PhotoByQualityScorer photoByQualityScorer;
    @InjectMocks
    private PhotoScorer photoScorer;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(photoScorer, "emptyPenalty", EMPTY_PENALTY);
    }

    @Test
    void onePhoto() {
        Photo photo = new Photo("AnyUri", "SD");
        when(photoByQualityScorer.score(photo)).thenReturn(STANDARD_SCORE);
        verifyScore(10, photoScorer.score(asList(photo)));
    }

    @Test
    void oneHighDefinitionPhoto() {
        Photo photo = new Photo("AnyUri", "HD");
        when(photoByQualityScorer.score(photo)).thenReturn(HD_SCORE);
        verifyScore(20, photoScorer.score(asList(photo)));
    }

    @Test
    void severalPhotos() {
        Photo hdPhotoOne = new Photo("hdPhotoOne", "HD");
        Photo hdPhotoTwo = new Photo("hdPhotoTwo", "HD");
        when(photoByQualityScorer.score(any())).thenReturn(HD_SCORE);

        verifyScore(40, photoScorer.score(asList(hdPhotoOne,hdPhotoTwo)));
    }

    @Test
    void noPhoto() {
        verifyScore(-10, photoScorer.score(asList()));
    }

    private void verifyScore(int expectedScore, int score) {
        assertEquals(expectedScore, score);
    }
}