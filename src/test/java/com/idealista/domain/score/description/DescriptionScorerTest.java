package com.idealista.domain.score.description;

import com.idealista.domain.model.advertisement.Description;
import com.idealista.domain.model.advertisement.Typology;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static com.idealista.domain.model.advertisement.Typology.FLAT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DescriptionScorerTest {

    public static final int EMPTY_SCORE = 0;
    public static final int NON_EMPTY_SCORE = 5;
    @Mock
    private LengthScorer lengthScorer;
    @Mock
    private KeywordScorer keywordScorer;
    @InjectMocks
    private DescriptionScorer descriptionScorer;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(descriptionScorer, "emptyScore", EMPTY_SCORE);
        ReflectionTestUtils.setField(descriptionScorer, "nonEmptyScore", NON_EMPTY_SCORE);
    }

    @Test
    void emptyText() {
        Description description = new Description();
        verifyScore(EMPTY_SCORE, descriptionScorer.score(FLAT, description));
    }

    @Test
    void score() {
        Description description = new Description("Any ");
        Typology typology = FLAT;
        when(lengthScorer.score(typology, description)).thenReturn(10);
        when(keywordScorer.score(description)).thenReturn(10);

        verifyScore(25, descriptionScorer.score(typology, description));
    }

    private void verifyScore(int expectedScore, int score) {
        assertEquals(expectedScore, score);
    }
}