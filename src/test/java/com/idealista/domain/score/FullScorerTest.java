package com.idealista.domain.score;

import com.idealista.domain.model.advertisement.FullVerifier;
import com.idealista.domain.model.advertisement.GarageAdvertisement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FullScorerTest {
    public static final int FULL_SCORE = 40;
    public static final int INCOMPLETE_SCORE = 0;
    @Mock
    private FullVerifier fullVerifier;
    @InjectMocks
    private FullScorer fullScorer;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(fullScorer, "fullScore", FULL_SCORE);
    }

    @Test
    void incomplete() {
        GarageAdvertisement advertisement = anyAdvertisement();
        when(fullVerifier.verify(advertisement)).thenReturn(false);
        verifyIncompleteScore(advertisement);
    }

    @Test
    void full() {
        GarageAdvertisement advertisement = anyAdvertisement();
        when(fullVerifier.verify(advertisement)).thenReturn(true);
        verifyFullScore(advertisement);
    }

    private void verifyFullScore(GarageAdvertisement advertisement) {
        verifyScore(FULL_SCORE, fullScorer.score(advertisement));
    }

    private void verifyIncompleteScore(GarageAdvertisement advertisement) {
        verifyScore(INCOMPLETE_SCORE, fullScorer.score(advertisement));
    }

    private void verifyScore(int expectedScore, int score) {
        assertThat(score, equalTo(expectedScore));
    }

    private GarageAdvertisement anyAdvertisement() {
        return new GarageAdvertisement(1, null);
    }

}