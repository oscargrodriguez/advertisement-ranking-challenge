package com.idealista.domain.score.description;

import com.idealista.domain.model.advertisement.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KeywordRetrieverTest {
    private KeywordRetriever keywordRetriever;

    @BeforeEach
    void setUp() {
        keywordRetriever = new KeywordRetriever();
    }

    @Test
    void oneKeyword() {
        assertEquals(1, keywordRetriever.getNumberOfKeywords("Text with Luminoso keyword added"));
        assertEquals(1, keywordRetriever.getNumberOfKeywords("Text with Nuevo keyword added"));
        assertEquals(1, keywordRetriever.getNumberOfKeywords("Text with Céntrico keyword added"));
        assertEquals(1, keywordRetriever.getNumberOfKeywords("Text with Reformado keyword added"));
        assertEquals(1, keywordRetriever.getNumberOfKeywords("Text with Ático keyword added"));
    }

    @Test
    void severalSpaces() {
        assertEquals(1, keywordRetriever.getNumberOfKeywords("Any   luminoso   any"));
    }

    @Test
    void caseSensitive() {
        assertEquals(1, keywordRetriever.getNumberOfKeywords("Text with luminoso keyword added"));
        assertEquals(1, keywordRetriever.getNumberOfKeywords("Text with LUMINOSO keyword added"));
    }

    @Test
    void encoding() {
        assertEquals(1, keywordRetriever.getNumberOfKeywords("any atico any"));
        assertEquals(1, keywordRetriever.getNumberOfKeywords("any ático any"));
        assertEquals(2, keywordRetriever.getNumberOfKeywords("any atico centrico any"));
        assertEquals(2, keywordRetriever.getNumberOfKeywords("any ático céntrico any"));
    }

    @Test
    void severalKeywords() {
        assertEquals(2, keywordRetriever.getNumberOfKeywords("Luminoso and Reformado"));
        assertEquals(3, keywordRetriever.getNumberOfKeywords("Luminoso reformado céntrico"));
    }

    @Test
    void keywordContainedInTextButNotAWord() {
        assertEquals(0, keywordRetriever.getNumberOfKeywords("anyany anyatico any"));
    }
}