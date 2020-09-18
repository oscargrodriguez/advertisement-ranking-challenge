package com.idealista.domain.score.description;

import com.idealista.domain.model.advertisement.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KeywordScorerTest {
    public static final int KEYWORD_SCORE = 5;
    private KeywordScorer keywordScorer;

    @BeforeEach
    void setUp() {
        keywordScorer = new KeywordScorer();
        ReflectionTestUtils.setField(keywordScorer, "keywordScore", KEYWORD_SCORE);
    }

    @Test
    void oneKeyword() {
        verifyScore(KEYWORD_SCORE, keywordScorer.score(new Description("Text with Luminoso keyword added")));
        verifyScore(KEYWORD_SCORE, keywordScorer.score(new Description("Text with Nuevo keyword added")));
        verifyScore(KEYWORD_SCORE, keywordScorer.score(new Description("Text with Céntrico keyword added")));
        verifyScore(KEYWORD_SCORE, keywordScorer.score(new Description("Text with Reformado keyword added")));
        verifyScore(KEYWORD_SCORE, keywordScorer.score(new Description("Text with Ático keyword added")));
    }

    @Test
    void severalSpaces() {
        verifyScore(KEYWORD_SCORE, keywordScorer.score(new Description("Any   luminoso   any")));
    }

    @Test
    void caseSensitive() {
        verifyScore(KEYWORD_SCORE, keywordScorer.score(new Description("Text with luminoso keyword added")));
        verifyScore(KEYWORD_SCORE, keywordScorer.score(new Description("Text with LUMINOSO keyword added")));
    }

    @Test
    void encoding() {
        verifyScore(KEYWORD_SCORE, keywordScorer.score(new Description("any atico any")));
        verifyScore(KEYWORD_SCORE, keywordScorer.score(new Description("any ático any")));
        verifyScore(KEYWORD_SCORE * 2, keywordScorer.score(new Description("any atico centrico any")));
        verifyScore(KEYWORD_SCORE * 2, keywordScorer.score(new Description("any ático céntrico any")));
    }

    @Test
    void severalKeywords() {
        verifyScore(KEYWORD_SCORE * 2, keywordScorer.score(new Description("Luminoso and Reformado")));
        verifyScore(KEYWORD_SCORE * 3, keywordScorer.score(new Description("Luminoso reformado céntrico")));
    }

    @Test
    void keywordContainedInTextButNotAWord() {
        verifyScore(0, keywordScorer.score(new Description("anyany anyatico any")));
    }

    private void verifyScore(int expectedScore, int score) {
        assertEquals(expectedScore, score);
    }
}