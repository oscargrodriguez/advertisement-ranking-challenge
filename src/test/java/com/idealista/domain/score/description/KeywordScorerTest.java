package com.idealista.domain.score.description;

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
    void severalSpaces() {
        verifyScore(5, keywordScorer.score(new Description("Any   luminoso   any")));
    }

    @Test
    void caseSensitive() {
        verifyScore(5, keywordScorer.score(new Description("Text with luminoso keyword added")));
        verifyScore(5, keywordScorer.score(new Description("Text with LUMINOSO keyword added")));
    }

    @Test
    void encoding() {
        verifyScore(5, keywordScorer.score(new Description("any atico any")));
        verifyScore(5, keywordScorer.score(new Description("any ático any")));
        verifyScore(10, keywordScorer.score(new Description("any atico centrico any")));
        verifyScore(10, keywordScorer.score(new Description("any ático céntrico any")));
    }

    @Test
    void severalKeywords() {
        verifyScore(10, keywordScorer.score(new Description("Luminoso and Reformado")));
        verifyScore(15, keywordScorer.score(new Description("Luminoso reformado céntrico")));
    }

    @Test
    void keywordContainedInTextButNotAWord() {
        verifyScore(0, keywordScorer.score(new Description("anyany anyatico any")));
    }

    private void verifyScore(int expectedScore, int score) {
        assertEquals(expectedScore, score);
    }
}