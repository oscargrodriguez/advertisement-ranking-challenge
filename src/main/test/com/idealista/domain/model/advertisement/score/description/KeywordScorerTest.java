package com.idealista.domain.model.advertisement.score.description;

import com.idealista.domain.model.advertisement.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KeywordScorerTest {
    private KeywordScorer keywordScorer;

    @BeforeEach
    void setUp() {
        keywordScorer = new KeywordScorer();
    }

    @Test
    void oneKeyword() {
        verifyScore(5, keywordScorer.score(new Description("Text with Luminoso keyword added")));
        verifyScore(5, keywordScorer.score(new Description("Text with Nuevo keyword added")));
        verifyScore(5, keywordScorer.score(new Description("Text with Céntrico keyword added")));
        verifyScore(5, keywordScorer.score(new Description("Text with Reformado keyword added")));
        verifyScore(5, keywordScorer.score(new Description("Text with Ático keyword added")));
    }

    @Test
    void oneKeywordLowerCase() {
        verifyScore(5, keywordScorer.score(new Description("Text with luminoso keyword added")));
    }

    @Test
    void severalKeywords() {
        verifyScore(10, keywordScorer.score(new Description("Luminoso and Reformado")));
        verifyScore(15, keywordScorer.score(new Description("Luminoso reformado céntrico")));
    }


    private void verifyScore(int expectedScore, int score) {
        assertEquals(expectedScore, score);
    }
}