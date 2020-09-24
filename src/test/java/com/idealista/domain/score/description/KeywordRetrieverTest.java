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
        assertEquals(Integer.valueOf(1), keywordRetriever.getNumberOfKeywords("Text with Luminoso keyword added"));
        assertEquals(Integer.valueOf(1), keywordRetriever.getNumberOfKeywords("Text with Nuevo keyword added"));
        assertEquals(Integer.valueOf(1), keywordRetriever.getNumberOfKeywords("Text with Céntrico keyword added"));
        assertEquals(Integer.valueOf(1), keywordRetriever.getNumberOfKeywords("Text with Reformado keyword added"));
        assertEquals(Integer.valueOf(1), keywordRetriever.getNumberOfKeywords("Text with Ático keyword added"));
    }

    @Test
    void severalSpaces() {
        assertEquals(Integer.valueOf(1), keywordRetriever.getNumberOfKeywords("Any   luminoso   any"));
    }

    @Test
    void caseSensitive() {
        assertEquals(Integer.valueOf(1), keywordRetriever.getNumberOfKeywords("Text with luminoso keyword added"));
        assertEquals(Integer.valueOf(1), keywordRetriever.getNumberOfKeywords("Text with LUMINOSO keyword added"));
    }

    @Test
    void encoding() {
        assertEquals(Integer.valueOf(1), keywordRetriever.getNumberOfKeywords("any atico any"));
        assertEquals(Integer.valueOf(1), keywordRetriever.getNumberOfKeywords("any ático any"));
        assertEquals(Integer.valueOf(2), keywordRetriever.getNumberOfKeywords("any atico centrico any"));
        assertEquals(Integer.valueOf(2), keywordRetriever.getNumberOfKeywords("any ático céntrico any"));
    }

    @Test
    void severalKeywords() {
        assertEquals(Integer.valueOf(2), keywordRetriever.getNumberOfKeywords("Luminoso and Reformado"));
        assertEquals(Integer.valueOf(3), keywordRetriever.getNumberOfKeywords("Luminoso reformado céntrico"));
    }

    @Test
    void keywordContainedInTextButNotAWord() {
        assertEquals(Integer.valueOf(0), keywordRetriever.getNumberOfKeywords("anyany anyatico any"));
    }
}